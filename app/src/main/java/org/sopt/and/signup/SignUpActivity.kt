package org.sopt.and.signup

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowLeft
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.sopt.and.GlobalApplication
import org.sopt.and.R
import org.sopt.and.component.RoundedButton
import org.sopt.and.component.SignTextField
import org.sopt.and.component.Toolbar
import org.sopt.and.signin.SigninActivity
import org.sopt.and.ui.theme.ANDANDROIDTheme
import org.sopt.and.ui.theme.Black
import org.sopt.and.ui.theme.White


class SignupActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ANDANDROIDTheme {
                SignUpScreen()
            }
        }
    }
}

fun isValidEmail(email: String): Boolean {
    val emailRegex = "^[A-Za-z](.*)([@])(.+)(\\.)(.+)"
    return email.matches(emailRegex.toRegex())
}

fun isValidPwd(pwd: String): Boolean {
    if (pwd.length !in 8..20) return false

    var upperCase = false
    var lowerCase = false
    var num = false
    var specialChar = false

    pwd.forEach {
        when {
            it.isUpperCase() -> upperCase = true
            it.isLowerCase() -> lowerCase = true
            it.isDigit() -> num = true
            "!@#\$%^&*()_+-=[]{}|;:'\",.<>?/~`".contains(it) -> specialChar = true
        }
    }

    val validCheck = listOf(upperCase, lowerCase, num, specialChar).count { it }

    return validCheck >= 3
}

fun login(email: String, pwd: String) : Boolean {
    return isValidEmail(email) && isValidPwd(pwd)
}

@Composable
fun SignUpScreen() {
    val email = remember { mutableStateOf("") }
    val pwd = remember { mutableStateOf("") }
    val isPwdVisible = remember { mutableStateOf(false) }
    val context = LocalContext.current

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Black)
            .statusBarsPadding()
            .navigationBarsPadding()
            .imePadding(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Toolbar(
            content = stringResource(R.string.signup),
            leadingIcon = {
                IconButton(onClick = {}) {
                    Icon(
                       imageVector = Icons.AutoMirrored.Filled.KeyboardArrowLeft,
                        contentDescription = null,
                        tint = White,
                        modifier = Modifier
                            .size(40.dp)
                    )
                }
            }
        )

        Text(
            color = White,
            text = stringResource(R.string.signup_title),
            fontSize = 25.sp,
            lineHeight = 32.sp,
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 16.dp, end = 16.dp, top = 20.dp)
        )

        // Email 입력
        SignTextField(
            value = email.value,
            onValueChange = { newValue -> email.value = newValue },
            isPwdVisible = true,
            placeholder = stringResource(R.string.signup_id_placeholder),
            modifier = Modifier.padding(10.dp)
        )
        Text(
            color = White,
            text = stringResource(R.string.signup_id_warning),
            fontSize = 14.sp,
            modifier = Modifier.padding(start = 16.dp, end = 16.dp, top = 8.dp)
        )

        // Password 입력
        SignTextField(
            value = pwd.value,
            onValueChange = { newValue -> pwd.value = newValue },
            placeholder = stringResource(R.string.signup_pwd_placeholder),
            modifier = Modifier.padding(10.dp),
            isPwdVisible = isPwdVisible.value,
            onPwdVisibilityChange = {
                isPwdVisible.value = !isPwdVisible.value
            }
        )
        Text(
            color = White,
            text = stringResource(R.string.signup_pwd_warning),
            fontSize = 14.sp,
            modifier = Modifier.padding(start = 16.dp, end = 16.dp, top = 8.dp)
        )
        Spacer(modifier = Modifier.height(20.dp))

        Text(
            color = White,
            text = stringResource(R.string.sign_social),
            fontSize = 14.sp
        )
        Row (
            modifier = Modifier
                .padding(top = 20.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Image(
                painter = painterResource(R.drawable.btn_kakao),
                contentDescription = "Facebook",
                modifier = Modifier
                    .size(32.dp)

            )
            Image(
                painter = painterResource(R.drawable.btn_naver),
                contentDescription = "Twitter",
                modifier = Modifier
                    .size(32.dp)
            )
            Image(
                painter = painterResource(R.drawable.btn_google),
                contentDescription = "Google",
                modifier = Modifier
                    .size(32.dp)
            )
        }
        Spacer(modifier = Modifier.height(40.dp))

        Text(
            color = White,
            text = stringResource(R.string.warning),
            fontSize = 14.sp,
            modifier = Modifier.padding(start = 16.dp, end = 16.dp, top = 8.dp)
        )

        Spacer(modifier = Modifier.weight(1f))

        // 회원가입 버튼
        RoundedButton(
            content = stringResource(R.string.signup),
            onClick = {
                if(login(email.value, pwd.value)) {
                    val dataStore = GlobalApplication.getInstance().getDataStore()

                    CoroutineScope(Dispatchers.IO).launch {
                        dataStore.saveEmail(email.value)
                        dataStore.savePwd(pwd.value)

                        withContext(Dispatchers.Main) {
                            val resultIntent = Intent().apply {
                                putExtra("email", email.value)
                                putExtra("pwd", pwd.value)
                            }

                            (context as? Activity)?.setResult(Activity.RESULT_OK, resultIntent)
                            (context as? Activity)?.finish()
                        }
                    }
                } else {
                    Toast.makeText(context,"잘못된 형식입니다.", Toast.LENGTH_SHORT).show()
                }
            }
        )
    }
}

@Preview(showSystemUi = true)
@Composable
fun GreetingPreview2() {
    ANDANDROIDTheme {
        SignUpScreen()
    }
}
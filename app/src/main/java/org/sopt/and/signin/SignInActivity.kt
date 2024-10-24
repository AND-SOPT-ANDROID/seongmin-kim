package org.sopt.and.signin

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContract
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import org.sopt.and.GlobalApplication
import org.sopt.and.R
import org.sopt.and.component.RoundedButton
import org.sopt.and.component.SignTextField
import org.sopt.and.component.Toolbar
import org.sopt.and.mypage.MyPageActivity
import org.sopt.and.signup.SignupActivity
import org.sopt.and.ui.theme.ANDANDROIDTheme
import org.sopt.and.ui.theme.Black
import org.sopt.and.ui.theme.White

class SigninActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            SignInScreen()
        }
    }
}

@Composable
fun SignInScreen() {
    var saveEmail = ""
    var savePwd = ""
    val email = remember { mutableStateOf("") }
    val pwd = remember { mutableStateOf("") }
    val isPwdVisible = remember { mutableStateOf(false) }

    val context = LocalContext.current
    val dataStore = GlobalApplication.getInstance().getDataStore()

    LaunchedEffect(Unit) {
        saveEmail = dataStore.getEmail().first().toString()
        savePwd = dataStore.getPwd().first().toString()
    }

    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.StartActivityForResult()) { res ->
        val data = res.data
        email.value = data?.getStringExtra("email") ?: ""
        pwd.value = data?.getStringExtra("pwd") ?: ""
    }

    val snackBarHostState = remember { SnackbarHostState() }
    val coroutineScope = rememberCoroutineScope()

    Scaffold(
        snackbarHost = { SnackbarHost(hostState = snackBarHostState) }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Black)
                .padding(innerPadding)
                .statusBarsPadding()
                .navigationBarsPadding()
                .imePadding(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Toolbar(
                content = stringResource(R.string.wavve),
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
            Spacer(modifier = Modifier.height(40.dp))

            // email 입력
            SignTextField(
                value = email.value,
                onValueChange = { newValue -> email.value = newValue },
                isPwdVisible = true,
                placeholder = stringResource(R.string.id_placeholder),
                modifier = Modifier.padding(10.dp)
            )

            // pwd 입력
            SignTextField(
                value = pwd.value,
                onValueChange = { newValue -> pwd.value = newValue },
                placeholder = stringResource(R.string.pwd_placeholder),
                isPwdVisible = isPwdVisible.value,
                onPwdVisibilityChange = {
                    isPwdVisible.value = !isPwdVisible.value
                },
                modifier = Modifier.padding(10.dp)
            )
            Spacer(modifier = Modifier.height(20.dp))

            RoundedButton(
                content = stringResource(R.string.signin),
                onClick = {
                    coroutineScope.launch {
                        if (saveEmail == email.value && savePwd == pwd.value) {
                            snackBarHostState.showSnackbar("로그인 성공")
                            val intent = Intent(context, MyPageActivity::class.java)
                            intent.putExtra("email", email.value)
                            context.startActivity(intent)
                        } else {
                            snackBarHostState.showSnackbar("로그인 실패")
                        }
                    }
                }
            )

            Row(
                modifier = Modifier
                    .padding(top = 20.dp),
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                Text(
                    text = stringResource(R.string.find_id),
                    style = TextStyle(color = White)
                )
                Text(
                    text = "비밀번호 찾기",
                    style = TextStyle(color = White)
                )
                Text(
                    text = stringResource(R.string.signup),
                    style = TextStyle(color = White),
                    modifier = Modifier.clickable {
                        val intent = Intent(context, SignupActivity::class.java)
                        launcher.launch(intent)
                    }
                )
            }

            Spacer(modifier = Modifier.height(20.dp))

            Text(
                color = White,
                text = stringResource(R.string.sign_social),
                fontSize = 14.sp
            )
            Row(
                modifier = Modifier
                    .padding(top = 20.dp),
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                Image(
                    painter = painterResource(R.drawable.btn_kakao),
                    contentDescription = null,
                    modifier = Modifier
                        .size(32.dp)
                )
                Image(
                    painter = painterResource(R.drawable.btn_naver),
                    contentDescription = null,
                    modifier = Modifier
                        .size(32.dp)
                )
                Image(
                    painter = painterResource(R.drawable.btn_google),
                    contentDescription = null,
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
        }
    }
}

@Preview(showSystemUi = true)
@Composable
fun GreetingPreview3() {
    ANDANDROIDTheme {
        SignInScreen()
    }
}
package org.sopt.and.signup

import android.os.Bundle
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
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.sopt.and.R
import org.sopt.and.component.RoundedButton
import org.sopt.and.component.SignTextField
import org.sopt.and.component.Toolbar
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

@Composable
fun SignUpScreen() {
    val email = remember { mutableStateOf("") }

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
        SignTextField(
            value = email.value,
            onValueChange = { newValue ->
                email.value = newValue  // 입력된 값으로 상태 업데이트
            },
            placeholder = stringResource(R.string.signup_id_placeholder),
            modifier = Modifier.padding(10.dp)
        )
        Text(
            color = White,
            text = stringResource(R.string.signup_id_warning),
            fontSize = 14.sp,
            modifier = Modifier.padding(start = 16.dp, end = 16.dp, top = 8.dp)
        )
        SignTextField(
            value = email.value,
            onValueChange = { newValue ->
                email.value = newValue  // 입력된 값으로 상태 업데이트
            },
            placeholder = stringResource(R.string.signup_id_placeholder),
            modifier = Modifier.padding(10.dp)
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

        RoundedButton(
            content = stringResource(R.string.signup),
            onClick = {}
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
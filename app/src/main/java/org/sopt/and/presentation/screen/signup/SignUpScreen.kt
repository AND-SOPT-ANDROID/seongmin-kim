package org.sopt.and.presentation.screen.signup

import android.util.Log
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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.sopt.and.R
import org.sopt.and.presentation.component.RoundedButton
import org.sopt.and.presentation.component.SignTextField
import org.sopt.and.presentation.component.Toolbar
import org.sopt.and.ui.theme.Black
import org.sopt.and.ui.theme.White

@Composable
fun SignUpScreen(
    navigateUp: () -> Unit,
    navigateSignIn: () -> Unit,
    signUpEmail: String,
    signUpPwd: String,
    onEmailChange: (String) -> Unit,
    onPwdChange: (String) -> Unit,
    isPwdVisibility: Boolean,
    isPwdVisible: () -> Unit,
    isSignUp: (String, String) -> Unit,
    signUpSuccess: Boolean
) {

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
                IconButton(onClick = navigateUp) {
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
            value = signUpEmail,
            onValueChange = onEmailChange,
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
            value = signUpPwd,
            onValueChange = onPwdChange,
            placeholder = stringResource(R.string.signup_pwd_placeholder),
            modifier = Modifier.padding(10.dp),
            isPwdVisible = isPwdVisibility,
            onPwdVisibilityChange = isPwdVisible
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
                isSignUp(signUpEmail, signUpPwd)
            }
        )
    }

    if (signUpSuccess) {
        LaunchedEffect(Unit) {
            navigateSignIn() // 회원가입 성공 시 navigateSignIn 호출
            Log.d("로그","LaunchedEffect navigateSignIn")
        }
    }

}
package org.sopt.and.presentation.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
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
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.sopt.and.R
import org.sopt.and.presentation.component.RoundedButton
import org.sopt.and.presentation.component.SignTextField
import org.sopt.and.presentation.component.Toolbar
import org.sopt.and.ui.theme.Black
import org.sopt.and.ui.theme.White

@Composable
fun SignInScreen(
    navigateUp: () -> Unit,
    navigateSignUp: () -> Unit,
//    signUpEmail: String,
//    signUpPwd: String,
    signInEmail: String,
    signInPwd: String,
    onEmailChange: (String) -> Unit,
    onPwdChange: (String) -> Unit,
    isPwdVisibility: Boolean,
    isPwdVisible: () -> Unit,
    isLogin: (String, String) -> Unit,
    snackbarHostState: SnackbarHostState
) {
    Scaffold(
        snackbarHost = { SnackbarHost(hostState = snackbarHostState) }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Black)
                .statusBarsPadding()
                .navigationBarsPadding()
                .imePadding(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            // 툴바
            Toolbar(
                content = stringResource(R.string.wavve),
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
            Spacer(modifier = Modifier.height(40.dp))

            // 이메일 입력
            SignTextField(
                value = signInEmail,
                onValueChange = onEmailChange,
                isPwdVisible = false,  // 이메일은 false로 고정
                placeholder = stringResource(R.string.id_placeholder),
                modifier = Modifier.padding(10.dp)
            )

            // 비밀번호 입력
            SignTextField(
                value = signInPwd,
                onValueChange = onPwdChange,
                isPwdVisible = isPwdVisibility,
                onPwdVisibilityChange = isPwdVisible,
                placeholder = stringResource(R.string.pwd_placeholder),
                modifier = Modifier.padding(10.dp)
            )

            // 로그인 버튼
            RoundedButton(
                content = stringResource(R.string.signin),
                onClick = {
                    isLogin(signInEmail, signInPwd) // 로그인
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
                       navigateSignUp() // 클릭시 회원가입으로 이동
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
                    contentDescription = "카카오",
                    modifier = Modifier
                        .size(32.dp)
                )
                Image(
                    painter = painterResource(R.drawable.btn_naver),
                    contentDescription = "네이버",
                    modifier = Modifier
                        .size(32.dp)
                )
                Image(
                    painter = painterResource(R.drawable.btn_google),
                    contentDescription = "구글",
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

//    // ViewModel에서 저장된 로그인 정보를 불러오기
//    LaunchedEffect(Unit) {
//        viewModel.loadSignInInfo()
//    }
}
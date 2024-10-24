package org.sopt.and.presentation.navigation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import org.sopt.and.presentation.screen.mypage.MyPageScreen
import org.sopt.and.presentation.screen.signin.SignInScreen
import org.sopt.and.presentation.screen.signup.SignUpScreen
import org.sopt.and.presentation.viewmodel.SignInViewModel
import org.sopt.and.presentation.viewmodel.SignUpViewModel

@Composable
fun Navigation(
    modifier: Modifier = Modifier
) {
   Scaffold (
       modifier = Modifier.fillMaxSize()
   ) { innerPadding ->
       val navController = rememberNavController()
       NavHost(
           navController = navController,
           startDestination = Routes.SignInScreen
       ) {
           composable<Routes.SignInScreen> {
               val signInViewModel: SignInViewModel = viewModel()
               val signInEmail = signInViewModel.signInState.collectAsState().value.email
               SignInScreen(
                   navigateUp = { navController.popBackStack() },
                   navigateSignUp = { navController.navigate(Routes.SignUpScreen) },
                   navigateMyPage = {navController.navigate(Routes.MyPageScreen(signInEmail))},
                   signInEmail = signInEmail,
                   signInPwd = signInViewModel.signInState.collectAsState().value.password,
                   onEmailChange = { signInViewModel.setSignInEmail(it) },
                   onPwdChange  = { signInViewModel.setSignInPwd(it) },
                   isPwdVisibility = signInViewModel.signInState.collectAsState().value.isPassWordVisibility,
                   isPwdVisible = { signInViewModel.togglePasswordVisibility() },
                   isLogin = { signInEmail, signInPwd -> signInViewModel.login() },
                   signInSuccess = signInViewModel.isSignInSuccess.collectAsState().value,
                   snackbarHostState = remember { SnackbarHostState() }
               )
           }

           composable<Routes.SignUpScreen> {
               val signUpViewModel: SignUpViewModel = viewModel()
               SignUpScreen(
                   navigateUp = { navController.popBackStack() },
                   navigateSignIn = { navController.navigate(Routes.SignInScreen) },
                   signUpEmail = signUpViewModel.signUpState.collectAsState().value.email,
                   signUpPwd = signUpViewModel.signUpState.collectAsState().value.password,
                   onEmailChange = { signUpViewModel.setSignUpEmail(it) },
                   onPwdChange  = { signUpViewModel.setSignUpPwd(it) },
                   isPwdVisibility = signUpViewModel.signUpState.collectAsState().value.isPassWordVisibility,
                   isPwdVisible = { signUpViewModel.togglePasswordVisibility() },
                   isSignUp = { signUpEmail, signUpPwd -> signUpViewModel.signUp() },
                   signUpSuccess = signUpViewModel.isSignUpSuccess.collectAsState().value
               )
           }

           composable<Routes.MyPageScreen> {
               val args = it.toRoute<Routes.MyPageScreen>()
               MyPageScreen(
                   email = args.email
               )
           }
       }
   }

}

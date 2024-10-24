package org.sopt.and.presentation.navigation

import kotlinx.serialization.Serializable

sealed interface Routes {
    @Serializable
    data object SignInScreen : Routes

    @Serializable
    data object SignUpScreen : Routes

    @Serializable
    data class MyPageScreen(val email: String) : Routes

}
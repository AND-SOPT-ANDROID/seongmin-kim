package org.sopt.and.presentation.state

data class SignInState(
    val email: String = "",
    val password: String = "",
    var isPassWordVisibility: Boolean = false,
    val loginStatus: Boolean? = null,
)

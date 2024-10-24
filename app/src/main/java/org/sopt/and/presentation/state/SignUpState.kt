package org.sopt.and.presentation.state

data class SignUpState (
    val email: String = "",
    val password: String = "",
    var isPassWordVisibility: Boolean = false,
)
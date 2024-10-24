package org.sopt.and.utils

object RegexManager {
    val emailRegex = Regex("[0-9a-zA-Z]+(.[_a-z0-9-]+)*@(?:\\\\w+\\\\.)+\\\\w+\$")
    val specialCharRegex = Regex("[!@#\$%^&*()_+\\-=[\\]{}|;:'\",.<>?/~`]")
}
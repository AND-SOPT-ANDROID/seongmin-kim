package org.sopt.and.utils

object RegexManager {
    val emailRegex = Regex("^[A-Za-z](.*)([@])(.+)(\\.)(.+)")
    val passwordRegex = Regex("^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{8,}$")
}
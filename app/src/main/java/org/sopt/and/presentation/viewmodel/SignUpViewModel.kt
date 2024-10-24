package org.sopt.and.presentation.viewmodel

import android.util.Patterns
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import org.sopt.and.GlobalApplication
import org.sopt.and.presentation.state.SignUpState
import java.util.regex.Pattern

class SignUpViewModel : ViewModel() {

    private val _signUpState = MutableStateFlow(SignUpState())
    val signUpState: StateFlow<SignUpState>
        get() =  _signUpState.asStateFlow()

    private val _isSignUpSuccess = MutableStateFlow(false) // 회원가입 성공 여부
    val isSignUpSuccess: StateFlow<Boolean>
        get() = _isSignUpSuccess.asStateFlow()

    val dataStore = GlobalApplication.getInstance().getDataStore()

    // 회원가입 처리 함수
    fun signUp() {
        viewModelScope.launch {
            if(isValidEmail(_signUpState.value.email) && isValidPwd(_signUpState.value.password)) {
                dataStore.saveEmail(_signUpState.value.email)
                dataStore.savePwd(_signUpState.value.password)
                _isSignUpSuccess.value = true
            } else {
                _isSignUpSuccess.value = false
            }
        }
    }

    private fun isValidEmail(email: String): Boolean {
        val pattern: Pattern = Patterns.EMAIL_ADDRESS
        return pattern.matcher(email).matches()
    }

    private fun isValidPwd(password: String): Boolean {
        if(password.length !in MIN_PWD_SIZE..MAX_PWD_SIZE) return false

        val validCheckNum = listOf(
            password.any {it.isUpperCase()},
            password.any {it.isLowerCase()},
            password.any {it.isDigit()},
            password.any {!it.isLetterOrDigit()}, // 정규식 Regex Object 생성자 문제로 제거
        ).count { it }

        return validCheckNum >= 3
    }

    // 비밀번호 표시 여부
    fun togglePasswordVisibility() {
        _signUpState.value = _signUpState.value.copy(
            isPassWordVisibility = !_signUpState.value.isPassWordVisibility
        )
    }

    // 이메일 입력 처리
    fun setSignUpEmail(newEmail: String) {
        _signUpState.value = _signUpState.value.copy(
            email = newEmail
        )
    }

    // 비밀번호 입력 처리
    fun setSignUpPwd(newPassword: String) {
        _signUpState.value = _signUpState.value.copy(
            password = newPassword
        )
    }

    companion object {
        const val MIN_PWD_SIZE = 8
        const val MAX_PWD_SIZE = 20
    }


}
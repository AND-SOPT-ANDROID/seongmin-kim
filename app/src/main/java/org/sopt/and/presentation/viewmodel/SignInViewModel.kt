package org.sopt.and.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.launch
import org.sopt.and.GlobalApplication
import org.sopt.and.presentation.state.SignInState

class SignInViewModel : ViewModel() {

    private val _signInState = MutableStateFlow(SignInState())
    val signInState: StateFlow<SignInState>
        get() = _signInState.asStateFlow()

    private val _isSignInSuccess = MutableStateFlow(false) // 로그인 성공 여부
    val isSignInSuccess: StateFlow<Boolean>
        get() = _isSignInSuccess.asStateFlow()

    private val _loginStatusMessage = MutableStateFlow("")
    val loginStatusMessage: StateFlow<String> = _loginStatusMessage

    private val dataStore = GlobalApplication.getInstance().getDataStore()

    // 로그인 처리 함수
    fun login() {
        viewModelScope.launch {
            val savedEmail = dataStore.getEmail().first()
            val savedPassword = dataStore.getPwd().first()

            val isLoginSuccess = (savedEmail == _signInState.value.email) &&
                    (savedPassword == _signInState.value.password)
            Log.d("로그","isLoginSuccess $isLoginSuccess")
            Log.d("로그","savedEmail $savedEmail")
            Log.d("로그","savedPassword $savedPassword")
            Log.d("로그","_signInState.value.email ${_signInState.value.email}")
            Log.d("로그","_signInState.value.password ${_signInState.value.password}")

            if(isLoginSuccess) {
                _signInState.value = _signInState.value.copy(loginStatus = isLoginSuccess)
                _isSignInSuccess.value = isLoginSuccess
            } else {
                _isSignInSuccess.value = !isLoginSuccess
            }
        }
    }


    // 비밀번호 표시 여부
    fun togglePasswordVisibility() {
        _signInState.value = _signInState.value.copy(
            isPassWordVisibility = !_signInState.value.isPassWordVisibility
        )
    }

    // 이메일 입력 처리
    fun setSignInEmail(newEmail: String) {
        _signInState.value = _signInState.value.copy(
            email = newEmail
        )
    }

    // 비밀번호 입력 처리
    fun setSignInPwd(newPassword: String) {
        _signInState.value = _signInState.value.copy(
            password = newPassword
        )
    }

    // 이메일, 비밀번호 로드
    fun loadSignInInfo() {
        viewModelScope.launch {
            val email = dataStore.getEmail().firstOrNull() ?: ""
            val password = dataStore.getPwd().firstOrNull() ?: ""
            _signInState.value = _signInState.value.copy(
                email = email,
                password = password
            )
        }
    }

}
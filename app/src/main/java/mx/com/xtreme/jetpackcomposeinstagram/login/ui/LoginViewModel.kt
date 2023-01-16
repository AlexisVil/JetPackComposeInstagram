package mx.com.xtreme.jetpackcomposeinstagram.login.ui

import android.util.Log
import android.util.Patterns
import androidx.compose.runtime.Composable
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import mx.com.xtreme.jetpackcomposeinstagram.login.domain.usescases.LoginUseCase
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(private val loginUseCase: LoginUseCase) : ViewModel() {

    //val loginUseCase = LoginUseCase()

    //Campos de Email LiveData
    private val _email = MutableLiveData<String>()
    val email: LiveData<String> = _email

    //Campos de Password LiveData
    private val _password = MutableLiveData<String>()
    val password: LiveData<String> = _password

    //Login Enabled LiveData
    private val _isLoginEnabled = MutableLiveData<Boolean>()
    val isLoginEnabled: LiveData<Boolean> = _isLoginEnabled

    //Loader  LiveData
    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading


    fun onLoginChanged(email: String, password: String) {
        _email.value = email
        _password.value = password
        _isLoginEnabled.value = LoginEnabled(email, password)
    }

    fun LoginEnabled(email: String, password: String) =
        Patterns.EMAIL_ADDRESS.matcher(email).matches() && password.length > 5

    fun onLoginSelected() {
        viewModelScope.launch {
            _isLoading.value = true
            val result = loginUseCase(
                email.value!!,
                password.value!!
            )
            if (result) {
                Log.i("result", "Result OK")
            }
            _isLoading.value = false
        }
    }
}
package co.edu.unab.etdm.cg.storeapp.login.ui.viewmodel

import co.edu.unab.etdm.cg.storeapp.core.ui.model.User
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor() : ViewModel() {
    private val _email = MutableLiveData<String>()
    val email: LiveData<String> = _email

    private val _password = MutableLiveData<String>()
    val password: LiveData<String> = _password

    private val _isLogin = MutableLiveData<Boolean>()
    val isLogin: LiveData<Boolean> = _isLogin

    private val _isError = MutableLiveData<Boolean>()
    val isError: LiveData<Boolean> = _isError

    init {
        _email.value = ""
        _password.value = ""
        _isLogin.value = false
        _isError.value = false
    }

    fun onEmailChanged(email: String) {
        _email.value = email
    }

    fun onPasswordChanged(password: String) {
        _password.value = password
    }

    fun onIsLoginChange(isLogin: Boolean) {
        _isLogin.value = isLogin
    }

    fun onIsErrorChange(isError: Boolean) {
        _isError.value = isError
    }

    fun login(): String {
        /*        return when (_email.value == getUser().email && _password.value == getUser().password) {
                    true -> "Iniciando sesión...."
                    false -> "Usuario o contraseña invalido porfavor intente de nuevo"
                }*/
        return "Iniciando sesión...."
    }

    private fun getUser(): User {
        return User(document = 1234, name = "", email = "1")
    }

    fun verifyLogin(): Boolean {
        // TODO: refactor
        /*        _isLogin.value = (_email.value == getUser().email
                        && _password.value == getUser().password)
                return _isLogin.value == true*/
        return true
    }
}
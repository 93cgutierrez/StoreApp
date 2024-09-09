package co.edu.unab.etdm.cg.storeapp.signup.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import co.edu.unab.etdm.cg.storeapp.core.ui.model.User
import co.edu.unab.etdm.cg.storeapp.signup.domain.CreateAccountUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignUpViewModel @Inject constructor(private val createAccountUseCase: CreateAccountUseCase) :
    ViewModel() {
    fun createAccount(name: String, document: String, email: String, password: String) {
        val user = User(
            name = name,
            document = document.toLong(),
            email = email,
        )
        viewModelScope.launch(Dispatchers.IO) {
            createAccountUseCase(user, password)
        }
    }

}
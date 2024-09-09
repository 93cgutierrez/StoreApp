package co.edu.unab.etdm.cg.storeapp.login.domain

import co.edu.unab.etdm.cg.storeapp.login.model.repository.LoginRepository
import co.edu.unab.etdm.cg.storeapp.login.ui.LoginUIState
import javax.inject.Inject

class LoginUseCase @Inject constructor(private val loginRepository: LoginRepository) {
    suspend operator fun invoke(email: String, password: String): LoginUIState {
        val loginResult = loginRepository(email, password)
        return if (loginResult != null) {
            LoginUIState.Success
        } else {
            LoginUIState.Error
        }
    }
}
package co.edu.unab.etdm.cg.storeapp.signup.domain

import co.edu.unab.etdm.cg.storeapp.core.ui.model.User
import co.edu.unab.etdm.cg.storeapp.signup.data.repository.SignUpRepository
import javax.inject.Inject

class CreateAccountUseCase @Inject constructor(private val signUpRepository: SignUpRepository) {
    suspend operator fun invoke(user: User, password: String): Boolean {
        val accountResult = signUpRepository.createAccount(
            email = user.email,
            password = password
        )
        return if (accountResult != null) {
            println("created account id: ${accountResult.user?.uid}")
            true
        } else {
            println("failed to create account")
            false
        }
    }

}
package co.edu.unab.etdm.cg.storeapp.signup.domain

import co.edu.unab.etdm.cg.storeapp.core.ui.model.User
import co.edu.unab.etdm.cg.storeapp.signup.data.repository.SignUpRepository
import javax.inject.Inject

class CreateAccountUseCase @Inject constructor(
    private val signUpRepository: SignUpRepository,
    private val createUserFirestoreUseCase: CreateUserFirestoreUseCase
) {
    suspend operator fun invoke(user: User, password: String): Boolean {
        val accountResult = signUpRepository.createAccount(
            email = user.email,
            password = password
        )
        return if (accountResult != null) {
            val savedId: String = accountResult.user?.uid ?: ""
            println("created account id: $savedId")
            if (savedId.isEmpty())
                return false
            // Update Firestore with user's details
            createUserFirestoreUseCase(user.copy(id = savedId))
            true
        } else {
            println("failed to create account")
            false
        }
    }

}
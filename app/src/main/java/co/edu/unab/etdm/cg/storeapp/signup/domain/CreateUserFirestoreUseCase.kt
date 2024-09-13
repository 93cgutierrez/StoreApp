package co.edu.unab.etdm.cg.storeapp.signup.domain

import co.edu.unab.etdm.cg.storeapp.core.ui.model.User
import co.edu.unab.etdm.cg.storeapp.signup.data.repository.SignUpRepository
import javax.inject.Inject

class CreateUserFirestoreUseCase @Inject constructor(private val signUpRepository: SignUpRepository) {
    suspend operator fun invoke(user: User): Boolean {
        return signUpRepository.createUserFirestore(user)
    }
}
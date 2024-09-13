package co.edu.unab.etdm.cg.storeapp.profile.domain

import androidx.lifecycle.LiveData
import co.edu.unab.etdm.cg.storeapp.core.ui.model.User
import co.edu.unab.etdm.cg.storeapp.user.data.repository.UsersRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetUserByIdUseCase @Inject constructor(private val repository: UsersRepository) {
    operator fun invoke(userId: String): LiveData<User> =
        repository.getUserByIdFirestore(userId) // TODO: Convert to actual User type when available in the User model.
}
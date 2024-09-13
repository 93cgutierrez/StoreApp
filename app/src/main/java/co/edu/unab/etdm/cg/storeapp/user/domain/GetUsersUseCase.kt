package co.edu.unab.etdm.cg.storeapp.user.domain

import co.edu.unab.etdm.cg.storeapp.core.ui.model.User
import co.edu.unab.etdm.cg.storeapp.user.data.repository.UsersRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetUsersUseCase @Inject constructor(
    private val usersRepository: UsersRepository,
) {
    operator fun invoke(): Flow<List<User>> = usersRepository.getAllUsersFirestore()
}
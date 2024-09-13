package co.edu.unab.etdm.cg.storeapp.user.data.repository

import androidx.lifecycle.LiveData
import co.edu.unab.etdm.cg.storeapp.core.ui.model.User
import co.edu.unab.etdm.cg.storeapp.core.data.datasource.UserFirestoreDatasource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class UsersRepository @Inject constructor(
    private val userAPIDatasource: UserFirestoreDatasource,
) {
    //Firestore
    fun getAllUsersFirestore(): Flow<List<User>> = userAPIDatasource.getAll()

    fun getUserByIdFirestore(userId: String): LiveData<User> = userAPIDatasource.getUserById(userId)
}
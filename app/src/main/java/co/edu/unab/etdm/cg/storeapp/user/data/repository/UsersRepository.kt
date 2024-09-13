package co.edu.unab.etdm.cg.storeapp.user.data.repository

import co.edu.unab.etdm.cg.storeapp.core.ui.model.User
import co.edu.unab.etdm.cg.storeapp.product.datasource.ProductAPIDatasource
import co.edu.unab.etdm.cg.storeapp.user.datasource.UserFirestoreDatasource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class UsersRepository @Inject constructor(
    private val userAPIDatasource: UserFirestoreDatasource,
) {
    //Firestore
    fun getAllUsersFirestore(): Flow<List<User>> = userAPIDatasource.getAll()
}
package co.edu.unab.etdm.cg.storeapp.user.datasource

import co.edu.unab.etdm.cg.storeapp.core.data.local.entity.ProductEntity
import co.edu.unab.etdm.cg.storeapp.core.data.network.FirebaseClient
import co.edu.unab.etdm.cg.storeapp.core.data.network.entity.UserEntity
import co.edu.unab.etdm.cg.storeapp.core.ui.model.User
import co.edu.unab.etdm.cg.storeapp.core.ui.model.toProduct
import co.edu.unab.etdm.cg.storeapp.core.ui.model.toUser
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import javax.inject.Inject

private const val COLLECTION_NAME_USERS = "users"

class UserFirestoreDatasource @Inject constructor(private val firebaseClient: FirebaseClient) {
    fun getAll(): Flow<List<User>> {
        return callbackFlow {
            val query = firebaseClient.firestoreDB.collection(COLLECTION_NAME_USERS)
            val subscription = query.addSnapshotListener { snapshot, error ->
                if (error != null) {
                    close(error)
                    return@addSnapshotListener
                }
                if (snapshot != null) {
                    val users = snapshot.documents.map { user ->
                        user.toObject(UserEntity::class.java)!!
                    }
                    trySend(users.map { userEntity -> userEntity.toUser() })
                }
            }
            awaitClose {
                subscription.remove()
            }
        }
    }
}
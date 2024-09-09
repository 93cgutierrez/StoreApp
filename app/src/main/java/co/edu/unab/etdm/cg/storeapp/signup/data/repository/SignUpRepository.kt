package co.edu.unab.etdm.cg.storeapp.signup.data.repository

import co.edu.unab.etdm.cg.storeapp.core.data.network.FirebaseClient
import co.edu.unab.etdm.cg.storeapp.core.ui.model.User
import co.edu.unab.etdm.cg.storeapp.core.ui.model.toUserEntity
import com.google.firebase.auth.AuthResult
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class SignUpRepository @Inject constructor(private val client: FirebaseClient) {
    suspend fun createAccount(email: String, password: String): AuthResult? {
        return client.auth.createUserWithEmailAndPassword(email, password).await()
    }

    suspend fun createUserFirestore(user: User): Boolean {
        return kotlin.runCatching {
            client.firestoreDB.collection("users").document(user.id).set(user.toUserEntity())
                .await()
        }.isSuccess
    }

}
package co.edu.unab.etdm.cg.storeapp.signup.data.repository

import co.edu.unab.etdm.cg.storeapp.core.data.network.FirebaseClient
import com.google.firebase.auth.AuthResult
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class SignUpRepository @Inject constructor(private val client: FirebaseClient) {
    suspend fun createAccount(email: String, password: String): AuthResult? {
        return client.auth.createUserWithEmailAndPassword(email, password).await()
    }

}
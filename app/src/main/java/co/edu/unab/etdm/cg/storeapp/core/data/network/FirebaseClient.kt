package co.edu.unab.etdm.cg.storeapp.core.data.network

import com.google.firebase.auth.FirebaseAuth
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class FirebaseClient @Inject constructor() {
    val auth: FirebaseAuth = FirebaseAuth.getInstance()

}
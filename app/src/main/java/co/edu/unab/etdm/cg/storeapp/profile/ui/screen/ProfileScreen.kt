package co.edu.unab.etdm.cg.storeapp.profile.ui.screen

import android.app.Activity
import android.content.Context
import android.content.Intent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import co.edu.unab.etdm.cg.storeapp.R
import co.edu.unab.etdm.cg.storeapp.core.ui.activity.LoginActivity
import co.edu.unab.etdm.cg.storeapp.profile.ui.viewmodel.ProfileViewModel
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

@Composable
fun ProfileScreen(
    modifier: Modifier,
    profileViewModel: ProfileViewModel
) {
    val context: Context = LocalContext.current
    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        //homework
        //crear screen como filas mostrndo info user email pass name
        //terminar profile
        Text(text = "Profile Screen", modifier = Modifier.align(Alignment.Center))
        Button(
            onClick = {
                logout(context)
            }, modifier = Modifier
                .align(Alignment.Center)
                .padding(top = 40.dp)
        ) {
            Text(text = "Logout")
        }
    }
}


fun logout(context: Context) {
    //create shared preferences
    val sharedPreferences = context.getSharedPreferences(
        context.getString(R.string.prefs_name),
        Context.MODE_PRIVATE
    )
        .edit()
    sharedPreferences.remove("isLogged").apply()

    //signOut authentication
    Firebase.auth.signOut()

    // go to login activity
    val intent = Intent(context, LoginActivity::class.java)
    context.startActivity(intent)
    (context as Activity).finish()

}

package co.edu.unab.etdm.eden.storeapp.profile.ui.screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import co.edu.unab.etdm.eden.storeapp.profile.ui.viewmodel.ProfileViewModel

@Composable
fun ProfileScreen(profileViewModel: ProfileViewModel) {
    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        //homework
        //crear screen como filas mostrndo info user email pass name
        //terminar profile
        Text(text = "Profile Screen", modifier = Modifier.align(Alignment.Center))
    }
}
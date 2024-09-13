package co.edu.unab.etdm.cg.storeapp.profile.ui.screen

import android.app.Activity
import android.content.Context
import android.content.Intent
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.Lifecycle
import co.edu.unab.etdm.cg.storeapp.R
import co.edu.unab.etdm.cg.storeapp.core.ui.activity.LoginActivity
import co.edu.unab.etdm.cg.storeapp.core.ui.model.User
import co.edu.unab.etdm.cg.storeapp.profile.ui.viewmodel.ProfileViewModel
import coil.compose.SubcomposeAsyncImage
import coil.request.ImageRequest
import coil.size.Size
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

@Composable
fun ProfileScreen(
    modifier: Modifier,
    viewModel: ProfileViewModel
) {
    val lifecycle: Lifecycle = LocalLifecycleOwner.current.lifecycle
    val context: Context = LocalContext.current
    //get shared preferences
    val uid = context.getSharedPreferences(
        context.getString(R.string.prefs_name),
        Context.MODE_PRIVATE
    ).getString("uid", null)

    if (uid == null) {
        // go to login activity
        val intent = Intent(context, LoginActivity::class.java)
        context.startActivity(intent)
        (context as Activity).finish()
        return
    }

    val user: User by viewModel.getUserById(uid).observeAsState(
        initial = User(
            document = 0,
            email = "",
            name = ""
        )
    )

    /*    //handler UI states
        val uiState by produceState<ProfileUIState>(
            initialValue = ProfileUIState.Loading,
            key1 = lifecycle, key2 = viewModel
        ) {
            lifecycle.repeatOnLifecycle(state = Lifecycle.State.STARTED) {
                viewModel.uiState.collect { state -> value = state }
            }
        }*/

    if (user.id.isEmpty()) {
        /*        LaunchedEffect(Unit) {
                    Toast.makeText(context, "$user", Toast.LENGTH_SHORT).show()
                }*/
        Text(text = "User not found", modifier = modifier.fillMaxWidth())
    }

    Body(
        context = context,
        modifier = modifier,
        user = user,
    )

    /*    when (uiState) {
            ProfileUIState.Empty -> {
                Text(text = "User not found", modifier = modifier.fillMaxWidth())
            }

            is ProfileUIState.Error -> {
                val errorMessage = (uiState as ProfileUIState.Error).throwable
                Text(text = "Error: ${errorMessage.message}", modifier = modifier.fillMaxWidth())
            }

            ProfileUIState.Idle -> {
                // TODO: Show loading state
                Text(text = "Loading user data", modifier = modifier.fillMaxWidth())
            }

            ProfileUIState.Loading -> {
                Box(modifier = modifier.fillMaxSize()) {
                    CircularProgressIndicator(Modifier.align(Alignment.Center))
                }
            }

            is ProfileUIState.Success -> {
                val user = (uiState as ProfileUIState.Success).user
                Body(
                    modifier = modifier,
                    user = user,
                )

                *//*            Text(text = "Email: ${user.email}", modifier = modifier.fillMaxWidth())
                        Text(text = "Name: ${user.name}", modifier = modifier.fillMaxWidth())
                        Text(text = "Password: ${user.password}", modifier = modifier.fillMaxWidth())
                        Button(
                            onClick = {
                                //editar info user
                                //homework
                            }, modifier = Modifier
                                .align(Alignment.Center)
                                .padding(top = 40.dp)
                        ) {
                            Text(text = "Edit Profile")
                        }*//*
        }
    }*/
}

@Composable
fun Body(context: Context, modifier: Modifier, user: User) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Profile Image
        SubcomposeAsyncImage(
            model = ImageRequest.Builder(LocalContext.current)
                .data(user.image)
                .size(Size.ORIGINAL) // Set the target size to load the image at.
                .error(R.drawable.ic_error)
                .build(),
            loading = {
                CircularProgressIndicator()
            },
            contentDescription = user.name,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .border(2.dp, Color.Transparent)
                .size(200.dp)
                .clip(CircleShape),
        )

        // Name
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 32.dp),
            text = user.name,
            style = MaterialTheme.typography.titleLarge,
            textAlign = TextAlign.Center
        )

        // Document
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = "Document:",
                style = MaterialTheme.typography.bodyMedium
            )
            Text(
                text = "${user.document}",
                style = MaterialTheme.typography.bodyMedium,
                fontWeight = FontWeight.Bold
            )
        }

        // Email
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = "Email:",
                style = MaterialTheme.typography.bodyMedium
            )
            Text(
                text = user.email,
                style = MaterialTheme.typography.bodyMedium,
                fontWeight = FontWeight.Bold
            )
        }
        // Logout button
        Button(
            onClick = {
                logout(context)
            }, modifier = Modifier
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

    sharedPreferences.remove("uid").apply()

    //signOut authentication
    Firebase.auth.signOut()

    // go to login activity
    val intent = Intent(context, LoginActivity::class.java)
    context.startActivity(intent)
    (context as Activity).finish()

}




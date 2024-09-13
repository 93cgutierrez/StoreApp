package co.edu.unab.etdm.cg.storeapp.user.ui.screen

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.produceState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.NavHostController
import co.edu.unab.etdm.cg.storeapp.R
import co.edu.unab.etdm.cg.storeapp.core.ui.model.Product
import co.edu.unab.etdm.cg.storeapp.core.ui.model.User
import co.edu.unab.etdm.cg.storeapp.user.ui.UsersUIState
import co.edu.unab.etdm.cg.storeapp.user.ui.viewmodel.UsersViewModel
import coil.compose.SubcomposeAsyncImage
import coil.request.ImageRequest
import coil.size.Size

@Composable
fun UsersScreen(
    navController: NavHostController,
    modifier: Modifier,
    viewModel: UsersViewModel,
) {
    val lifecycle: Lifecycle = LocalLifecycleOwner.current.lifecycle
    val context: Context = LocalContext.current

    //handler UI states
    val uiState by produceState<UsersUIState>(
        initialValue = UsersUIState.Loading,
        key1 = lifecycle, key2 = viewModel
    ) {
        lifecycle.repeatOnLifecycle(state = Lifecycle.State.STARTED) {
            viewModel.uiState.collect { state -> value = state }
        }
    }

    when (uiState) {
        UsersUIState.Empty -> {
            Text(text = "No users found", modifier = modifier.fillMaxWidth())
        }

        is UsersUIState.Error -> {
            val errorMessage = (uiState as UsersUIState.Error).throwable
            Text(text = "Error: ${errorMessage.message}", modifier = modifier.fillMaxWidth())
        }

        UsersUIState.Idle -> {
        }

        UsersUIState.Loading -> {
            Box(modifier = Modifier.fillMaxSize()) {
                CircularProgressIndicator(Modifier.align(Alignment.Center))
            }
        }

        is UsersUIState.Success -> {
            val usersAPI = (uiState as UsersUIState.Success).users
            UserList(
                modifier = modifier,
                users = usersAPI
            )
        }
    }
}


@Composable
fun UserList(
    modifier: Modifier,
    users: List<User>
) {
    val context: Context = LocalContext.current
    LazyVerticalGrid(
        modifier = modifier
            .fillMaxSize(),
        columns = GridCells.Fixed(2),
        contentPadding = PaddingValues(4.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(users.size) { index ->
            UserItem(
                user = users[index],
                onSelected = { user ->
                    Toast.makeText(
                        context,
                        "on press $index Item: $user",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            )
        }
    }
}

@Composable
fun UserItem(user: User, onSelected: (User) -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(0.dp)
            .size(250.dp)
            .border(border = BorderStroke(1.dp, Color.Green))
            .background(Color.Magenta)
            .pointerInput(Unit) {
                detectTapGestures(
                    onTap = {
                        onSelected(user)
                    },
                )
            }
    ) {
        ConstraintLayout(modifier = Modifier.fillMaxSize()) {
            val (document, image, name, email) = createRefs()
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
                    .fillMaxSize()
                    .constrainAs(image) {
                        top.linkTo(parent.top)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                    }
            )
            /*            Text(
                            modifier = Modifier
                                .fillMaxWidth()
                                .size(40.dp)
                                .background(Color.Black.copy(alpha = 0.6f))
                                .constrainAs(document) {
                                    top.linkTo(parent.top)
                                    start.linkTo(parent.start)
                                    end.linkTo(parent.end)
                                },
                            text = "${user.document}",
                            textAlign = TextAlign.Center,
                            fontSize = 25.sp,
                            color = Color.White.copy(alpha = 0.9f),
                        )*/
            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .size(70.dp)
                    .background(Color.Black.copy(alpha = 0.6f))
                    .constrainAs(name) {
                        top.linkTo(document.bottom)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                    },
                text = user.name,
                textAlign = TextAlign.Center,
                fontSize = 25.sp,
                maxLines = 2,
                minLines = 1,
                color = Color.White.copy(alpha = 0.9f),
            )
            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .size(80.dp)
                    .background(Color.Black.copy(alpha = 0.6f))
                    .constrainAs(email) {
                        bottom.linkTo(image.bottom)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                    },
                text = user.email,
                textAlign = TextAlign.Center,
                fontSize = 20.sp,
                minLines = 1,
                maxLines = 2,
                color = Color.White.copy(alpha = 0.9f),
            )
        }
    }
}

package co.edu.unab.etdm.eden.storeapp.login.screen

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import co.edu.unab.etdm.eden.storeapp.login.viewmodel.LoginViewModel
import co.edu.unab.etdm.eden.storeapp.core.view.MainActivity
import co.edu.unab.etdm.eden.storeapp.R
import coil.compose.AsyncImage

//@Preview(showBackground = true)
@Composable
fun LoginScreen(navController: NavHostController, viewModel: LoginViewModel) {
    //LoginViewModel
    Column(modifier = Modifier.fillMaxSize()) {
        HeaderScreen()
        BodyLogin(navController = navController, viewModel = viewModel)
        FooterLogin()
    }
}

@Composable
fun HeaderScreen() {
    ConstraintLayout(modifier = Modifier.fillMaxWidth()) {
        val (imgLogin, txtLogin) = createRefs()
        AsyncImage(
            model = "https://cengage.my.site.com/resource/1607465003000/loginIcon",
            contentDescription = "Logo",
            Modifier
                .constrainAs(imgLogin) {
                    top.linkTo(parent.top, margin = 32.dp)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                }
                .size(100.dp)
        )
        Text(
            text = stringResource(R.string.txt_login_title),
            modifier = Modifier.constrainAs(txtLogin) {
                top.linkTo(imgLogin.bottom, margin = 32.dp)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
            })
    }
}

@Composable
fun BodyLogin(navController: NavController, viewModel: LoginViewModel) {
    //observeasstate

    val emailValue: String by viewModel.email.observeAsState(initial = "")
    val passwordValue: String by viewModel.password.observeAsState(initial = "")
    val isLogin: Boolean by viewModel.isLogin.observeAsState(initial = false)
    val isError: Boolean by viewModel.isError.observeAsState(initial = false)

    val context: Context = LocalContext.current

    if (isLogin) {
        Toast.makeText(context, "Iniciando sesion...", Toast.LENGTH_SHORT).show()
        viewModel.onIsLoginChange(false)
        val intent = Intent(context, MainActivity::class.java)
        context.startActivity(intent)
        (context as Activity).finish()
    } else if (isError) {
        Toast.makeText(context, "Error", Toast.LENGTH_SHORT).show()
        viewModel.onIsErrorChange(false)
    }
    ConstraintLayout(modifier = Modifier.fillMaxSize()) {
        val (tfEmail, tfPass, btnLogin) = createRefs()

        TextField(value = emailValue,
            onValueChange = { viewModel.onEmailChanged(it) },
            modifier = Modifier
                .fillMaxWidth()
                .constrainAs(tfEmail) {
                    top.linkTo(parent.top)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                }, colors = TextFieldDefaults.colors(unfocusedContainerColor = Color.Transparent),
            placeholder = { Text(text = stringResource(R.string.txt_placeholder_email_login)) })

        TextField(
            value = passwordValue,
            onValueChange = { viewModel.onPasswordChanged(it) },
            leadingIcon = {
                Icon(imageVector = Icons.Filled.Lock, contentDescription = "lock password")
            },
            trailingIcon = {
                Icon(imageVector = Icons.Filled.Lock, contentDescription = "lock password")
            },
            modifier = Modifier
                .fillMaxWidth()
                .constrainAs(tfPass) {
                    top.linkTo(tfEmail.bottom, margin = 20.dp)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                }, colors = TextFieldDefaults.colors(unfocusedContainerColor = Color.Transparent),
            placeholder = { Text(text = stringResource(R.string.txt_placeholder_password_login)) },
            visualTransformation = PasswordVisualTransformation()
        )

        Button(onClick = {
            //navigate to registerScreen
            //navController.navigate(StoreAppDestinations.RegisterDestination.route)
            viewModel.verifyLogin()
            viewModel.isLogin.value?.let { viewModel.onIsErrorChange(!it) }
                ?: viewModel.onIsErrorChange(true)
        }, modifier = Modifier.constrainAs(btnLogin) {
            top.linkTo(tfPass.bottom, margin = 20.dp)
            start.linkTo(parent.start)
            end.linkTo(parent.end)
        }) {
            Text(text = stringResource(R.string.txt_login_title))
        }

    }

}


@Preview(showBackground = true)
@Composable
fun FooterLogin() {
    //TODO: 20240727 PENDING FINISH LINK
    ConstraintLayout(
        modifier = Modifier
            .fillMaxWidth()
            .height(100.dp)
    ) {
        ConstraintLayout() {

        }
        val (footerContainer) = createRefs()

        Box(
            modifier = Modifier
                .constrainAs(footerContainer) {
                    bottom.linkTo(parent.bottom)
                }
                .fillMaxWidth()
                .height(100.dp)
                .background(color = Color.Red)
        ) {

        }
    }

}








package co.edu.unab.etdm.cg.storeapp.login.ui.screen

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.widget.Toast
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Button
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ChainStyle
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import co.edu.unab.etdm.cg.storeapp.R
import co.edu.unab.etdm.cg.storeapp.StoreAppDestinations
import co.edu.unab.etdm.cg.storeapp.core.ui.activity.MainActivity
import co.edu.unab.etdm.cg.storeapp.login.ui.viewmodel.LoginViewModel
import coil.compose.AsyncImage

//@Preview(showBackground = true)
@Composable
fun LoginScreen(navController: NavHostController, viewModel: LoginViewModel) {
    //LoginViewModel
    Column(modifier = Modifier.fillMaxSize()) {
        HeaderScreen()
        BodyLogin(navController = navController, viewModel = viewModel)
        Spacer(modifier = Modifier.weight(1f))
        FooterLogin(navController = navController)
    }
}

@Composable
fun HeaderScreen() {
    ConstraintLayout(
        modifier = Modifier
            .fillMaxWidth()
            .border(BorderStroke(width = 2.dp, color = Color.Black))
    ) {
        val (imgLogin, txtLogin) = createRefs()
        AsyncImage(
            model = "https://unired.edu.co/images/instituciones/UNAB/2017/junio/unab.jpg",
            contentDescription = "Logo",
            contentScale = ContentScale.FillWidth,
            modifier = Modifier
                .constrainAs(imgLogin) {
                    top.linkTo(parent.top, margin = 32.dp)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                }
                .fillMaxWidth()
                .height(150.dp)
        )
        Text(
            text = stringResource(R.string.txt_login_title),
            fontSize = 30.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.constrainAs(txtLogin) {
                top.linkTo(imgLogin.bottom, margin = 16.dp)
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
    ConstraintLayout(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 32.dp)
    ) {
        val (tfEmail, tfPass, btnLogin) = createRefs()

        TextField(value = emailValue,
            onValueChange = { viewModel.onEmailChanged(it) },
            leadingIcon = {
                Icon(
                    imageVector = Icons.Filled.Person, contentDescription = "mail",
                    tint = Color.Black
                )
            },
            modifier = Modifier
                .fillMaxWidth()
                .constrainAs(tfEmail) {
                    top.linkTo(parent.top, margin = 64.dp)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                }, colors = TextFieldDefaults.colors(unfocusedContainerColor = Color.Transparent),
            placeholder = { Text(text = stringResource(R.string.txt_placeholder_email_login)) })

        TextField(
            value = passwordValue,
            onValueChange = { viewModel.onPasswordChanged(it) },
            leadingIcon = {
                Icon(
                    imageVector = Icons.Filled.Lock, contentDescription = "lock password",
                    tint = Color.Black
                )
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
            top.linkTo(tfPass.bottom, margin = 32.dp)
            start.linkTo(parent.start)
            end.linkTo(parent.end)
        }) {
            Text(text = stringResource(R.string.txt_login_title))
        }

    }

}


//@Preview(showBackground = true)
@Composable
fun FooterLogin(navController: NavController) {
    ConstraintLayout(
        modifier = Modifier
            .fillMaxWidth()
            .size(100.dp)
    ) {
        val (divider, textSignUp, linkSignUp) = createRefs()
        Divider(modifier = Modifier.constrainAs(divider) {
            top.linkTo(parent.top)
            start.linkTo(parent.start)
            end.linkTo(parent.end)
        })
        Text(text = "No tienes cuenta?", modifier = Modifier
            .constrainAs(textSignUp) {
                top.linkTo(divider.bottom)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
                bottom.linkTo(parent.bottom)
            }
            .padding(end = 8.dp))
        Text(
            text = "Registrate acá!!!",
            modifier = Modifier
                .constrainAs(linkSignUp)
                {
                    top.linkTo(divider.bottom)
                    start.linkTo(textSignUp.end)
                    end.linkTo(parent.end)
                    bottom.linkTo(parent.bottom)
                }
                .clickable { navController.navigate(StoreAppDestinations.RegisterDestination.route) },
            color = Color.Magenta,
            textDecoration = TextDecoration.Underline,
        )
        createHorizontalChain(textSignUp, linkSignUp, chainStyle = ChainStyle.Packed)
    }

}








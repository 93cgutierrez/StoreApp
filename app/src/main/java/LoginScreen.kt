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
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import co.edu.unab.etdm.eden.storeapp.R
import co.edu.unab.etdm.eden.storeapp.StoreAppDestinations
import coil.compose.AsyncImage

//@Preview(showBackground = true)
@Composable
fun LoginScreen(navController: NavHostController) {
    Column(modifier = Modifier.fillMaxSize()) {
        HeaderScreen()
        BodyLogin(navController = navController)
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
fun BodyLogin(navController: NavController) {
    ConstraintLayout(modifier = Modifier.fillMaxSize()) {
        val (tfEmail, tfPass, btnLogin) = createRefs()
        var emailValue: String by rememberSaveable {
            mutableStateOf("")
        }
        var passwordValue: String by rememberSaveable {
            mutableStateOf("")
        }
        TextField(value = emailValue,
            onValueChange = { emailValue = it },
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
            onValueChange = { passwordValue = it },
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
            navController.navigate(StoreAppDestinations.RegisterDestination.route)
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



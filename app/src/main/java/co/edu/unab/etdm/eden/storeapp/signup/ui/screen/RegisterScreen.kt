package co.edu.unab.etdm.eden.storeapp.signup.ui.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.navigation.NavController
import co.edu.unab.etdm.eden.storeapp.R
import co.edu.unab.etdm.eden.storeapp.signup.ui.viewmodel.SignUpViewModel
import coil.compose.AsyncImage

@Composable
fun RegisterScreen(navController: NavController,
                   signUpViewModel: SignUpViewModel
) {
    Column(modifier = Modifier.fillMaxSize()) {
        HeaderRegister()
        BodyRegister(navController)
    }
}


@Composable
fun BodyRegister(navController: NavController) {
    ConstraintLayout(modifier = Modifier.fillMaxSize()) {
        val (tfName, tfEmail, tfPass, btnLogin) = createRefs()
        TextField(value = "",
            onValueChange = {},
            modifier = Modifier
                .fillMaxWidth()
                .constrainAs(tfName) {
                    top.linkTo(parent.top)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                }, colors = TextFieldDefaults.colors(unfocusedContainerColor = Color.Transparent),
            placeholder = { Text(text = stringResource(R.string.txt_placeholder_email_login)) })
        TextField(value = "",
            onValueChange = {},
            modifier = Modifier
                .fillMaxWidth()
                .constrainAs(tfEmail) {
                    top.linkTo(parent.top)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                }, colors = TextFieldDefaults.colors(unfocusedContainerColor = Color.Transparent),
            placeholder = { Text(text = stringResource(R.string.txt_placeholder_email_login)) })

        /*        TextField(value = "",
                    onValueChange = {},
                    modifier = Modifier
                        .fillMaxWidth()
                        .constrainAs(tfPass) {
                            top.linkTo(tfEmail.bottom, margin = 20.dp)
                            start.linkTo(parent.start)
                            end.linkTo(parent.end)
                        }, colors = TextFieldDefaults.colors(unfocusedContainerColor = Color.Transparent),
                    placeholder = { Text(text = stringResource(R.string.txt_placeholder_password_login)) })*/

        /*        TextField(value = "",
                    onValueChange = {},
                    modifier = Modifier
                        .fillMaxWidth()
                        .constrainAs(tfPass) {
                            top.linkTo(tfEmail.bottom, margin = 20.dp)
                            start.linkTo(parent.start)
                            end.linkTo(parent.end)
                        }, colors = TextFieldDefaults.colors(unfocusedContainerColor = Color.Transparent),
                    placeholder = { Text(text = stringResource(R.string.txt_register_confirm_password)) })*/

        Button(onClick = {
            navController.popBackStack()
        }, modifier = Modifier.constrainAs(btnLogin) {
            top.linkTo(tfPass.bottom, margin = 20.dp)
            start.linkTo(parent.start)
            end.linkTo(parent.end)
        }) {
            Text(text = stringResource(R.string.txt_btn_register))
        }

    }

}

@Composable
fun HeaderRegister() {
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
            text = stringResource(R.string.txt_register_tittle),
            modifier = Modifier.constrainAs(txtLogin) {
                top.linkTo(imgLogin.bottom, margin = 32.dp)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
            })
    }
}

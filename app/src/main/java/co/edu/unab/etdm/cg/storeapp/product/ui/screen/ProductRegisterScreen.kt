package co.edu.unab.etdm.cg.storeapp.product.ui.screen

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedButton
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
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ChainStyle
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import androidx.navigation.NavController
import co.edu.unab.etdm.cg.storeapp.product.ui.viewmodel.ProductRegisterViewModel
import coil.compose.AsyncImage

@Composable
fun ProductRegisterScreen(
    modifier: Modifier,
    navController: NavController,
    viewModel: ProductRegisterViewModel
) {

    var name: String by rememberSaveable {

        mutableStateOf("")
    }
    var price: String by rememberSaveable {

        mutableStateOf("")
    }
    var description: String by rememberSaveable {

        mutableStateOf("")
    }

    var image: String by rememberSaveable {

        mutableStateOf("https://cdn-icons-png.freepik.com/256/12313/12313717.png?semt=ais_hybrid")
    }

    ConstraintLayout(
        modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp)
            .verticalScroll(
                rememberScrollState()
            )
    ) {
        val (imgProduct, tfName, tfPrice, tfDescription, tfImage, btnAdd, btnCancel) = createRefs()
        AsyncImage(
            model = image, contentDescription = name, modifier = Modifier
                .size(100.dp)
                .constrainAs(imgProduct) {
                    top.linkTo(parent.top, margin = 32.dp)
                    start.linkTo(parent.start, margin = 16.dp)
                    end.linkTo(parent.end, margin = 16.dp)

                }, contentScale = ContentScale.Crop
        )
        TextField(
            value = name,
            onValueChange = { productName -> name = productName },
            label = { Text(text = "Nombre:") },
            placeholder = { Text(text = "Nombre del producto") },
            modifier = Modifier
                .fillMaxWidth()
                .constrainAs(tfName) {
                    top.linkTo(imgProduct.bottom, margin = 32.dp)
                    start.linkTo(parent.start, margin = 16.dp)
                    end.linkTo(parent.end, margin = 16.dp)
                    width = Dimension.fillToConstraints
                },
            colors = TextFieldDefaults.colors(
                unfocusedContainerColor = Color.Transparent
            ),
            singleLine = true
        )
        TextField(
            value = price,
            onValueChange = { productPrice -> price = productPrice },
            label = { Text(text = "Precio:") },
            placeholder = { Text(text = "30000") },
            modifier = Modifier
                .fillMaxWidth()
                .constrainAs(tfPrice) {
                    top.linkTo(tfName.bottom, margin = 16.dp)
                    start.linkTo(parent.start, margin = 16.dp)
                    end.linkTo(parent.end, margin = 16.dp)
                    width = Dimension.fillToConstraints
                },
            colors = TextFieldDefaults.colors(
                unfocusedContainerColor = Color.Transparent
            ),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number), singleLine = true
        )
        TextField(
            value = description,
            onValueChange = { productDescription -> description = productDescription },
            label = { Text(text = "Descripción:") },
            placeholder = { Text(text = "Descripción del producto") },
            modifier = Modifier
                .fillMaxWidth()
                .constrainAs(tfDescription) {
                    top.linkTo(tfPrice.bottom, margin = 32.dp)
                    start.linkTo(parent.start, margin = 16.dp)
                    end.linkTo(parent.end, margin = 16.dp)
                    width = Dimension.fillToConstraints
                },
            colors = TextFieldDefaults.colors(
                unfocusedContainerColor = Color.Transparent
            ),
        )
        TextField(
            value = image,
            onValueChange = { productImageUrl -> image = productImageUrl },
            label = { Text(text = "URL Imagen:") },
            placeholder = { Text(text = "URL del producto") },
            modifier = Modifier
                .fillMaxWidth()
                .constrainAs(tfImage) {
                    top.linkTo(tfDescription.bottom, margin = 32.dp)
                    start.linkTo(parent.start, margin = 16.dp)
                    end.linkTo(parent.end, margin = 16.dp)
                    width = Dimension.fillToConstraints
                },
            colors = TextFieldDefaults.colors(
                unfocusedContainerColor = Color.Transparent
            ),
            singleLine = true
        )
        Button(
            onClick = {
                viewModel.addProduct(name, price, description, image)
                navController.popBackStack()
            },
            modifier = Modifier.constrainAs(btnAdd) {
                top.linkTo(tfImage.bottom, margin = 32.dp)
                start.linkTo(btnCancel.end, margin = 16.dp)
                end.linkTo(parent.end, margin = 16.dp)
                width = Dimension.fillToConstraints
            }) {
            Text(text = "Create product")
        }
        OutlinedButton(
            onClick = {
                navController.popBackStack()
            },
            modifier = Modifier
                .constrainAs(btnCancel) {
                    top.linkTo(tfImage.bottom, margin = 32.dp)
                    start.linkTo(parent.start, margin = 16.dp)
                    end.linkTo(btnAdd.start, margin = 16.dp)
                    width = Dimension.fillToConstraints
                }
                .padding(end = 8.dp)
        ) {
            Text(text = "Regresar")
        }
        createHorizontalChain(btnCancel, btnAdd, chainStyle = ChainStyle.Packed)
    }
}
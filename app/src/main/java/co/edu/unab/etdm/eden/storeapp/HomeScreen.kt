package co.edu.unab.etdm.eden.storeapp

import Product
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import coil.compose.AsyncImage

@Composable
fun HomeScreen(modifier: Modifier) {
    Box(modifier = modifier.fillMaxSize()) {
        Text(text = "Home Screen", Modifier.align(Alignment.Center))
    }
}

//productItem UI composable

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProductItem(product: Product) {
    Card(
        onClick = { },
        modifier = Modifier
            .fillMaxWidth()
            .size(200.dp),

        ) {
        ConstraintLayout(modifier = Modifier.fillMaxSize()) {
            val (imgProduct, nameProduct, priceProduct) = createRefs()
            AsyncImage(
                model = product.image,
                contentDescription = product.name,
                modifier = Modifier
                    .border(2.dp, Color.Black)
                    .size(100.dp)
                    .constrainAs(imgProduct) {
                        top.linkTo(parent.top)
                        start.linkTo(parent.start, 32.dp)
                        bottom.linkTo(parent.bottom)
                    })
            Text(
                text = product.name,
                modifier = Modifier.constrainAs(nameProduct) {
                    top.linkTo(imgProduct.top, margin = 8.dp)
                    start.linkTo(imgProduct.end, margin = 32.dp)
                    end.linkTo(parent.end)
                    bottom.linkTo(priceProduct.top)
                })
            Text(
                text = "$ ${product.price}",
                modifier = Modifier.constrainAs(priceProduct) {
                    bottom.linkTo(imgProduct.bottom, margin = 8.dp)
                    start.linkTo(imgProduct.end, margin = 32.dp)
                    end.linkTo(parent.end)
                    top.linkTo(nameProduct.bottom)
                })
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ProductList() {
    ProductItem(getFakeProducts().first())
}

//ProductList UI composable

//getFakeProducts
fun getFakeProducts(): List<Product> {
    return listOf(
        Product("keyboard", 300000),
        Product("Mouse", 200000),
        Product("Monitor", 500000),
        Product("Mouse Gaming", 350000),
        Product("laptop", 300000),
    )
}
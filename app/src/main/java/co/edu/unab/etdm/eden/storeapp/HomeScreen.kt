package co.edu.unab.etdm.eden.storeapp

import Product
import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.navigation.NavHostController
import coil.compose.SubcomposeAsyncImage
import coil.request.ImageRequest
import coil.size.Size

@Composable
fun HomeScreen(navController: NavHostController, modifier: Modifier) {
    val products = getFakeProducts()
    val context: Context = LocalContext.current
    LazyColumn(
        modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {

        items(products.size) { index ->
            ProductItem(product = products[index]) { productValue ->
                Toast.makeText(context, "$index Item: ${productValue}", Toast.LENGTH_SHORT).show()
                navController.navigate(StoreAppDestinations.ProfileDestination.route)
            }
        }
    }
}

//productItem UI composable

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProductItem(product: Product, onSelected: (Product) -> Unit) {
    val context: Context = LocalContext.current
    Card(
        onClick = {
            //Toast.makeText(context, product.name, Toast.LENGTH_SHORT).show()
            onSelected(product)
        },
        modifier = Modifier
            .fillMaxWidth()
            .size(200.dp),

        ) {
        ConstraintLayout(modifier = Modifier.fillMaxSize()) {
            val (imgProduct, nameProduct, priceProduct) = createRefs()
            SubcomposeAsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(product.image)
                    .size(Size.ORIGINAL) // Set the target size to load the image at.
                    .error(R.drawable.ic_error)
                    .build(),
                loading = {
                    CircularProgressIndicator()
                },
                contentDescription = product.name,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .border(2.dp, Color.Transparent)
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
    ProductItem(getFakeProducts().last()) {}
}

//ProductList UI composable

//getFakeProducts
fun getFakeProducts(): List<Product> {
    return listOf(
        Product(
            "keyboard",
            150000,
            "This is a good keyboard",
            "https://my-media.apjonlinecdn.com/catalog/product/cache/b3b166914d87ce343d4dc5ec5117b502/4/P/4P4F6AA-1_T1678954122.png",
        ),
        Product(
            "mouse pad",
            20000,
            "This is a good keyboard",
            "https://my-media.hhhhyyyyhhhhhhhhhhhhhhhhhhh",
        ),
        Product("Mouse", 200000),
        Product("Monitor", 500000),
        Product("Mouse Gaming", 350000),
        Product("laptop", 300000),
        Product(
            "power sources",
            450000,
            "This is better than normal power sources",
            "https://seasonic.com/wp-content/uploads/2024/02/a12-600-500-back-panel-angled-300x222.png",
        ),
    )
}
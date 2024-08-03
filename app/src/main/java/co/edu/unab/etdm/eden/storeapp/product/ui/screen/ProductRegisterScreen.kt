package co.edu.unab.etdm.eden.storeapp.product.ui.screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import co.edu.unab.etdm.eden.storeapp.product.ui.viewmodel.ProductDetailViewModel
import co.edu.unab.etdm.eden.storeapp.product.ui.viewmodel.ProductRegisterViewModel

@Composable
fun ProductRegisterScreen(
    navController: NavHostController,
    modifier: Modifier,
    viewModel: ProductRegisterViewModel
) {
    Box(
        modifier = modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text(text = "Register")
    }
}
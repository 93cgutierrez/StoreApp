package co.edu.unab.etdm.eden.storeapp.home.ui

import co.edu.unab.etdm.eden.storeapp.product.data.Product

sealed interface ProductListUIState {
    data object Loading : ProductListUIState
    data class Success(val products: List<Product>) : ProductListUIState
    data class Error(val error: Throwable) : ProductListUIState
}
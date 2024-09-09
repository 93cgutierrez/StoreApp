package co.edu.unab.etdm.cg.storeapp.home.ui

import co.edu.unab.etdm.cg.storeapp.product.data.Product

sealed interface ProductsUIState {
    //extras
    data object Idle : ProductsUIState
    data object Empty : ProductsUIState

    //commons
    data object Loading : ProductsUIState
    data class Success(val products: List<Product>) : ProductsUIState
    data class Error(val throwable: Throwable) : ProductsUIState
}
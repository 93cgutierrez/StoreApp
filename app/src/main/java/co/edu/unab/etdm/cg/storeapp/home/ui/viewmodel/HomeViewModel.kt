package co.edu.unab.etdm.cg.storeapp.home.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import co.edu.unab.etdm.cg.storeapp.home.domain.DeleteAllProductsUseCase
import co.edu.unab.etdm.cg.storeapp.home.domain.DeleteProductUseCase
import co.edu.unab.etdm.cg.storeapp.home.domain.GetProductsUseCase
import co.edu.unab.etdm.cg.storeapp.home.domain.SaveProductsUseCase
import co.edu.unab.etdm.cg.storeapp.home.ui.ProductsUIState
import co.edu.unab.etdm.cg.storeapp.home.ui.ProductsUIState.Success
import co.edu.unab.etdm.cg.storeapp.product.data.Product
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getProductsUseCase: GetProductsUseCase,
    private val savedProductUseCase: SaveProductsUseCase,
    private val deleteProductUseCase: DeleteProductUseCase,
    private val deleteAllProductsUseCase: DeleteAllProductsUseCase,
) : ViewModel() {

    val productList: Flow<List<Product>> = getProductsUseCase()

    val uiState: StateFlow<ProductsUIState> = getProductsUseCase().map(::Success)
        .catch { throwable ->
            ProductsUIState.Error(throwable)
        }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000),
            ProductsUIState.Loading)

    fun loadFakeProductList() {
        val products = listOf(
            /*            Product(
                            name =
                            "keyboard",
                            price =
                            150000,
                            description =
                            "This is a good keyboard",
                            image =
                            "https://my-media.apjonlinecdn.com/catalog/product/cache/b3b166914d87ce343d4dc5ec5117b502/4/P/4P4F6AA-1_T1678954122.png",
                        ),
                        Product(
                            name =
                            "mouse pad",
                            price =
                            20000,
                            description =
                            "This is a good keyboard",
                            image =
                            "https://my-media.hhhhyyyyhhhhhhhhhhhhhhhhhhh",
                        ),*/
            Product(name = "Mouse", price = 200000),
            Product(name = "Monitor", price = 500000),
            /*            Product(name = "Mouse Gaming", price = 350000),
                        Product(name = "laptop", price = 300000),
                        Product(name = "laptop 3", price = 600000),*/
            /*            Product(
                            name =
                            "power sources",
                            price =
                            450000,
                            description =
                            "This is better than normal power sources",
                            image =
                            "https://seasonic.com/wp-content/uploads/2024/02/a12-600-500-back-panel-angled-300x222.png",
                        ),*/
        )
        viewModelScope.launch(Dispatchers.IO) {
            savedProductUseCase(products)
        }
    }

    fun deleteProduct(product: Product) {
        viewModelScope.launch(Dispatchers.IO) {
            deleteProductUseCase(product)
        }
    }

    fun deleteAllProducts() {
        viewModelScope.launch(Dispatchers.IO) {
            deleteAllProductsUseCase()
        }
    }
}
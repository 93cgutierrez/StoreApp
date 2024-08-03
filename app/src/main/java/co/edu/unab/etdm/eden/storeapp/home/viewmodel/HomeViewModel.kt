package co.edu.unab.etdm.eden.storeapp.home.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import co.edu.unab.etdm.eden.storeapp.home.model.repository.HomeRepository
import co.edu.unab.etdm.eden.storeapp.product.model.Product
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val homeRepository: HomeRepository) : ViewModel() {
    val productList: LiveData<List<Product>> = homeRepository.products

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
            homeRepository.saveProducts(products)
        }
    }

    fun deleteProduct(product: Product) {
        viewModelScope.launch(Dispatchers.IO) {
            homeRepository.deleteProduct(product)
        }
    }

    fun deleteAllProducts() {
        viewModelScope.launch(Dispatchers.IO) {
            homeRepository.deleteAllProducts()
        }
    }
}
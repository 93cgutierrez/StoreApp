package co.edu.unab.etdm.eden.storeapp.product.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import co.edu.unab.etdm.eden.storeapp.product.model.Product
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProductDetailViewModel @Inject constructor(): ViewModel() {
    private val _product = MutableLiveData<Product>()
    val product: LiveData<Product> = _product

    //init
    init {
        //loadProduct(1)
    }

    fun loadProduct(productId: Int) {
        val products = loadFakeProductList()

        // Load product from API or database
        _product.value = products.find { it.id == productId }

    }

    private fun loadFakeProductList(): List<Product> {
        val products =  listOf(
            Product(
                1,
                "keyboard",
                150000,
                "This is a good keyboard",
                "https://my-media.apjonlinecdn.com/catalog/product/cache/b3b166914d87ce343d4dc5ec5117b502/4/P/4P4F6AA-1_T1678954122.png",
            ),
            Product(
                2,
                "mouse pad",
                20000,
                "This is a good keyboard",
                "https://my-media.hhhhyyyyhhhhhhhhhhhhhhhhhhh",
            ),
            Product(3, "Mouse", 200000),
            Product(4, "Monitor", 500000),
            Product(5, "Mouse Gaming", 350000),
            Product(6, "laptop", 300000),
            Product(
                7,
                "power sources",
                450000,
                "This is better than normal power sources",
                "https://seasonic.com/wp-content/uploads/2024/02/a12-600-500-back-panel-angled-300x222.png",
            ),
        )
        return products
    }
}
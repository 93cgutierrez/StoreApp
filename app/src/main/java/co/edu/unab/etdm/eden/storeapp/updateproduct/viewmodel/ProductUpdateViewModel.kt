package co.edu.unab.etdm.eden.storeapp.updateproduct.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import co.edu.unab.etdm.eden.storeapp.home.domain.DeleteProductUseCase
import co.edu.unab.etdm.eden.storeapp.home.domain.GetProductsUseCase
import co.edu.unab.etdm.eden.storeapp.product.data.Product
import co.edu.unab.etdm.eden.storeapp.updateproduct.domain.ProductUpdateUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProductUpdateViewModel @Inject constructor(
    private val updateProductUseCase: ProductUpdateUseCase
) : ViewModel() {

    private val _product = MutableLiveData<Product>()
    val product: LiveData<Product> = _product

    suspend fun updateProduct(id: Int, name: String, price: String, description: String, image: String) {
       val updateProduct: Product = Product(
            id = id,
            name = name,
            description = description,
            image = image,
            price = price.toInt(),
        )
        viewModelScope.launch(Dispatchers.IO) {
            updateProductUseCase(updateProduct)
        }
    }

    fun getProductById(id: Int): LiveData<Product>
    = updateProductUseCase.invoke(id)
}
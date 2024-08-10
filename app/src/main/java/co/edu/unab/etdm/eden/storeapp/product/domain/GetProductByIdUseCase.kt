package co.edu.unab.etdm.eden.storeapp.product.domain

import androidx.lifecycle.LiveData
import co.edu.unab.etdm.eden.storeapp.home.data.repository.HomeRepository
import co.edu.unab.etdm.eden.storeapp.product.data.Product
import co.edu.unab.etdm.eden.storeapp.product.data.repository.ProductDetailRepository
import javax.inject.Inject

class GetProductByIdUseCase @Inject constructor(
    private val productRepository: ProductDetailRepository
) {
    fun getProductById(id: Int): LiveData<Product> = getProductById(id)
}
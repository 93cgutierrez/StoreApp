package co.edu.unab.etdm.eden.storeapp.updateproduct.domain

import androidx.lifecycle.map
import co.edu.unab.etdm.eden.storeapp.home.data.repository.HomeRepository
import co.edu.unab.etdm.eden.storeapp.product.data.Product
import co.edu.unab.etdm.eden.storeapp.product.data.repository.ProductDetailRepository
import co.edu.unab.etdm.eden.storeapp.product.data.toProduct
import co.edu.unab.etdm.eden.storeapp.updateproduct.data.repository.UpdateProductRepository
import javax.inject.Inject

class ProductUpdateUseCase @Inject constructor(private val productRepository: UpdateProductRepository) {
    suspend operator fun invoke(product: Product) {
        productRepository.updateProduct(product)
    }

    //getProductById
    fun invoke(id: Int) = productRepository.getProductById(id).map { item -> item.toProduct() }
}
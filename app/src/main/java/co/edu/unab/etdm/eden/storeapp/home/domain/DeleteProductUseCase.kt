package co.edu.unab.etdm.eden.storeapp.home.domain

import co.edu.unab.etdm.eden.storeapp.home.data.repository.HomeRepository
import co.edu.unab.etdm.eden.storeapp.product.data.Product
import javax.inject.Inject

class DeleteProductUseCase @Inject constructor(private val homeRepository: HomeRepository) {
    suspend operator fun invoke(product: Product) = homeRepository.deleteProduct(product)
}
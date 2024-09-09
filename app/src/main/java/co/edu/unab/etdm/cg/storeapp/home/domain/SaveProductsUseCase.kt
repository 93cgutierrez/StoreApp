package co.edu.unab.etdm.cg.storeapp.home.domain

import co.edu.unab.etdm.cg.storeapp.home.data.repository.HomeRepository
import co.edu.unab.etdm.cg.storeapp.product.data.Product
import javax.inject.Inject

class SaveProductsUseCase @Inject constructor(private val homeRepository: HomeRepository) {
    suspend operator fun invoke(products: List<Product>) {
        homeRepository.saveProducts(products)
    }
}
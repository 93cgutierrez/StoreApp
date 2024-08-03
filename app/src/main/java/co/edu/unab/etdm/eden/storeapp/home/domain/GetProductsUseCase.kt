package co.edu.unab.etdm.eden.storeapp.home.domain

import androidx.lifecycle.LiveData
import co.edu.unab.etdm.eden.storeapp.home.data.repository.HomeRepository
import co.edu.unab.etdm.eden.storeapp.product.data.Product
import javax.inject.Inject

class GetProductsUseCase @Inject constructor(private val homeRepository: HomeRepository) {
    operator fun invoke(): LiveData<List<Product>> {
        return homeRepository.products
    }
}
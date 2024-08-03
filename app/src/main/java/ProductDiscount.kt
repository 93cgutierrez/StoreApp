import co.edu.unab.etdm.eden.storeapp.product.model.Product

class ProductDiscount(name: String, price: Int, var discount: Int) : Product(name, price) {
}
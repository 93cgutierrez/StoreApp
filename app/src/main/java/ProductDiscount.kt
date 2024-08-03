import co.edu.unab.etdm.eden.storeapp.product.model.Product

class ProductDiscount(id: Int, name: String, price: Int, var discount: Int) : Product(id, name, price) {
}
open class Product(var name: String, var price: Int, var description: String = "Sin descripción") {

    override fun toString(): String {
        return "name: $name, price: $price, description: $description"
    }

}
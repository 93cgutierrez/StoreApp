open class Product(
    var name: String,
    var price: Int,
    var description: String = "Sin descripción",
    var image: String = "https://www.libreriahuequito.com/public/images/productos/default.png"
) {

    override fun toString(): String {
        return "name: $name, price: $price, description: $description and image: $image"
    }

}
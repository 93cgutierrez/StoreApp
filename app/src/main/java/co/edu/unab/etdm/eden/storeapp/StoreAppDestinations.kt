package co.edu.unab.etdm.eden.storeapp

sealed class StoreAppDestinations(val title: String, val route: String) {
    data object LoginDestination : StoreAppDestinations("Login", "login")
    data object RegisterDestination : StoreAppDestinations("Register", "register")
    data object HomeDestination : StoreAppDestinations("Products", "home")
    data object ProfileDestination : StoreAppDestinations("My profile", "profile")
    data object ProductDetailDestination :
        StoreAppDestinations(
            "Product Details",
            "product-detail/{${NavArgs.ProductId.key}}"
        ) {
        fun createRoute(id: Int) = "product-detail/$id"
    }

    companion object {
        val mainScreens = listOf<StoreAppDestinations>(
            HomeDestination,
            ProfileDestination, ProductDetailDestination
        )
    }
}

//enum NavArgs
enum class NavArgs(val key: String) {
    ProductId("productId")
}
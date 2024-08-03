package co.edu.unab.etdm.eden.storeapp

sealed class StoreAppDestinations(val title: String, val route: String) {
    data object LoginDestination : StoreAppDestinations("Login", "login")
    data object RegisterDestination : StoreAppDestinations("Register", "register")
    data object HomeDestination : StoreAppDestinations("Products", "home")
    data object ProfileDestination : StoreAppDestinations("My profile", "profile")
    data object ProductDetailDestination : StoreAppDestinations("Product Details",
        "product_detail") {
        fun withProductId(productId: Int) = StoreAppDestinations
            .ProductDetailDestination("Product Details $productId", "product_detail_$productId")
    }

    companion object {
        val mainScreens = listOf<StoreAppDestinations>(HomeDestination, ProfileDestination)
    }
}
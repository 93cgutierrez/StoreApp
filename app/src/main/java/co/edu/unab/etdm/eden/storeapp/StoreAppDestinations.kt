package co.edu.unab.etdm.eden.storeapp

sealed class StoreAppDestinations(val title: String, val route: String) {
    data object LoginDestination : StoreAppDestinations("login", "login")
    data object RegisterDestination : StoreAppDestinations("register", "register")
}
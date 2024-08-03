package co.edu.unab.etdm.eden.storeapp.di

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class StoreAppApplication: Application() {
    // TODO: Add any application-level dependencies
    // Example: Database, network clients, etc.
    // Remember to add @Singleton if you want to share these dependencies across multiple modules
    // or add @Module to provide these dependencies to specific modules
    // In this case, since we don't have any specific modules, we're using Hilt's default behavior'

}
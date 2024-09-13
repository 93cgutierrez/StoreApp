package co.edu.unab.etdm.cg.storeapp.profile.ui

import co.edu.unab.etdm.cg.storeapp.core.ui.model.User

sealed interface ProfileUIState {
    //extras
    data object Idle : ProfileUIState
    data object Empty : ProfileUIState

    //commons
    data object Loading : ProfileUIState
    data class Success(val user: User) : ProfileUIState
    data class Error(val throwable: Throwable) : ProfileUIState
}
package co.edu.unab.etdm.cg.storeapp.user.ui

import co.edu.unab.etdm.cg.storeapp.core.ui.model.User

sealed interface UsersUIState {
    //extras
    data object Idle : UsersUIState
    data object Empty : UsersUIState

    //commons
    data object Loading : UsersUIState
    data class Success(val users: List<User>) : UsersUIState
    data class Error(val throwable: Throwable) : UsersUIState
}
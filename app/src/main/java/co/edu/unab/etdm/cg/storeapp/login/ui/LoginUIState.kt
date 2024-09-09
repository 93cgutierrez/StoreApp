package co.edu.unab.etdm.cg.storeapp.login.ui

sealed interface LoginUIState {
    //commons
    data object Default : LoginUIState
    data object Loading : LoginUIState
    data object Success : LoginUIState
    data object Error : LoginUIState
}
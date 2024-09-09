package co.edu.unab.etdm.cg.storeapp.core.data.network.entity

data class UserEntity(
    var id: String = "",
    var name: String,
    var document: Long,
    var email: String
)
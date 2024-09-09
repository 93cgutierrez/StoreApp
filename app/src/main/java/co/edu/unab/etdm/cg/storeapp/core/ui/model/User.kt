package co.edu.unab.etdm.cg.storeapp.core.ui.model

import co.edu.unab.etdm.cg.storeapp.core.data.network.entity.UserEntity

data class User(
    var id: String = "",
    var name: String,
    var document: Long,
    var email: String
)

fun User.toUserEntity(): UserEntity = UserEntity(
    id = this.id,
    name = this.name,
    document = this.document,
    email = this.email,
)

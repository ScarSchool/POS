package at.scarc.androidApp.models

data class User(
    var id: Long?,
    var userType: UserTypes?,
    var name: String?,
    var email: String?,
    var pwdToken: String?
) {
    override fun toString(): String = "$id - $name: $email"
}
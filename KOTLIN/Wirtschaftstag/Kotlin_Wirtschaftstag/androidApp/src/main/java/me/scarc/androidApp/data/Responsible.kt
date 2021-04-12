package me.scarc.androidApp.data

data class Responsible(
    var id: Int,
    var name: String,
    var pwdToken: String,
    var email: String,
    var mail: Mail,
    var responsibleList: List<Participation>
) : User(id, name, pwdToken, email) {

}

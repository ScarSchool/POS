package me.scarc.androidApp.data

data class Admin(
    var id: Int,
    var name: String,
    var pwdToken: String,
    var email: String,
    var mails: List<Mail>,
    var organizes: List<Event>
) : User(id, name, pwdToken, email) {

}
package me.scarc.androidApp.data

data class Pupil(
    var id: Int,
    var name: String,
    var pwdToken: String,
    var email: String,
    var joins: List<TimeSlot>

) : User(id, name, pwdToken, email) {
}
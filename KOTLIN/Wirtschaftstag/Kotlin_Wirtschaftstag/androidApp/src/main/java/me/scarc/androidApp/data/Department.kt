package me.scarc.androidApp.data

data class Department(
    var id: Int,
    var label: String,
    var participatesIn: List<Participation>
) {
}
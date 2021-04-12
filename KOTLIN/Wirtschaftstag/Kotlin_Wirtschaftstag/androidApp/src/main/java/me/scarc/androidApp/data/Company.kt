package me.scarc.androidApp

import me.scarc.androidApp.data.Participation

data class Company(
    var id: Int,
    var name: String,
    var zipTown: String,
    var street: String,
    var phone: String,
    var replyTo: String,
    var comments: String,
    var participatesIn: List<Participation>,
    var email: String,
) {
    override fun toString(): String {
        return "Company #$id\n$name in $zipTown, $street.\nContact: $replyTo\nEmail: $email\nPhone: $phone \n" +
                "Comments: $comments"
    }
}
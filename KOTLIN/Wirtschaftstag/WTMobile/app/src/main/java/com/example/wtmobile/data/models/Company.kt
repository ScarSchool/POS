package com.example.wtmobile.data.models

import com.example.wtmobile.data.models.Participation

data class Company(
    var id: Int,
    var name: String,
    var zipTown: String,
    var street: String,
    var phone: String,
    var replyTo: String,
    var comments: String,
    var email: String,
) {
    override fun toString(): String {
        return "Company #$id\n$name in $zipTown, $street.\nContact: $replyTo\nEmail: $email\nPhone: $phone \n" +
                "Comments: $comments"
    }
}
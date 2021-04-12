package me.scarc.androidApp

data class CompanyData(
    var id : Int,
    var name : String,
    var zipTown : String,
    var street : String,
    var phone : String,
    var replyTo : String,
    var comments : String,
    var participatesIn : List<Any>,
    var email : String,
) {
    override fun toString() : String {
        return "Company #$id\n$name in $zipTown, $street.\nContact: $replyTo\nEmail: $email\nPhone: $phone \n" +
                "Comments: $comments"
    }
}
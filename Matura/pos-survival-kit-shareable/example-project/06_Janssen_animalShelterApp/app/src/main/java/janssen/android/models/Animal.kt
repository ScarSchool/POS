package janssen.android.models

import java.time.LocalDate

class Animal {
    
    var id: Long = 0
    var name: String? = null
    var race: String? = null
    var dateOfBirth: LocalDate? = null
    var note: String? = null
    var gender: Gender? = Gender.Other
    var sold: Boolean? = false
    
    override fun toString(): String {
        return "Animal(id=$id)"
    }
    
}

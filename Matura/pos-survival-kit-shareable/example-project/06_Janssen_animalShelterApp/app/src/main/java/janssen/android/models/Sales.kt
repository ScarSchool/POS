package janssen.android.models

import java.time.LocalDate

class Sales {
    
    var id: Long = 0
    var shelter: AnimalShelter? = null
    var animal: Animal? = null
    var price: Double? = 0.0
    var date: LocalDate? = null
    
    override fun toString(): String {
        return "Sales(id=$id)"
    }
    
}

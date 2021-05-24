package janssen.android.models

class AnimalShelter {
    
    var address: String = "?"
    var animals: List<Animal> = emptyList()
    var phoneNumber: String? = null
    var sales: List<Sales> = emptyList()
    
    override fun toString(): String {
        return "AnimalShelter(address=$address)"
    }
    
}

package at.scarc.androidApp.models

data class Department(
    var id: Long?,
    var label: String?
) {
    override fun toString(): String = "$id - $label"
}
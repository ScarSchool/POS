package me.scarc.androidApp.data

import java.time.LocalDate
import java.util.*

data class Event(
    var id: Int,
    var date: Date,
    var label: String,
    var defaultPrice: Double,
    var organizer: Admin
) {
}
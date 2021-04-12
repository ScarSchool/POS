package me.scarc.androidApp.data

import me.scarc.androidApp.Company

class Participation(
    var id: Int,
    var price: Double,
    var paidAlready: Double,
    var comments: String,
    var eventAt: Event,
    var responsible: Responsible,
    var wantsToParticipate: Department,
    var company: Company,
    var timeSlots: List<TimeSlot>
) {

}

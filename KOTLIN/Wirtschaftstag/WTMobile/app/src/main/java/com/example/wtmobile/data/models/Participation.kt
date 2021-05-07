package com.example.wtmobile.data.models

import java.io.Serializable

class Participation(
    var id: Int,
    var price: Double,
    var paidAlready: Double,
    var comments: String,
    var event: Event,
    var responsible: Responsible,
    var interestedDepartments: List<Department>,
    var company: Company,
    var timeSlotOffers: List<TimeSlot>
) : Serializable

package com.example.wtmobile.data.models

import com.example.wtmobile.data.models.TimeSlot

data class Pupil(
    var id: Int,
    var name: String,
    var pwdToken: String,
    var email: String,
    var joins: List<TimeSlot>
)
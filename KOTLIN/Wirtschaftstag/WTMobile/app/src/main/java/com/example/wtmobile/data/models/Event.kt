package com.example.wtmobile.data.models

data class Event(
    var id: Int,
    var date: String,
    var label: String,
    var defaultPrice: Double,
    var organiser: User?
)
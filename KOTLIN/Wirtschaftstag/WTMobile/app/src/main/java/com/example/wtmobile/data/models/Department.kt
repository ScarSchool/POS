package com.example.wtmobile.data.models

import com.example.wtmobile.data.models.Participation

data class Department(
    var id: Int,
    var label: String,
    var participatesIn: List<Participation>
) {
}
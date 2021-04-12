package me.scarc.androidApp.data

import java.io.File
import java.time.LocalDate
import java.time.LocalTime
import java.util.*

data class Mail(
    var id: Int,
    var nr: Int,
    var date: LocalDate,
    var time: LocalTime,
    var subject: String,
    var content: String,
    var attachment1: File,
    var attachment2: File,
    var attachment3: File,
    var recipients: List<Responsible>,
    var sender: Admin
) {
}
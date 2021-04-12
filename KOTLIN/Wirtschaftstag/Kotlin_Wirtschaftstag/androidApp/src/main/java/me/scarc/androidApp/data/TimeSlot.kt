package me.scarc.androidApp.data

import java.time.LocalTime

class TimeSlot(
    var id: Int,

    var starts: LocalTime,
    var ends: LocalTime,
    var maxParticipants: Int,
    var participation: Participation,
    var pupils: List<Pupil>
) {

}

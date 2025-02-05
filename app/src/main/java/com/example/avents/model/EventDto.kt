package com.example.avents.model

data class Event(
    val name: String,
    val date: String,
    val location: String
)
val upComingEventList = listOf(
    Event("UpComing Music Concert", "Feb 20, 2025", "New York"),
    Event("UpComing Art Exhibition", "Mar 10, 2025", "Los Angeles"),
    Event("UpComing Tech Conference", "Apr 5, 2025", "San Francisco"),
)

val onGoingEventList = listOf(
    Event("OnGoing Music Concert", "Feb 20, 2025", "New York"),
    Event("OnGoing Art Exhibition", "Mar 10, 2025", "Los Angeles"),
    Event("OnGoing Tech Conference", "Apr 5, 2025", "San Francisco"),
)
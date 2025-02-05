package com.example.avents.view.home

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.avents.model.Event

@Composable
fun EventList(
    events: List<Event>,
    modifier: Modifier,
    onEventClick: (String) -> Unit
) {
    LazyColumn(
        modifier = Modifier
            .padding(bottom = 200.dp)
            .then(modifier)
    ) {
        items(events) { event ->
            EventCard(
                eventName = event.name,
                eventDate = event.date,
                eventLocation = event.location,
                onEventClick = onEventClick
            )
        }
    }
}
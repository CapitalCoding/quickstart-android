package br.com.diogomoreno.events.data.datasource.remote.event

import br.com.diogomoreno.events.domain.model.Event
import retrofit2.Call
import javax.inject.Inject

class EventRemoteDataSource @Inject constructor(private val eventService: EventService) {
    val events: Call<MutableList<Event?>?>
        get() = eventService.events

    fun getEvent(eventId: Int): Call<Event?>? {
        return eventService.getEvent(eventId)
    }

}
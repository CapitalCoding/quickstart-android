package br.com.diogomoreno.events.data.repositories

import br.com.diogomoreno.events.data.datasource.remote.event.EventRemoteDataSource
import br.com.diogomoreno.events.domain.model.Event
import retrofit2.Call
import javax.inject.Inject

class EventRepository @Inject constructor(private val eventRemoteDataSource: EventRemoteDataSource) {

    //usar wrapper
    val events: Call<MutableList<Event?>?>
        get() = eventRemoteDataSource.events

    fun getEvent(id: Int): Call<Event?>? {
        return eventRemoteDataSource.getEvent(id)
    } //search load etc...

}
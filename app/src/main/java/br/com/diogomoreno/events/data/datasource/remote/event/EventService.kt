package br.com.diogomoreno.events.data.datasource.remote.event

import br.com.diogomoreno.events.domain.model.Event
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface EventService {
    @GET("events/{id}")
    fun getEvent(@Path("id") eventId: Int): Call<Event?>?

    @get:GET("events")
    val events: Call<MutableList<Event?>?>
}
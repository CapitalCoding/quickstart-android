package br.com.diogomoreno.events.domain.usecase

import br.com.diogomoreno.events.data.repositories.EventRepository
import br.com.diogomoreno.events.domain.model.Event
import retrofit2.Call
import javax.inject.Inject

class ListEventsUseCase @Inject constructor(private val eventRepository: EventRepository) {
    operator fun invoke(): Call<MutableList<Event?>?> {
        return eventRepository.events
    }

}
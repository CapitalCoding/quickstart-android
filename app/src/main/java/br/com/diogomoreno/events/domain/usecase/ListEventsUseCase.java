package br.com.diogomoreno.events.domain.usecase;

import java.util.List;

import javax.inject.Inject;

import br.com.diogomoreno.events.domain.model.Event;
import br.com.diogomoreno.events.data.repositories.EventRepository;
import retrofit2.Call;

public class ListEventsUseCase {
    private final EventRepository eventRepository;

    @Inject
    public ListEventsUseCase(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }

    public Call<List<Event>> invoke(){
        return eventRepository.getEvents();
    }
}

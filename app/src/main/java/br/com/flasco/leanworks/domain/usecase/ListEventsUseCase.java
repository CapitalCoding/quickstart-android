package br.com.flasco.leanworks.domain.usecase;

import java.util.List;

import javax.inject.Inject;

import br.com.flasco.leanworks.data.repositories.EventRepository;
import br.com.flasco.leanworks.domain.model.Event;
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

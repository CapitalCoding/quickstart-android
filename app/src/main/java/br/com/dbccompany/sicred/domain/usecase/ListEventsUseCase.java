package br.com.dbccompany.sicred.domain.usecase;

import java.util.List;

import javax.inject.Inject;

import br.com.dbccompany.sicred.data.repositories.EventRepository;
import br.com.dbccompany.sicred.domain.model.Event;
import io.reactivex.Single;
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

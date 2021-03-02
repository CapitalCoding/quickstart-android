package br.com.diogomoreno.events.data.repositories;

import java.util.List;

import javax.inject.Inject;

import br.com.diogomoreno.events.data.datasource.remote.event.EventRemoteDataSource;
import br.com.diogomoreno.events.domain.model.Event;
import retrofit2.Call;

public class EventRepository {

    private EventRemoteDataSource eventRemoteDataSource;

    @Inject
    public EventRepository(EventRemoteDataSource eventRemoteDataSource) {
        this.eventRemoteDataSource = eventRemoteDataSource;
    }

    //usar wrapper
    public Call<List<Event>> getEvents() {
        return eventRemoteDataSource.getEvents();
    }

    public Call<Event> getEvent(int id) {
        return eventRemoteDataSource.getEvent(id);
    }

    //search load etc...
}

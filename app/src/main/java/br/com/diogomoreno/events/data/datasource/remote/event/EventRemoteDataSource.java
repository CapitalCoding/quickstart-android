package br.com.diogomoreno.events.data.datasource.remote.event;


import java.util.List;

import javax.inject.Inject;

import br.com.diogomoreno.events.domain.model.Event;
import retrofit2.Call;

public class EventRemoteDataSource {

    private final EventService eventService;

    @Inject
    public EventRemoteDataSource(EventService eventService) {
        this.eventService = eventService;
    }

    public Call<List<Event>> getEvents(){
        return eventService.getEvents();
    }

    public Call<Event> getEvent(int eventId){
        return eventService.getEvent(eventId);
    }
}
package br.com.flasco.leanworks.data.datasource.remote.event;

import java.util.List;

import br.com.flasco.leanworks.domain.model.Event;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface EventService {

    @GET("events/{id}")
    Call<Event> getEvent(@Path("id") int eventId);

    @GET("events")
    Call<List<Event>> getEvents();

}

package br.com.dbccompany.sicred.data.datasource.remote.event;

import java.util.List;

import br.com.dbccompany.sicred.domain.model.Event;
import io.reactivex.Single;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface EventService {

    @GET("events/{id}")
    Call<Event> getEvent(@Path("id") int eventId);

    @GET("events")
    Call<List<Event>> getEvents();

}

package br.com.dbccompany.sicred.data.repositories;

import androidx.lifecycle.MutableLiveData;

import java.util.List;

import javax.inject.Inject;

import br.com.dbccompany.sicred.data.datasource.remote.event.EventRemoteDataSource;
import br.com.dbccompany.sicred.domain.model.Event;
import io.reactivex.Single;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

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

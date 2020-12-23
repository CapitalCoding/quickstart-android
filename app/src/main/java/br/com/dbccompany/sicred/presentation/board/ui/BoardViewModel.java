package br.com.dbccompany.sicred.presentation.board.ui;

import android.provider.CalendarContract;
import android.util.Log;

import androidx.hilt.Assisted;
import androidx.hilt.lifecycle.ViewModelInject;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.SavedStateHandle;
import androidx.lifecycle.ViewModel;

import java.util.List;

import br.com.dbccompany.sicred.data.datasource.remote.event.EventRemoteDataSource;
import br.com.dbccompany.sicred.data.repositories.EventRepository;
import br.com.dbccompany.sicred.domain.model.Event;
import br.com.dbccompany.sicred.domain.usecase.ListEventsUseCase;
import io.reactivex.Emitter;
import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BoardViewModel extends ViewModel {
    private final EventRepository eventRepository;
    private final SavedStateHandle savedStateHandle;
    private final ListEventsUseCase listEventsUseCase;
    private MutableLiveData<List<Event>> eventsLiveData = new MutableLiveData<>();
    private MutableLiveData<Boolean> showError = new MutableLiveData<>();
    private MutableLiveData<Boolean> showProgress = new MutableLiveData<>();
    private MutableLiveData<Boolean> showContent = new MutableLiveData<>();

    @ViewModelInject
    public BoardViewModel(EventRepository eventRepository,
                          @Assisted SavedStateHandle savedStateHandle,
                          ListEventsUseCase listEventsUseCase
    ) {
        this.eventRepository = eventRepository;
        this.savedStateHandle = savedStateHandle;
        this.listEventsUseCase = listEventsUseCase;
        loadEvents();
    }

    private static final String TAG = "BoardViewModel";
    public void loadEvents() {
        setLoadState();
        handleResult(listEventsUseCase.invoke());
        Log.d(TAG, "loadEvents: ");
    }
    public void setLoadState(){
        showProgress.postValue(true);
        showError.postValue(false);
        showContent.postValue(false);
    }
    public void handleResult(Call<List<Event>> result){
         eventRepository.getEvents().enqueue(new Callback<List<Event>>() {
            @Override
            public void onResponse(Call<List<Event>> call, Response<List<Event>> response) {
                setContentState(response.body());
            }

            @Override
            public void onFailure(Call<List<Event>> call, Throwable t) {
                setErrorState();
            }
        });
    }


    public void setContentState(List<Event> eventList){
        showContent.postValue(true);
        eventsLiveData.postValue(eventList);
        showProgress.postValue(false);
        showError.postValue(false);
    }

    public void setErrorState(){
        showError.postValue(true);
        showProgress.postValue(false);
        showContent.postValue(false);
    }

    public EventRepository getEventRepository() {
        return eventRepository;
    }

    public SavedStateHandle getSavedStateHandle() {
        return savedStateHandle;
    }

    public ListEventsUseCase getListEventsUseCase() {
        return listEventsUseCase;
    }

    public MutableLiveData<List<Event>> getEventsLiveData() {
        return eventsLiveData;
    }

    public void setEventsLiveData(MutableLiveData<List<Event>> eventsLiveData) {
        this.eventsLiveData = eventsLiveData;
    }

    public MutableLiveData<Boolean> getShowError() {
        return showError;
    }

    public void setShowError(MutableLiveData<Boolean> showError) {
        this.showError = showError;
    }

    public MutableLiveData<Boolean> getShowProgress() {
        return showProgress;
    }

    public void setShowProgress(MutableLiveData<Boolean> showProgress) {
        this.showProgress = showProgress;
    }

    public MutableLiveData<Boolean> getShowContent() {
        return showContent;
    }

    public void setShowContent(MutableLiveData<Boolean> showContent) {
        this.showContent = showContent;
    }
}
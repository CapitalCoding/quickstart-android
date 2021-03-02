package br.com.diogomoreno.events.presentation.event.ui;

import androidx.hilt.Assisted;
import androidx.hilt.lifecycle.ViewModelInject;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.SavedStateHandle;
import androidx.lifecycle.ViewModel;

import br.com.diogomoreno.events.data.repositories.EventRepository;
import br.com.diogomoreno.events.domain.model.Event;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EventDetailViewModel extends ViewModel {
    private final EventRepository eventRepository;
    private MutableLiveData<Boolean> showProgress = new MutableLiveData<>(true);
    private MutableLiveData<Boolean> showError = new MutableLiveData<>();
    private MutableLiveData<Boolean> showContent = new MutableLiveData<>();

    @ViewModelInject
    public EventDetailViewModel(EventRepository eventRepository,
                                @Assisted SavedStateHandle savedStateHandle
                                ) {
        this.eventRepository = eventRepository;
    }

    public MutableLiveData<Event> eventMutableLiveData = new MutableLiveData<>();
    public boolean isChecked;


    public void setEventId(int eventId) {
        eventRepository.getEvent(eventId).enqueue(new Callback<Event>() {
            @Override
            public void onResponse(Call<Event> call, Response<Event> response) {
                setContentState(response.body());
            }

            @Override
            public void onFailure(Call<Event> call, Throwable t) {
                setErrorState();
            }
        });
    }

    public void setErrorState(){
        showError.postValue(true);
        showProgress.postValue(false);
        showContent.postValue(false);
    }
    private void setContentState(Event body) {
        showContent.postValue(true);
        eventMutableLiveData.postValue(body);
        showProgress.postValue(false);
        showError.postValue(false);
    }
    public void setLoadState(){
        showProgress.postValue(true);
        showError.postValue(false);
        showContent.postValue(false);
    }

    public MutableLiveData<Event> getEventMutableLiveData() {
        return eventMutableLiveData;
    }

    public void setEventMutableLiveData(MutableLiveData<Event> eventMutableLiveData) {
        this.eventMutableLiveData = eventMutableLiveData;
    }

    public boolean isChecked() {
        return isChecked;
    }

    public void setChecked(boolean checked) {
        isChecked = checked;
    }

    public EventRepository getEventRepository() {
        return eventRepository;
    }

    public MutableLiveData<Boolean> getShowProgress() {
        return showProgress;
    }

    public void setShowProgress(MutableLiveData<Boolean> showProgress) {
        this.showProgress = showProgress;
    }

    public MutableLiveData<Boolean> getShowError() {
        return showError;
    }

    public void setShowError(MutableLiveData<Boolean> showError) {
        this.showError = showError;
    }

    public MutableLiveData<Boolean> getShowContent() {
        return showContent;
    }

    public void setShowContent(MutableLiveData<Boolean> showContent) {
        this.showContent = showContent;
    }
}
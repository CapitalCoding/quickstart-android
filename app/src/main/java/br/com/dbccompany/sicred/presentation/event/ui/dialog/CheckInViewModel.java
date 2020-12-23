package br.com.dbccompany.sicred.presentation.event.ui.dialog;

import androidx.hilt.Assisted;
import androidx.hilt.lifecycle.ViewModelInject;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.SavedStateHandle;
import androidx.lifecycle.ViewModel;

import br.com.dbccompany.sicred.data.datasource.remote.checkin.CheckInResponse;
import br.com.dbccompany.sicred.data.repositories.EventRepository;
import br.com.dbccompany.sicred.domain.model.CheckIn;
import br.com.dbccompany.sicred.domain.model.Event;
import br.com.dbccompany.sicred.domain.usecase.RealizeCheckinUseCase;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CheckInViewModel extends ViewModel {

    private final RealizeCheckinUseCase realizeCheckInUseCase;
    private final SavedStateHandle savedStateHandle;
    private final EventRepository eventRepository;
    private MutableLiveData<Boolean> showProgress = new MutableLiveData<>();
    private MutableLiveData<Boolean> showError = new MutableLiveData<>();
    private MutableLiveData<Boolean> showContent = new MutableLiveData<>();
    private String eventId;
    private MutableLiveData<Boolean> showSuccess = new MutableLiveData<>();

    @ViewModelInject
    public CheckInViewModel(EventRepository eventRepository,
                            @Assisted SavedStateHandle savedStateHandle,
                            RealizeCheckinUseCase realizeCheckinUseCase) {
        this.eventRepository = eventRepository;
        this.savedStateHandle = savedStateHandle;
        this.realizeCheckInUseCase = realizeCheckinUseCase;
    }

    public void setErrorState(){
        showError.postValue(true);
        showProgress.postValue(false);
        showContent.postValue(false);
    }
    private void setShowSuccess() {
        showContent.postValue(true);
        showSuccess.postValue(true);
        showProgress.postValue(false);
        showError.postValue(false);
    }
    public void setLoadState(){
        showProgress.postValue(true);
        showError.postValue(false);
        showContent.postValue(false);
    }

    public void checkInAction(String name, String email, String eventId) {
        setLoadState();
        realizeCheckInUseCase.invoke(new CheckIn(eventId, name, email))
                .enqueue(new Callback<CheckInResponse>() {
            @Override
            public void onResponse(Call<CheckInResponse> call, Response<CheckInResponse> response) {
                    setShowSuccess();
            }

            @Override
            public void onFailure(Call<CheckInResponse> call, Throwable t) {
                setErrorState();
            }
        });
    }

    public void setEventId(String eventId) {
        this.eventId = eventId;
    }

    public String getEventId() {
        return eventId;
    }

    public RealizeCheckinUseCase getRealizeCheckInUseCase() {
        return realizeCheckInUseCase;
    }

    public SavedStateHandle getSavedStateHandle() {
        return savedStateHandle;
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

    public MutableLiveData<Boolean> getShowSuccess() {
        return showSuccess;
    }

    public void setShowSuccess(MutableLiveData<Boolean> showSuccess) {
        this.showSuccess = showSuccess;
    }
}

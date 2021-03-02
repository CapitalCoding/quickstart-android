package br.com.diogomoreno.events.presentation.board.ui

import android.util.Log
import androidx.hilt.Assisted
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import br.com.diogomoreno.events.data.repositories.EventRepository
import br.com.diogomoreno.events.domain.model.Event
import br.com.diogomoreno.events.domain.usecase.ListEventsUseCase
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class BoardViewModel @ViewModelInject constructor(val eventRepository: EventRepository,
                                                  @param:Assisted val savedStateHandle: SavedStateHandle,
                                                  val listEventsUseCase: ListEventsUseCase
) : ViewModel() {
    var eventsLiveData = MutableLiveData<MutableList<Event?>?>()
    var showError = MutableLiveData<Boolean>()
    var showProgress = MutableLiveData<Boolean?>()
    var showContent = MutableLiveData<Boolean>()
    fun loadEvents() {
        setLoadState()
        handleResult(listEventsUseCase.invoke())
        Log.d(TAG, "loadEvents: ")
    }

    fun setLoadState() {
        showProgress.postValue(true)
        showError.postValue(false)
        showContent.postValue(false)
    }

    fun handleResult(result: Call<MutableList<Event?>?>) {
        eventRepository.events?.enqueue(object : Callback<MutableList<Event?>?> {
            override fun onFailure(call: Call<MutableList<Event?>?>, t: Throwable) {
                setErrorState()
            }

            override fun onResponse(call: Call<MutableList<Event?>?>, response: Response<MutableList<Event?>?>) {
                setContentState(response.body())
            }


        })
    }

    fun setContentState(eventList: MutableList<Event?>?) {
        showContent.postValue(true)
        eventsLiveData.postValue(eventList)
        showProgress.postValue(false)
        showError.postValue(false)
    }

    fun setErrorState() {
        showError.postValue(true)
        showProgress.postValue(false)
        showContent.postValue(false)
    }

    companion object {
        private const val TAG = "BoardViewModel"
    }

    init {
        loadEvents()
    }
}
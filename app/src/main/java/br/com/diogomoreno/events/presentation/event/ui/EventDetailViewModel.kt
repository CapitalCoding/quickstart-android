package br.com.diogomoreno.events.presentation.event.ui

import androidx.hilt.Assisted
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import br.com.diogomoreno.events.data.repositories.EventRepository
import br.com.diogomoreno.events.domain.model.Event
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class EventDetailViewModel @ViewModelInject constructor(val eventRepository: EventRepository,
                                                        @Assisted savedStateHandle: SavedStateHandle?
) : ViewModel() {
    var showProgress = MutableLiveData(true)
    var showError = MutableLiveData<Boolean>()
    var showContent = MutableLiveData<Boolean>()
    var eventMutableLiveData = MutableLiveData<Event?>()
    var isChecked = false
    fun setEventId(eventId: Int) {
        eventRepository.getEvent(eventId)!!.enqueue(object : Callback<Event?> {
            override fun onResponse(call: Call<Event?>, response: Response<Event?>) {
                setContentState(response.body())
            }

            override fun onFailure(call: Call<Event?>, t: Throwable) {
                setErrorState()
            }
        })
    }

    fun setErrorState() {
        showError.postValue(true)
        showProgress.postValue(false)
        showContent.postValue(false)
    }

    private fun setContentState(body: Event?) {
        showContent.postValue(true)
        eventMutableLiveData.postValue(body)
        showProgress.postValue(false)
        showError.postValue(false)
    }

    fun setLoadState() {
        showProgress.postValue(true)
        showError.postValue(false)
        showContent.postValue(false)
    }

}
package br.com.diogomoreno.events.presentation.event.ui.dialog

import androidx.hilt.Assisted
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import br.com.diogomoreno.events.data.datasource.remote.checkin.CheckInResponse
import br.com.diogomoreno.events.data.repositories.EventRepository
import br.com.diogomoreno.events.domain.model.CheckIn
import br.com.diogomoreno.events.domain.usecase.RealizeCheckinUseCase
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CheckInViewModel @ViewModelInject constructor(val eventRepository: EventRepository,
                                                    @param:Assisted val savedStateHandle: SavedStateHandle,
                                                    val realizeCheckInUseCase: RealizeCheckinUseCase) : ViewModel() {
    var showProgress = MutableLiveData<Boolean>()
    var showError = MutableLiveData<Boolean>()
    var showContent = MutableLiveData<Boolean>()
    var eventId: String? = null
    var showSuccess = MutableLiveData<Boolean>()
    fun setErrorState() {
        showError.postValue(true)
        showProgress.postValue(false)
        showContent.postValue(false)
    }

    private fun setShowSuccess() {
        showContent.postValue(true)
        showSuccess.postValue(true)
        showProgress.postValue(false)
        showError.postValue(false)
    }

    fun setLoadState() {
        showProgress.postValue(true)
        showError.postValue(false)
        showContent.postValue(false)
    }

    fun checkInAction(name: String?, email: String?, eventId: String?) {
        setLoadState()
        realizeCheckInUseCase.invoke(CheckIn(eventId, name, email))
                ?.enqueue(object : Callback<CheckInResponse?> {
                    override fun onResponse(call: Call<CheckInResponse?>, response: Response<CheckInResponse?>) {
                        setShowSuccess()
                    }

                    override fun onFailure(call: Call<CheckInResponse?>, t: Throwable) {
                        setErrorState()
                    }
                })
    }

}
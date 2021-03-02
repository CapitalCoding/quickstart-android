package br.com.diogomoreno.events.data.repositories

import br.com.diogomoreno.events.data.datasource.remote.checkin.CheckInRemoteDataSource
import br.com.diogomoreno.events.data.datasource.remote.checkin.CheckInResponse
import br.com.diogomoreno.events.domain.model.CheckIn
import retrofit2.Call
import javax.inject.Inject

class CheckInRepository @Inject constructor(private val checkInRemoteDataSource: CheckInRemoteDataSource) {
    fun postCheckIn(checkIn: CheckIn?): Call<CheckInResponse?>? {
        return checkInRemoteDataSource.saveCheckIn(checkIn)

//                .enqueue(new Callback<CheckInResponse>() {
//            @Override
//            public void onResponse(Call<CheckInResponse> call, Response<CheckInResponse> response) {
//                if(response.code() == 200){
//                    listEventMutableLiveData.setValue(HttpResponse.Success(response.body(), response.message()));
//                }else{
//                    listEventMutableLiveData.setValue(HttpResponse.Failure(response.code(), response.body(), response.message()));
//                }
//            }
//
//            @Override
//            public void onFailure(Call<CheckInResponse> call, Throwable t) {
//                listEventMutableLiveData.setValue(HttpResponse.Failure(-1, null, t.getMessage()));
//            }
//        });
    } //search load etc...

}
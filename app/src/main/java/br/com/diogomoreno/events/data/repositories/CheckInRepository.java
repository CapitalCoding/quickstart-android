package br.com.diogomoreno.events.data.repositories;

import javax.inject.Inject;

import br.com.diogomoreno.events.data.datasource.remote.checkin.CheckInRemoteDataSource;
import br.com.diogomoreno.events.data.datasource.remote.checkin.CheckInResponse;
import br.com.diogomoreno.events.domain.model.CheckIn;
import retrofit2.Call;

public class CheckInRepository {

    private CheckInRemoteDataSource checkInRemoteDataSource;

    @Inject
    public CheckInRepository(CheckInRemoteDataSource checkInRemoteDataSource) {
        this.checkInRemoteDataSource = checkInRemoteDataSource;
    }

    public Call<CheckInResponse> postCheckIn(CheckIn checkIn) {
        return checkInRemoteDataSource.saveCheckIn(checkIn);

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
    }

    //search load etc...
}

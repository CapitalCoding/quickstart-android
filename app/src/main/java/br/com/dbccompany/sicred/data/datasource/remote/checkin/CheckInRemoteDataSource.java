package br.com.dbccompany.sicred.data.datasource.remote.checkin;

import javax.inject.Inject;

import br.com.dbccompany.sicred.domain.model.CheckIn;
import io.reactivex.Single;
import retrofit2.Call;

public class CheckInRemoteDataSource {
    private final CheckInService checkInService;

    @Inject
    public CheckInRemoteDataSource(CheckInService checkInService) {
        this.checkInService = checkInService;
    }

    public Call<CheckInResponse> saveCheckIn(CheckIn checkIn){
        return checkInService.saveCheckIn(checkIn);
    }
}

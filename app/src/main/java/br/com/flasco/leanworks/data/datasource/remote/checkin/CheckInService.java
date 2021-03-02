package br.com.flasco.leanworks.data.datasource.remote.checkin;

import br.com.flasco.leanworks.domain.model.CheckIn;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface CheckInService {
    @POST("checkin/")
    Call<CheckInResponse> saveCheckIn(@Body CheckIn checkIn);
}

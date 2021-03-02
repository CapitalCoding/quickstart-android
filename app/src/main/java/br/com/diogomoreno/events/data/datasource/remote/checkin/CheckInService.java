package br.com.diogomoreno.events.data.datasource.remote.checkin;

import br.com.diogomoreno.events.domain.model.CheckIn;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface CheckInService {
    @POST("checkin/")
    Call<CheckInResponse> saveCheckIn(@Body CheckIn checkIn);
}

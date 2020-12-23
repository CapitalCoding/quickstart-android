package br.com.dbccompany.sicred.data.datasource.remote.checkin;

import br.com.dbccompany.sicred.domain.model.CheckIn;
import io.reactivex.Single;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface CheckInService {
    @POST("checkin/")
    Call<CheckInResponse> saveCheckIn(@Body CheckIn checkIn);
}

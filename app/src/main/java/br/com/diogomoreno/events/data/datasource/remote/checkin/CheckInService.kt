package br.com.diogomoreno.events.data.datasource.remote.checkin

import br.com.diogomoreno.events.domain.model.CheckIn
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface CheckInService {
    @POST("checkin/")
    fun saveCheckIn(@Body checkIn: CheckIn?): Call<CheckInResponse?>?
}
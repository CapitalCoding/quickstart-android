package br.com.diogomoreno.events.data.datasource.remote.checkin

import br.com.diogomoreno.events.domain.model.CheckIn
import retrofit2.Call
import javax.inject.Inject

class CheckInRemoteDataSource @Inject constructor(private val checkInService: CheckInService) {
    fun saveCheckIn(checkIn: CheckIn?): Call<CheckInResponse?>? {
        return checkInService.saveCheckIn(checkIn)
    }

}
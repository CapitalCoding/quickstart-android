package br.com.diogomoreno.events.domain.usecase

import br.com.diogomoreno.events.data.datasource.remote.checkin.CheckInResponse
import br.com.diogomoreno.events.data.repositories.CheckInRepository
import br.com.diogomoreno.events.domain.model.CheckIn
import retrofit2.Call
import javax.inject.Inject

class RealizeCheckinUseCase @Inject constructor(private val checkInRepository: CheckInRepository) {
    operator fun invoke(checkIn: CheckIn?): Call<CheckInResponse?>? {
        return checkInRepository.postCheckIn(checkIn)
    }

}
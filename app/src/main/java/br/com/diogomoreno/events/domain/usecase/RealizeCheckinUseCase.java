package br.com.diogomoreno.events.domain.usecase;

import javax.inject.Inject;

import br.com.diogomoreno.events.data.repositories.CheckInRepository;
import br.com.diogomoreno.events.domain.model.CheckIn;
import br.com.diogomoreno.events.data.datasource.remote.checkin.CheckInResponse;
import retrofit2.Call;

public class RealizeCheckinUseCase {
    private final CheckInRepository checkInRepository;

    @Inject
    public RealizeCheckinUseCase(CheckInRepository checkInRepository) {
        this.checkInRepository = checkInRepository;
    }

    public Call<CheckInResponse> invoke(CheckIn checkIn){
        return checkInRepository.postCheckIn(checkIn);
    }
}

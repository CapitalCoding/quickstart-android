package br.com.flasco.leanworks.domain.usecase;

import javax.inject.Inject;

import br.com.flasco.leanworks.data.datasource.remote.checkin.CheckInResponse;
import br.com.flasco.leanworks.data.repositories.CheckInRepository;
import br.com.flasco.leanworks.domain.model.CheckIn;
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

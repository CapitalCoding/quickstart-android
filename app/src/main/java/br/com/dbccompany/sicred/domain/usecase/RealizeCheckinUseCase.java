package br.com.dbccompany.sicred.domain.usecase;

import javax.inject.Inject;

import br.com.dbccompany.sicred.data.datasource.remote.checkin.CheckInResponse;
import br.com.dbccompany.sicred.data.repositories.CheckInRepository;
import br.com.dbccompany.sicred.domain.model.CheckIn;
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

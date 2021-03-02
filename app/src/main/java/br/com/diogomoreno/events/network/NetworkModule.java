package br.com.diogomoreno.events.network;

import br.com.flasco.network.Network;
import br.com.diogomoreno.events.data.datasource.remote.checkin.CheckInService;
import br.com.diogomoreno.events.data.datasource.remote.event.EventService;
import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.android.components.ApplicationComponent;
import retrofit2.Retrofit;

@Module
@InstallIn(ApplicationComponent.class)
public class NetworkModule {

    @Provides
    public static Retrofit providesRetrofit(){
        return new Network().
                createNetworkClient("http://5f5a8f24d44d640016169133.mockapi.io/api/",
                        true).build();
    }


    @Provides
    public static EventService provideEventService(Retrofit retrofit){
        return retrofit.create(EventService.class);
    }

    @Provides
    public static CheckInService provideCheckInService(Retrofit retrofit){
        return retrofit.create(CheckInService.class);
    }
}

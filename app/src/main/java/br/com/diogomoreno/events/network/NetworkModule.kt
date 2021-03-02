package br.com.diogomoreno.events.network

import br.com.diogomoreno.events.data.datasource.remote.checkin.CheckInService
import br.com.diogomoreno.events.data.datasource.remote.event.EventService
import br.com.flasco.network.Network
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import retrofit2.Retrofit

@Module
@InstallIn(ActivityComponent::class)
object NetworkModule {
    @JvmStatic
    @Provides
    fun providesRetrofit(): Retrofit {
        return Network().createNetworkClient("http://5f5a8f24d44d640016169133.mockapi.io/api/",
                true).build()
    }

    @JvmStatic
    @Provides
    fun provideEventService(retrofit: Retrofit): EventService {
        return retrofit.create(EventService::class.java)
    }

    @JvmStatic
    @Provides
    fun provideCheckInService(retrofit: Retrofit): CheckInService {
        return retrofit.create(CheckInService::class.java)
    }
}
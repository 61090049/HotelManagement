package com.example.dechproduct.hotelreservationapp.di

import android.content.SharedPreferences
import com.example.dechproduct.hotelreservationapp.data.api.PromotionAPIService
import com.example.dechproduct.hotelreservationapp.data.api.ReservationAPIService
import com.example.dechproduct.hotelreservationapp.data.api.RoomAPIService
import com.example.dechproduct.hotelreservationapp.data.api.UserAPIService
import com.example.dechproduct.hotelreservationapp.data.repository.InformationRepositoryImpl
import com.example.dechproduct.hotelreservationapp.data.repository.ReservationRepositoryImpl
import com.example.dechproduct.hotelreservationapp.data.repository.RoomRepositoryImpl
import com.example.dechproduct.hotelreservationapp.data.repository.UserRepositoryImpl
import com.example.dechproduct.hotelreservationapp.domain.repository.InformationRepository
import com.example.dechproduct.hotelreservationapp.domain.repository.ReservationRepository
import com.example.dechproduct.hotelreservationapp.domain.repository.RoomRepository
import com.example.dechproduct.hotelreservationapp.domain.repository.UserRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Singleton
    @Provides
    fun provideUserRepository(
        apiReference: UserAPIService,
        sharedPreferences: SharedPreferences,
    ): UserRepository {
        return UserRepositoryImpl(apiReference, sharedPreferences)
    }

    @Singleton
    @Provides
    fun provideReservationRepository(
        apiReference: ReservationAPIService,
        sharedPreferences: SharedPreferences,
    ): ReservationRepository {
        return ReservationRepositoryImpl(apiReference, sharedPreferences)
    }

    @Singleton
    @Provides
    fun provideRoomRepository(
        apiReference: RoomAPIService,
        sharedPreferences: SharedPreferences,
    ): RoomRepository {
        return RoomRepositoryImpl(apiReference, sharedPreferences)
    }

    @Singleton
    @Provides
    fun provideInformationRepository(
        apiReference: PromotionAPIService,
        sharedPreferences: SharedPreferences,
    ): InformationRepository {
        return InformationRepositoryImpl(apiReference, sharedPreferences)
    }
}
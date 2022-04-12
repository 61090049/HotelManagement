package com.example.dechproduct.hotelreservationapp.di

import com.example.dechproduct.hotelreservationapp.domain.repository.ReservationRepository
import com.example.dechproduct.hotelreservationapp.domain.repository.RoomRepository
import com.example.dechproduct.hotelreservationapp.domain.repository.UserRepository
import com.example.dechproduct.hotelreservationapp.domain.usecase.UseCase
import com.example.dechproduct.hotelreservationapp.domain.usecase.login.LoginUseCase
import com.example.dechproduct.hotelreservationapp.domain.usecase.reservation.*
import com.example.dechproduct.hotelreservationapp.domain.usecase.room.*

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object UseCaseModule {

    @Singleton
    @Provides
    fun provideUseCase(userRepository: UserRepository,
                       reservationRepository: ReservationRepository,
                       roomRepository: RoomRepository): UseCase {
        return UseCase(
            LoginUseCase(userRepository),

            AddReserveUseCase(reservationRepository),
            SearchReserveUseCase(reservationRepository),
            PopulateReserveUseCase(reservationRepository),
            EditReserveUseCase(reservationRepository),
            RemoveReserveUseCase(reservationRepository),

            SearchRoomUseCase(roomRepository),
            MarkRoomUseCase(roomRepository),
        )
    }
}
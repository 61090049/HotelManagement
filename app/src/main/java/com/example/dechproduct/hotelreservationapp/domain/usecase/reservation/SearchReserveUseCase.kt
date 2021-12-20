package com.example.dechproduct.hotelreservationapp.domain.usecase.reservation

import com.example.dechproduct.hotelreservationapp.data.model.Reservation
import com.example.dechproduct.hotelreservationapp.domain.repository.ReservationRepository
import com.example.dechproduct.hotelreservationapp.util.Resource
import javax.inject.Inject

class SearchReserveUseCase @Inject constructor(private val reservationRepository: ReservationRepository) {
    suspend operator fun invoke(keyword: String): Resource<MutableList<Reservation>> = reservationRepository.searchReservation(keyword)
}
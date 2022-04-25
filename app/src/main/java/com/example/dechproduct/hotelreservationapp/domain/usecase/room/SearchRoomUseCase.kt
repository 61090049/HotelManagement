package com.example.dechproduct.hotelreservationapp.domain.usecase.room

import com.example.dechproduct.hotelreservationapp.data.model.Room
import com.example.dechproduct.hotelreservationapp.data.model.utility.room.RoomStatus
import com.example.dechproduct.hotelreservationapp.domain.repository.RoomRepository
import com.example.dechproduct.hotelreservationapp.util.Resource
import javax.inject.Inject

class SearchRoomUseCase @Inject constructor(private val roomRepository: RoomRepository) {
    suspend operator fun invoke(keyword: String, arg: RoomStatus = RoomStatus.NONE): Resource<MutableList<Room>> =
        roomRepository.searchRoom(keyword, arg)
}
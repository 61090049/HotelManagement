package com.example.dechproduct.hotelreservationapp.domain.usecase.room

import com.example.dechproduct.hotelreservationapp.data.model.room.Room
import com.example.dechproduct.hotelreservationapp.domain.repository.RoomRepository
import com.example.dechproduct.hotelreservationapp.util.Resource
import javax.inject.Inject

class EditRoomUseCase @Inject constructor(private val roomRepository: RoomRepository) {
    suspend operator fun invoke(room: Room): Resource<Room> =
        roomRepository.editRoom(room)
}
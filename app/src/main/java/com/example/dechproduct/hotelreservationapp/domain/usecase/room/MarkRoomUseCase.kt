package com.example.dechproduct.hotelreservationapp.domain.usecase.room

import com.example.dechproduct.hotelreservationapp.data.model.Feature
import com.example.dechproduct.hotelreservationapp.data.model.Room
import com.example.dechproduct.hotelreservationapp.data.model.RoomStatus
import com.example.dechproduct.hotelreservationapp.domain.repository.RoomRepository
import com.example.dechproduct.hotelreservationapp.util.Resource
import javax.inject.Inject

class MarkRoomUseCase @Inject constructor(private val editRoomUseCase: EditRoomUseCase) {
    suspend operator fun invoke(room: Room, roomStatus: RoomStatus): Resource<Room> {
        room.roomStatus = roomStatus
        return editRoomUseCase(room)
    }
}
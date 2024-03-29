package com.example.dechproduct.hotelreservationapp.data.model.room

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
enum class RoomStatus(
    var displayName: String,

    var internalCode: String,

    ) : Parcelable {
    READY("Ready", "RS"),
    GUARANTEED("Guaranteed", "GR"),
    REQ_CLEAN("Request Cleaning", "NC"),
    OUT_ORDER("Out of Order", "NF"),
    OCCUPIED("Occupied", "U0"),
    LEAVING("Leaving Tomorrow", "EO"),
    INSPECTING("Inspecting", "WI"),
    SOF_CLEAN("Cleaning for Extensions", "UC"),
    NONE("Default", "NON");

    companion object {
        private fun getByInternalCode(key: String) = RoomStatus.values().find { it.internalCode == key }
        fun pack(roomStatus: RoomStatus): String = roomStatus.internalCode
        fun unpack(roomStatus: String): RoomStatus? = getByInternalCode(roomStatus)
    }
}
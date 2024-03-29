package com.example.dechproduct.hotelreservationapp.util

object Constants {
    //Utility
    const val CAPACITY_MAX_CHILD = 2

    //Target API
    const val API_ACCESS_BASE_URL =
        "http://10.0.2.2:3000/"
    const val API_ACCESS_INDEX_URL =
        "EmployeeLoginAPI/"
    const val API_ACCESS_KEY_ID = "accessID"
    const val API_ACCESS_KEY_USERNAME = "username"
    const val API_ACCESS_KEY_PASSWORD = "password"
    const val API_ACCESS_KEY_EMPLOYEE = "employee"

    const val API_ACCESS_KEY_EID = "employeeID"
    const val API_ACCESS_KEY_FNAME = "firstName"
    const val API_ACCESS_KEY_LNAME = "lastName"
    const val API_ACCESS_KEY_DEP = "department"
    const val API_ACCESS_KEY_POS = "position"

    const val API_BOOKING_BASE_URL =
        "http://10.0.2.2:3001/"
    const val API_BOOKING_INDEX_URL =
        "ReservationAPI/"
    const val API_BOOKING_KEY_ID = "bookingID"
    const val API_BOOKING_KEY_GUEST = "guest"
    const val API_BOOKING_KEY_TICKET = "ticket"
    const val API_BOOKING_KEY_ROOM = "room"
    const val API_BOOKING_KEY_PAYMENT = "payment"
    const val API_BOOKING_KEY_DATE_IN = "arrivalDate"
    const val API_BOOKING_KEY_DATE_OUT = "departDate"
    const val API_BOOKING_KEY_ADULT = "adultCount"
    const val API_BOOKING_KEY_CHILD = "childCount"
    const val API_BOOKING_KEY_STATUS = "status"
    const val API_BOOKING_KEY_BREAKFAST = "breakfast"
    const val API_BOOKING_KEY_ADDBED = "isAddonBed"

    const val API_ROOM_BASE_URL =
        "http://10.0.2.2:3002/"
    const val API_ROOM_INDEX_URL =
        "RoomAPI/"
    const val API_ROOM_KEY_ID = "roomID"
    const val API_ROOM_KEY_TYPE = "type"
    const val API_ROOM_KEY_BEDS = "beds"
    const val API_ROOM_KEY_CAP = "maxCap"
    const val API_ROOM_KEY_FLOOR = "floor"
    const val API_ROOM_KEY_FEATURES = "features"
    const val API_ROOM_KEY_ADDBED = "canAddonBed"
    const val API_ROOM_KEY_SMOKING = "smoking"
    const val API_ROOM_KEY_OCCUP = "occupancy"
    const val API_ROOM_KEY_WALKING = "isWalking"
    const val API_ROOM_KEY_DEVICES = "device"
    const val API_ROOM_KEY_PRICE = "price"

    const val API_ROOM_KEY_DATE_IN = "arrivalDate"
    const val API_ROOM_KEY_DATE_OUT = "departDate"
    const val API_ROOM_KEY_STATUS = "status"

    const val API_TICKET_BASE_URL =
        "http://10.0.2.2:3003/"
    const val API_TICKET_INDEX_URL =
        "GuestLoginAPI/"
    const val API_TICKET_KEY_ID = "ticketID"
    const val API_TICKET_KEY_USERNAME = "userName"
    const val API_TICKET_KEY_PASSWORD = "password"
    const val API_TICKET_KEY_GUEST = "guest"

    const val API_PROMOTION_BASE_URL =
        "http://10.0.2.2:3004/"
    const val API_PROMOTION_INDEX_URL =
        "PromotionAPI/"
    const val API_PROMOTION_KEY_ID = "promotionID"
    const val API_PROMOTION_KEY_TITLE = "title"
    const val API_PROMOTION_KEY_DESCRIPTION = "description"
    const val API_PROMOTION_KEY_PARTNER = "partners"
    const val API_PROMOTION_KEY_DISCOUNT = "discount"

    //Helper API
    const val API_GUEST_KEY_ID = "guestID"
    const val API_GUEST_KEY_FNAME = "firstName"
    const val API_GUEST_KEY_LNAME = "lastName"
    const val API_GUEST_KEY_PHONE = "phoneNumber"
    const val API_GUEST_KEY_ADDRESS = "address"
    const val API_GUEST_KEY_REGION = "region"
    const val API_GUEST_KEY_POSTAL = "postalCode"
    const val API_GUEST_KEY_COUNTRY = "country"
    const val API_GUEST_KEY_VERID = "verificationID"
    const val API_GUEST_KEY_VERPHOTO = "verificationPhoto"

    const val API_PAYMENT_KEY_ID = "paymentID"
    const val API_PAYMENT_KEY_TYPE = "type"
    const val API_PAYMENT_KEY_PHOTO = "photo"

    const val API_DEVICE_KEY_ID = "deviceID"
    const val API_DEVICE_KEY_NAME = "displayName"
    const val API_DEVICE_KEY_TYPE = "type"
    const val API_DEVICE_KEY_SERIAL = "serial"
    const val API_DEVICE_KEY_OEM = "manufacturer"
    const val API_DEVICE_KEY_STATUS = "status"

    //Maintenance API
    const val API_DEVICE_BASE_URL =
        "http://10.0.2.2:3005/"
    const val API_DEVICE_INDEX_URL =
        "MaintenanceAPI/"

    //Persistent Data
    const val SHARED_PREF = "USER_CONFIG"
    const val SHARED_PREF_SESSION = "SESSION_NAME"

    const val INTENT_SELECTED_BOOKING = "SELECTED_BOOKING"

    //Debug
    const val JSON_SERVER_FILTER = "_like"
}
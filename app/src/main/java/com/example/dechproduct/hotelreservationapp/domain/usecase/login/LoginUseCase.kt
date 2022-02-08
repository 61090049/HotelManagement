package com.example.dechproduct.hotelreservationapp.domain.usecase.login

import com.example.dechproduct.hotelreservationapp.data.model.Staff
import com.example.dechproduct.hotelreservationapp.util.Resource
import com.example.dechproduct.hotelreservationapp.domain.repository.UserRepository
import javax.inject.Inject

class LoginUseCase @Inject constructor(private val userRepository: UserRepository){
    suspend operator fun invoke(username:String, password:String): Resource<Staff> = userRepository.login(username, password)
}
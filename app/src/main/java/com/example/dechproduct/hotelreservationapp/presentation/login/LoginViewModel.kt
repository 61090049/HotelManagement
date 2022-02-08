package com.example.dechproduct.hotelreservationapp.presentation.login

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.dechproduct.hotelreservationapp.data.model.Staff
import com.example.dechproduct.hotelreservationapp.util.Resource
import com.example.dechproduct.hotelreservationapp.domain.usecase.UseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(private val useCase: UseCase) : ViewModel() {

    var activeUser = MutableLiveData<Resource<Staff>>()

    suspend fun loginUser(username:String, password:String){
        viewModelScope.launch {
            val currentUser = useCase.loginUseCase(username, password)
            activeUser.postValue(currentUser)
        }
    }

}
package com.shakiv.husain.kisannetwork.ui.viewmodel

import androidx.lifecycle.ViewModel
import com.shakiv.husain.kisannetwork.data.model.BaseResponse
import com.shakiv.husain.kisannetwork.data.model.Contact
import com.shakiv.husain.kisannetwork.data.network.Resource
import com.shakiv.husain.kisannetwork.data.repository.AuthRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class AuthViewModel @Inject constructor(private val authRepository: AuthRepository) : ViewModel() {

    fun sendSms(to:String, body:String): Flow<Resource<BaseResponse<String>>> {
        return authRepository.sendSms(to,body)
    }

}
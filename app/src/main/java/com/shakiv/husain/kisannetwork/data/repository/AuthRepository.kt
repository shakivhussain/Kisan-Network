package com.shakiv.husain.kisannetwork.data.repository

import com.shakiv.husain.kisannetwork.utils.AppConstants
import com.shakiv.husain.kisannetwork.data.api.AuthService
import com.shakiv.husain.kisannetwork.data.model.BaseResponse
import com.shakiv.husain.kisannetwork.data.network.ApiResponse
import com.shakiv.husain.kisannetwork.data.network.NetworkRequest
import com.shakiv.husain.kisannetwork.data.network.Resource
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class AuthRepository @Inject constructor(
    private val authService: AuthService
) {
    fun sendSms(to: String, body: String) = flow<Resource<BaseResponse<String>>> {
        emit(Resource.Loading())

        try {
            val data = NetworkRequest.process {
                authService.sendMessage(
                    from = AppConstants.TWILIO_NUMBER, to = to, body = body
                )
            }.run {
                when (this) {
                    is ApiResponse.Success -> {
                        results ?: throw Exception("Error in Sending SMS.")
                    }

                    is ApiResponse.Failure -> {
                        throw Exception("Error in Sending SMS.")
                    }
                }
            }
            emit(Resource.Success(data))
        } catch (e: Exception) {
            e.printStackTrace()
            emit(Resource.Failure(null, e.localizedMessage))
        }

    }



}
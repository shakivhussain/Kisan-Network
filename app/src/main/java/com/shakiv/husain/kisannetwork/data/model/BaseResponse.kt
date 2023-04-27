package com.shakiv.husain.kisannetwork.data.model

import com.google.gson.annotations.SerializedName

data class BaseResponse<T>(
    @SerializedName("results") var data: T?,
)
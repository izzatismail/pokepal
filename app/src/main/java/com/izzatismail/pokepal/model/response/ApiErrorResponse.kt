package com.izzatismail.pokepal.model.response

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ApiErrorResponse (
    @SerializedName("success")
    val successfulResponse: Boolean? = false,

    @SerializedName("status_code")
    val status_code: Int? = 0
    ,
    @SerializedName("status_message")
    val status_message: String? = ""
): Parcelable
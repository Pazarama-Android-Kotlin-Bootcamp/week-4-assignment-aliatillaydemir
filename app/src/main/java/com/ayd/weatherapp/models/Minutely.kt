package com.ayd.weatherapp.models


import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Minutely(
    @SerializedName("dt")
    val dt: Int?,
    @SerializedName("precipitation")
    val precipitation: Int?
): Parcelable
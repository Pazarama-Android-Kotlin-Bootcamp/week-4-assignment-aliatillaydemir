package com.ayd.weatherapp.models

import android.os.Parcelable
import com.google.gson.Gson
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize
import kotlinx.android.parcel.RawValue

@Parcelize                 //bu classlar parcelize çünkü bunları detay ekranıma göndereceğim. retrofit ile tek bir yerden datalar çekilir ve model classlarla istenilen aktivite ve fragmentlara bu bilgiler parcelize olarak aktarılır.
data class MainWeather(
    @SerializedName("current")
    val current: @RawValue Current?,   //bu classları da parcelize yap.
    @SerializedName("daily")
    val daily: @RawValue List<Daily>?,
    //@SerializedName("hourly")
    //val hourly: List<Hourly>?,
    @SerializedName("lat")
    val lat: Double?,
    @SerializedName("lon")
    val lon: Double?,
    @SerializedName("minutely")
    val minutely: @RawValue List<Minutely>?,
    @SerializedName("timezone")
    val timezone: String?,
    @SerializedName("timezone_offset")
    val timezoneOffset: Int?
): Parcelable {
    fun toJson(): String{                      //String olarak bundle gönderiyoruz. Json çevirmeleri burada yapılıyor.
        return Gson().toJson(this)
    }
    companion object {
        fun fromJson(jsonValue: String): MainWeather {
            return Gson().fromJson(jsonValue, MainWeather::class.java)
        }
    }

}//bütün veriler nullable. null olması gibi durumlar için böyle kurdum yapıyı. Bazı model classlarını kullanmıyorum ama dursun, belki ilerleyen aşamalarda kullanırım.
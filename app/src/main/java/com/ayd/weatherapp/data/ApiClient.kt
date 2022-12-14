package com.ayd.weatherapp.data

import com.ayd.weatherapp.BuildConfig
import com.ayd.weatherapp.data.intercepter.AuthInterceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ApiClient {   //mimari kullanmadığımız için burada client oluşturduk. Singleton olsun diye companion object içerisinde yer alıyor.

    companion object {
        private lateinit var apiService: ApiService

        fun getApiService(): ApiService {
            if (!::apiService.isInitialized) {
                val retrofit = Retrofit.Builder()
                    .baseUrl(BuildConfig.BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(getHttpClient())
                    .build()

                apiService = retrofit.create(ApiService::class.java)                   //retrofit bağlantısı sağlandı
            }
            return apiService
        }

        private fun getHttpClient(): OkHttpClient {            //http client bağlantı, okuma vs. gibi süreçler buradan kontrol edilebiliyor.
            val httpClient = OkHttpClient.Builder()
            httpClient.addInterceptor(AuthInterceptor())
            httpClient.connectTimeout(60, java.util.concurrent.TimeUnit.SECONDS)
            httpClient.readTimeout(60, java.util.concurrent.TimeUnit.SECONDS)
            httpClient.writeTimeout(90, java.util.concurrent.TimeUnit.SECONDS)
            return httpClient.build()
        }
    }

}
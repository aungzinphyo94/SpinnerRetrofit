package com.azp.spinnerretrofit.api

import com.azp.spinnerretrofit.model.Township
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class TownshipApi {

    private val townshipInterface: ApiInterface

    companion object{
        const val BASE_URL = "http://food-delivery-shwe-sin-soe.khaingthinkyi.me/api/"
    }

    init {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        townshipInterface = retrofit.create(ApiInterface::class.java)
    }

    fun getTownships(): Call<Township>{
        return townshipInterface.getTownship()
    }

}
package com.azp.spinnerretrofit.api

import com.azp.spinnerretrofit.model.Township
import retrofit2.Call
import retrofit2.http.GET

interface ApiInterface {

    @GET("setup/township")
    fun getTownship(): Call<Township>

}
package com.azp.spinnerretrofit.viewmodel

import android.util.Log
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.azp.spinnerretrofit.api.TownshipApi
import com.azp.spinnerretrofit.model.Township
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class TownshipViewmodel : ViewModel() {

    private var township: MutableLiveData<Township> = MutableLiveData()

    fun getTownship(): LiveData<Township> = township

    fun loadTownship() {

        val townshipApi: TownshipApi = TownshipApi()

        val call = townshipApi.getTownships()

        call.enqueue(object : Callback<Township> {
            override fun onFailure(call: Call<Township>, t: Throwable) {
                Log.d("Result Error>>>>>>", t.toString())
            }

            override fun onResponse(call: Call<Township>, response: Response<Township>) {
                response?.isSuccessful.let {
                    val townshipResult = Township(response.body()?.townships?: emptyList())
                    township.value = townshipResult
                    Log.d("Result township>>>>>>", townshipResult.toString())
                    Log.d("Result Response>>>>>>", response.body().toString())

                }
            }

        })
    }
}

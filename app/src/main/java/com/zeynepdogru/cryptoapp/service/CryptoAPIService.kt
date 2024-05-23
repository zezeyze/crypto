package com.zeynepdogru.cryptoapp.service

import com.zeynepdogru.cryptoapp.model.Crypto
import com.zeynepdogru.cryptoapp.model.CryptoResponse
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class CryptoAPIService {

    val api=Retrofit.Builder()
        .baseUrl("https://api.coinlore.net/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(CryptoAPI::class.java)

    fun getData(): Call<CryptoResponse>{
        return api.getProducts()
    }
}
package com.zeynepdogru.cryptoapp.service

import com.zeynepdogru.cryptoapp.model.Crypto
import com.zeynepdogru.cryptoapp.model.CryptoResponse
import retrofit2.Call
import retrofit2.http.GET

interface CryptoAPI {
   // https://fakestoreapi.com/products
    @GET("api/tickers/?start=100&limit=100")
    fun getProducts(): Call<CryptoResponse>


}
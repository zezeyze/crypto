package com.zeynepdogru.cryptoapp.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.zeynepdogru.cryptoapp.database.CryptoDatabase
import com.zeynepdogru.cryptoapp.database.CryptoDao
import com.zeynepdogru.cryptoapp.model.Crypto
import com.zeynepdogru.cryptoapp.model.CryptoResponse
import com.zeynepdogru.cryptoapp.service.CryptoAPIService
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class MainViewModel(application: Application): AndroidViewModel(application) {
    private val cryptoAPI = CryptoAPIService()
    val productData = MutableLiveData<List<Crypto>>()
    val productError = MutableLiveData<Boolean>()

    private var cryptoDatabase: CryptoDatabase?=null
    private var cryptoDao: CryptoDao? =null
    val crypto= MutableLiveData<Crypto>()

    init{
        cryptoDatabase= CryptoDatabase.getInstance(application)
        cryptoDao= cryptoDatabase?.cryptoDao()
    }

    fun getDataFromAPI() {
        cryptoAPI.getData().enqueue(object : Callback<CryptoResponse> { // Dikkat edin, Callback türü artık CryptoResponse
            override fun onResponse(call: Call<CryptoResponse>, response: Response<CryptoResponse>) {
                if (response.isSuccessful) {
                    // Doğru şekilde erişim sağlamak için response.body()?.data kullanın
                    productData.value = response.body()?.data
                    productError.value = false
                } else {
                    productError.value = true
                }
            }

            override fun onFailure(call: Call<CryptoResponse>, t: Throwable) {
                productError.value = true
                Log.e("RetrofitError", t.message.toString())
            }
        })
    }
    fun insertAll(list: List<Crypto>) = viewModelScope.launch {
        cryptoDao?.insertAll(list)
    }

    fun findByName(name:String) = viewModelScope.launch {
        crypto.value=cryptoDao?.findByName(name)
    }
}


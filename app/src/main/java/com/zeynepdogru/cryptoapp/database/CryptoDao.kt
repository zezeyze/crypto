package com.zeynepdogru.cryptoapp.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.zeynepdogru.cryptoapp.model.Crypto



@Dao
    interface CryptoDao {
        @Query("SELECT * FROM cryptos")
        suspend fun getAll(): List<Crypto>

        @Query("SELECT * FROM cryptos WHERE name = :cryptoName")
        suspend fun findByName(cryptoName: String): Crypto

      @Insert
        suspend fun insertAll(cryptos: List<Crypto>)

    }

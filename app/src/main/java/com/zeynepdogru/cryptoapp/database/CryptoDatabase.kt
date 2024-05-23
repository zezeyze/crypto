package com.zeynepdogru.cryptoapp.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.zeynepdogru.cryptoapp.model.Crypto

@Database(entities = [Crypto::class], version = 1)
abstract class CryptoDatabase : RoomDatabase() {
    abstract fun cryptoDao(): CryptoDao

    companion object {
        @Volatile //sakladığı değerin Thread'ler tarafından okunmaya çalışıldığında hepsinde aynı değerin okunacağının garantisini verir.
        private var INSTANCE: CryptoDatabase? = null

        fun getInstance(context: Context): CryptoDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    CryptoDatabase::class.java,
                    "crypto-DB"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }

}


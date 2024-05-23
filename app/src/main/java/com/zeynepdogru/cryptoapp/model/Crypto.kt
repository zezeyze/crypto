package com.zeynepdogru.cryptoapp.model

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = "cryptos")
data class Crypto(
    @PrimaryKey(autoGenerate = true) val uid:Int=0,
    @ColumnInfo(name="id")
    val id:String,
    @ColumnInfo(name="symbol")
    val symbol:String,
    @ColumnInfo(name="name")
    val name:String,
    @ColumnInfo(name="nameid")
    val nameid:String,
    @ColumnInfo(name="rank")
    val rank:Int,
    @ColumnInfo(name="price_usd")
    val price_usd:String,
    @ColumnInfo(name="percent_change_24h")
    val percent_change_24h:String,
    @ColumnInfo(name="percent_change_1h")
    val percent_change_1h:String,
    @ColumnInfo(name="percent_change_7d")
    val percent_change_7d:String,
    @ColumnInfo(name="price_btc")
    val price_btc:String,
    @ColumnInfo(name="market_cap_usd")
    val market_cap_usd:String,
    @ColumnInfo(name="volume24")
    val volume24:Double,
    @ColumnInfo(name="volume24a")
    val volume24a:Double,
    @ColumnInfo(name="csupply")
    val csupply:String,
    @ColumnInfo(name="tsupply")
    val tsupply:String,
    @ColumnInfo(name="msupply")
    val msupply:String?

) : Parcelable


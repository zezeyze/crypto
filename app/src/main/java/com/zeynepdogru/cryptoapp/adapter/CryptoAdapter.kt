package com.zeynepdogru.cryptoapp.adapter

import android.annotation.SuppressLint
import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.zeynepdogru.cryptoapp.R
import com.zeynepdogru.cryptoapp.databinding.ItemCryptoBinding
import com.zeynepdogru.cryptoapp.model.Crypto
import android.widget.Toast
import com.zeynepdogru.cryptoapp.view.MainActivity
import com.zeynepdogru.cryptoapp.view.home.HomeFragment
class CryptoAdapter( var cryptoList: ArrayList<Crypto>,
                     private var onClick: (position: Int)->Unit) :RecyclerView.Adapter<CryptoAdapter.CryptoViewHolder>() {


    private val colors: Array<String> = arrayOf("#8dcd88","#e1bebe","#8fc6d4","#bda5c8","#c8e1a2","#99badd","#0d9de3","#ffe48f")

    class CryptoViewHolder(var view: ItemCryptoBinding): RecyclerView.ViewHolder(view.root) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CryptoViewHolder{

        val inflater=LayoutInflater.from(parent.context)
        val view=DataBindingUtil.inflate<ItemCryptoBinding>(inflater, R.layout.item_crypto,parent,false)

        return CryptoViewHolder(view)
    }

    override fun getItemCount(): Int {
        return cryptoList.size
    }

    override fun onBindViewHolder(holder: CryptoViewHolder, position: Int) {
        holder.view.titleTV.text= cryptoList[position].name
        holder.view.priceTV.text= cryptoList[position].price_usd

        holder.view.carditem.setOnClickListener{
            onClick(position)
        }
        holder.itemView.setBackgroundColor(Color.parseColor(colors[position % 8]))
        holder.view.titleTV.text = cryptoList[position].price_usd

        holder.view.priceTV.setOnClickListener {
            if(HomeFragment.interstitialAd!=null && HomeFragment.interstitialAd!!.isLoaded){
                HomeFragment.interstitialAd!!.show(MainActivity.activity)
            }else {
                Toast.makeText(holder.view.root.context, "Ad did not load", Toast.LENGTH_SHORT).show()
            }
            onClick(position)
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    fun updateList(newList: List<Crypto>){
        cryptoList=newList as ArrayList<Crypto>
        notifyDataSetChanged()
    }
}
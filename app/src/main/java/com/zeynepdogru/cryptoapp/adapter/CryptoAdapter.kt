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

 class CryptoAdapter( var cryptoList: ArrayList<Crypto>, private var onClick: (position: Int)->Unit) :RecyclerView.Adapter<CryptoAdapter.CryptoViewHolder>() {

    private val colors: Array<String> = arrayOf("#13bd27","#29c1e1","#b129e1","#d3df13","#f6bd0c","#a1fb93","#0d9de3","#ffe48f")

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

//        holder.itemView.setOnClickListener {
//            listener.onItemClick(cryptoList.get(position))
//        }


//        Glide.with(holder.view.root)
//            .load(cyrptoList[position].productImage)
//            .into(holder.view.productIV)
    }

    @SuppressLint("NotifyDataSetChanged")
    fun updateList(newList: List<Crypto>){
        cryptoList=newList as ArrayList<Crypto>
        notifyDataSetChanged()
    }
}
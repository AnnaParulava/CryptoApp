package com.example.cryptoapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.cryptoapp.databinding.ActivityCoinPrceListBinding
import com.example.cryptoapp.databinding.ItemCoinInfoBinding
import com.example.cryptoapp.pojo.CoinPriceInfo
import com.squareup.picasso.Picasso

class CoinInfoAdapter: RecyclerView.Adapter<CoinInfoAdapter.CoinInfoViewHolder>() {

    private lateinit var binding: ActivityCoinPrceListBinding

    var coinInfoList: List<CoinPriceInfo> = listOf()
        set(value){
            field = value
            notifyDataSetChanged()
        }

    var onCoinClickListener: OnCoinClickListener? = null

    inner class CoinInfoViewHolder(private val binding: ItemCoinInfoBinding): RecyclerView.ViewHolder(binding.root){
        val ivLogoCoin: ImageView = binding.ivLogoCoin
        val tvSymbols: TextView = binding.tvSymbols
        val tvPrice: TextView = binding.tvPrice
        val tvLastUpdate: TextView = binding.tvLastUpdate


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CoinInfoViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemCoinInfoBinding.inflate(inflater, parent, false)
        return CoinInfoViewHolder(binding)
    }

    override fun getItemCount() =  coinInfoList.size

    override fun onBindViewHolder(holder: CoinInfoViewHolder, position: Int) {
        val coin = coinInfoList[position]
        holder.tvSymbols.text = buildString {
        append(coin.fromSymbol)
        append(" / ")
        append(coin.toSymbol)
    }
        holder.tvPrice.text = coin.price
        holder.tvLastUpdate.text = coin.getFormattedTime()
        Picasso.get().load(coin.getFullImageUrl()).into(holder.ivLogoCoin)

        holder.itemView.setOnClickListener{
            onCoinClickListener?.onCoinClick(coin)
        }
    }

    interface  OnCoinClickListener{
        fun onCoinClick(coinPriceInfo: CoinPriceInfo)
    }
}
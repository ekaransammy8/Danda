package com.sammyekaran.danda.view.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.sammyekaran.danda.R
import com.sammyekaran.danda.databinding.AdapterTagSuggestionBinding
import com.sammyekaran.danda.databinding.AdapterTrendingGifBinding
import com.sammyekaran.danda.model.trending.Gif
import com.sammyekaran.danda.model.trending.Image
import kotlinx.android.synthetic.main.adapter_trending_gif.view.*


class TrendingGifAdapter(list: List<Gif>, itemClick: ItemClick) :
        RecyclerView.Adapter<TrendingGifAdapter.ViewHolder>() {

    var list: List<Gif>?
    var itemClick: ItemClick
    var context: Context? = null

    init {
        this.list = list
        this.itemClick = itemClick
    }

    override fun onCreateViewHolder(parent: ViewGroup, p1: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        context = parent.context
        val binding = DataBindingUtil.inflate<AdapterTrendingGifBinding>(inflater, R.layout.adapter_trending_gif, parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return list!!.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        Glide.with(context!!)
                .load(list?.get(position)?.image)
                .into(holder.itemView.imageView)



        holder.itemView.setOnClickListener {
            itemClick.onItemClick(it,list?.get(position)?.upload_id)
        }
    }

    fun customNotify(data: List<Gif>) {
        this.list = data
        notifyDataSetChanged()
    }

    class ViewHolder(binding: AdapterTrendingGifBinding) : RecyclerView.ViewHolder(binding.root) {
        val binding: AdapterTrendingGifBinding = binding

    }

    interface ItemClick {
        fun onItemClick(view:View,id: String?)
    }
}
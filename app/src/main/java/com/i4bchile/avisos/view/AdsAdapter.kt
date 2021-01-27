package com.i4bchile.avisos.view

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.i4bchile.avisos.databinding.ItemListAdBinding
import com.i4bchile.avisos.model.Ad

class AdsAdapter(val listener: OnItemClickListener) : RecyclerView.Adapter<AdVH>() {

    val adList = mutableListOf<Ad>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AdVH {
        val binding = ItemListAdBinding.inflate(LayoutInflater.from(parent.context))
        return AdVH(binding)
    }

    override fun onBindViewHolder(holder: AdVH, position: Int) {
        val ad = adList[position]
        holder.itemView.setOnClickListener { listener.onClick(ad.namePublisher) }
        holder.bind(ad)
    }

    override fun getItemCount(): Int {
        return adList.size
    }

    fun updateAds(list: List<Ad>) {
        Log.d("TAG", "updateCategories: en adapter: ${list.size}")
        adList.clear()
        adList.addAll(list)
        notifyDataSetChanged()

    }
}

class AdVH(val binding: ItemListAdBinding) : RecyclerView.ViewHolder(binding.root) {

    fun bind(ad: Ad) {
        binding.tvPublisher.text = ad.namePublisher
        binding.tvPyme.text = ad.pyme

    }


}

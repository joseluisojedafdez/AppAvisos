package com.i4bchile.avisos.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.i4bchile.avisos.databinding.FragmentListaAdsBinding
import com.i4bchile.avisos.viewmodel.AvisosVM

class FragmentListAds(val value:String): Fragment(),OnItemClickListener {
    private lateinit var binding:FragmentListaAdsBinding
    private val viewModel: AvisosVM by viewModels()
    private val adapter=AdsAdapter(this)
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding= FragmentListaAdsBinding.inflate(layoutInflater)
        binding.tvCategoriesListAdd.text=value
        binding.rvListaAd.adapter=adapter
        binding.rvListaAd.layoutManager= LinearLayoutManager(this.context)
        viewModel.listAds(value).observe(viewLifecycleOwner,{
            adapter.updateAds(it)
        })

        return binding.root
    }

    override fun onClick(value: String) {
        //TODO: Abre el fragmento de detalle

    }

}
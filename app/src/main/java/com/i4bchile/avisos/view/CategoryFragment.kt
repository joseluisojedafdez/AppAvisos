package com.i4bchile.avisos.view

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.i4bchile.avisos.databinding.FragmentCategoryBinding
import com.i4bchile.avisos.viewmodel.AvisosVM

class CategoryFragment: Fragment() {
    private lateinit var binding:FragmentCategoryBinding
    val adapter=CategoryAdapter()
    val viewModel: AvisosVM by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding= FragmentCategoryBinding.inflate(layoutInflater)
        binding.rvCategories.adapter=adapter
        binding.rvCategories.layoutManager=LinearLayoutManager(this.context)
        viewModel.listCategory.observe(viewLifecycleOwner, {
            Log.d("TAG", "onCreateView: listacategorias con: ${it.size}")
            adapter.updateCategories(it)
        })



        return binding.root
    }


}
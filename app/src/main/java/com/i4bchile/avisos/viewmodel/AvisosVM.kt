package com.i4bchile.avisos.viewmodel


import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.i4bchile.avisos.model.Ad
import com.i4bchile.avisos.model.Repository
import kotlinx.coroutines.launch

class AvisosVM: ViewModel() {
    fun listAds(value: String): LiveData<List<Ad>> {
        return repository.listAds(value)

    }

    val repository= Repository()
    val listCategory=repository.listCategories

   init{

        viewModelScope.launch{repository.getDocuments()}

    }



}
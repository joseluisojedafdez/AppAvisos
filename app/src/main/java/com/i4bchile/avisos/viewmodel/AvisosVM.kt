package com.i4bchile.avisos.viewmodel


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.i4bchile.avisos.model.Repository
import kotlinx.coroutines.launch

class AvisosVM: ViewModel() {

    val repository= Repository()
    val listCategory=repository.listCategories

   init{

        viewModelScope.launch{repository.getDocuments()}

    }



}
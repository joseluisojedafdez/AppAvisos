package com.i4bchile.avisos.viewmodel


import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.i4bchile.avisos.model.Ad
import com.i4bchile.avisos.model.Evaluation
import com.i4bchile.avisos.model.Ratings
import com.i4bchile.avisos.model.Repository
import kotlinx.coroutines.launch

class AvisosVM : ViewModel() {

    private val repository = Repository()
    val listCategory = repository.listCategories

    init {

        viewModelScope.launch { repository.getDocuments() }
    }

    fun listAds(value: String): LiveData<List<Ad>> {
        return repository.listAds(value)
    }

    fun getDetail(value: String): LiveData<Ad> {
        return repository.getDetail(value)
    }

    fun addEval(eval: Evaluation) {
        repository.addEval(eval)
    }

    fun getRatings(pNamePublisher:String):LiveData<Ratings>{

        return repository.getRatings(pNamePublisher)

    }
}

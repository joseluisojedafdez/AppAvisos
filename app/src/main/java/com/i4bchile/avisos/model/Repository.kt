package com.i4bchile.avisos.model


import android.util.Log
import androidx.lifecycle.LiveData
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await


class Repository {

    private val adsDatabase = AdsApplication.adsDatabase!!
    val listCategories = adsDatabase.adsDao().getCategories()


    var db = FirebaseFirestore.getInstance()
    var TAG = "Firebase"
    val listAdsFirebase = mutableListOf<Ad>()
    val listEvalsFirebase=mutableListOf<Evaluation>()


    suspend fun getDocuments() {
        Log.d(TAG, "getDocuments: ")
        db.collection("Avisos").get().await().documents.forEach {
            val ad = getAd(it)
            listAdsFirebase.add(ad)
        }
        insertAds(listAdsFirebase)
        db.collection("Evaluations").get().await().documents.forEach{
            if (it!=null){
            val eval=getEval(it)
            listEvalsFirebase.add(eval)}
        }
        insertEvals(listEvalsFirebase)
    }

    private fun getEval(document: DocumentSnapshot): Evaluation {
        val namePublisher=document.getString("namePublisher")!!
        val userName=document.getString("userName")!!
        val evaluation= document.getLong("rating")!!
        val comment=document.getString("comment")!!

        return Evaluation(namePublisher,userName,evaluation.toInt(),comment)


    }


    private suspend fun insertAds(listAdsFirebase: MutableList<Ad>) {
        Log.d(TAG, "insertAds: añadiendo ${listAdsFirebase.size} documentos")
        adsDatabase.adsDao().insertAds(listAdsFirebase)

    }
    private suspend fun insertEvals(listEvalsFirebase:MutableList<Evaluation>){

        adsDatabase.adsDao().insertEvals(listEvalsFirebase)

    }

    private fun getAd(document: DocumentSnapshot): Ad {
        val namePublisher = document.getString("namePublisher")!!
        val pyme = document.getString("pyme")!!
        val category = document.getString("category")!!
        val active = document.getBoolean("active")!!
        val imageURL = document.getString("imageURL")!!

        val title = document.getString("title")!!
        val description = document.getString("description")!!
        val email = document.getString("email")!!
        val cellPhone = document.getString("cellPhone")!!
        val webSite = document.getString("webSite")
        val address = document.getString("address")!!
        val city = document.getString("city")!!
        val details = Detail(title, description, email, cellPhone, webSite, address, city)
        val ad = Ad(namePublisher, pyme, category, details, active, imageURL)

        return ad

    }

    fun listAds(value: String): LiveData<List<Ad>> {

        return adsDatabase.adsDao().getAdsbyCategory(value)

    }

    fun getDetail(value: String): LiveData<Ad> {

        return adsDatabase.adsDao().getAd(value)

    }

    fun addEval(eval: Evaluation) {

        val evaluations=db.collection("Evaluations")
        val data = hashMapOf(
            "namePublisher" to eval.namePublisher,
            "userName" to eval.userName,
            "rating" to eval.rating,
            "comment" to eval.comment
        )
        evaluations.document().set(data)

    }

    fun getRatings(publisher:String):LiveData<Ratings>{
        Log.d("TAG", "getRatings: ${adsDatabase.adsDao().getRatings(publisher).value}")
        return adsDatabase.adsDao().getRatings(publisher)
    }

}
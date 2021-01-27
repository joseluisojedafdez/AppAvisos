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

    fun addData() {

        val avisos=db.collection("Avisos")


        val data1=hashMapOf(
            "namePublisher" to "Magaly Portales",
            "pyme" to "Panaderia Maggy",
            "category" to "Panaderia/Pasteleria",
            "active" to true,
            "ImageURL" to "",
            "title" to "Servicio de Panaderia y Pasteleria"	,
            "description" to "",
            "email" to "",
            "cellPhone" to "+56 9 7468 0783",
            "webSite" to "",
            "address" to ""	,
            "city" to "")
        avisos.document().set(data1)

        val data2=hashMapOf(
            "namePublisher" to "Arely Orellana",
            "pyme" to "Pie de Limon",
            "category" to "Panaderia/Pasteleria",
            "active" to true,
            "ImageURL" to "",
            "title" to "Pie de Limon a Pedido",
            "description" to "",
            "email" to "",
            "cellPhone" to "+56 9 7443 6839",
            "webSite" to "",
            "address" to "",
            "city" to "")

        avisos.document().set(data2)



        val data3=hashMapOf(
            "namePublisher" to "Pilar Luna",
            "pyme" to "Sanitizantes",
            "category" to "Comestibles e Insumos",
            "active" to true,
            "ImageURL" to "",
            "title" to "Venta de Sanitizantes",
            "description" to "",
            "email" to "",
            "cellPhone" to "+56 9 6263 0258",
            "webSite" to "",
            "address" to "",
            "city" to "")
        avisos.document().set(data3)

        val data4=hashMapOf(
            "namePublisher" to "Javi Fernandez",
            "pyme" to "Muebleria",
            "category" to "Muebles",
            "active" to true,
            "ImageURL" to "",
            "title" to "Confección de Muebles de Madera",
            "description" to "",
            "email" to "",
            "cellPhone" to "+56 9 8906 0778",
            "webSite" to "",
            "address" to "",
            "city" to "")
        avisos.document().set(data4)

        val data5=hashMapOf(
            "namePublisher" to "Luis Neira",
            "pyme" to "Maestro y Mecï¿½nico",
            "category" to "Maestros",
            "active" to true,
            "ImageURL" to "",
            "title" to "Servicio de Mecï¿½nica	 soldador e instalacion de ceramicos",
            "description" to "",
            "email" to "",
            "cellPhone" to "+56 9 9845 7219",
            "webSite" to "",
            "address" to "",
            "city" to "")

        avisos.document().set(data5)

        val data6=hashMapOf(
            "namePublisher" to "Janini Marin",
            "pyme" to "La Canasta Market",
            "category" to "Comestibles e Insumos",
            "active" to true,
            "ImageURL" to "",
            "title" to "Minimarket",
            "description" to "Servicio de abarrotes	 bebidas	 snack	 etc.",
            "email" to "",
            "cellPhone" to "+56 9 6453 6744",
            "webSite" to "",
            "address" to "",
            "city" to "")
        avisos.document().set(data6)

        val data7=hashMapOf(
            "namePublisher" to "M Isidora Espinoza",
            "pyme" to "Clases de Quimica",
            "category" to "Clases Particulares",
            "active" to true,
            "ImageURL" to "",
            "title" to "Clases Online Quimica",
            "description" to "Desde 7 Basico a 4 Medio",
            "email" to "",
            "cellPhone" to "+56 9 6833 3161",
            "webSite" to "",
            "address" to "",
            "city" to "")

        avisos.document().set(data7)

        val data8=hashMapOf(
            "namePublisher" to "Francisco Pozo",
            "pyme" to "Desinfectantes",
            "category" to "Comestibles e Insumos",
            "active" to true,
            "ImageURL" to "",
            "title" to "Venta de Amonio Cuaternario",
            "description" to "",
            "email" to "",
            "cellPhone" to "+56 9 8148 7234",
            "webSite" to "",
            "address" to "",
            "city" to "")

        avisos.document().set(data8)

        val data9=hashMapOf(
            "namePublisher" to "Denisse Johnson",
            "pyme" to "Kinesiologiaa",
            "category" to "Bienestar/Forma física",
            "active" to true,
            "ImageURL" to "",
            "title" to "Kinesiologa",
            "description" to "Rehabilitacion de lesiones y entrenamiento online",
            "email" to "",
            "cellPhone" to "+56 9 86610 1861",
            "webSite" to "",
            "address" to "",
            "city" to "")

        avisos.document().set(data9)

        val data10=hashMapOf(
            "namePublisher" to "David Urbano",
            "pyme" to "Construccion",
            "category" to "Maestros",
            "active" to true,
            "ImageURL" to "",
            "title" to "Construccion y Muebleria",
            "description" to "",
            "email" to "",
            "cellPhone" to "+56 9 4011 7228",
            "webSite" to "",
            "address" to "",
            "city" to "")
        avisos.document().set(data10)

        val data11=hashMapOf(
            "namePublisher" to "Beatriz Mena",
            "pyme" to "Tortas Caseras",
            "category" to "Panaderia/Pasteleria",
            "active" to true,
            "ImageURL" to "",
            "title" to "Tortas caserras a pedido",
            "description" to "",
            "email" to "",
            "cellPhone" to "+56 9 9125 3568",
            "webSite" to "",
            "address" to "",
            "city" to "")
        avisos.document().set(data11)


        val data12=hashMapOf(
            "namePublisher" to "Gabriel Brunaud",
            "pyme" to "Delicias Chef",
            "category" to "Panaderia/Pasteleria",
            "active" to true,
            "ImageURL" to "",
            "title" to "Pastas y Reposteria",
            "description" to "Delivery en el centro de Viña del Mar",
            "email" to "",
            "cellPhone" to "+56 9 5212 1266",
            "webSite" to "",
            "address" to "",
            "city" to "")
        avisos.document().set(data12)

        val data13=hashMapOf(
            "namePublisher" to "Kevin Severino",
            "pyme" to "Wood Working Chile",
            "category" to "Muebles",
            "active" to true,
            "ImageURL" to "",
            "title" to "Muebleria y Remodelacion",
            "description" to "",
            "email" to "",
            "cellPhone" to "+56 9 4859 6515",
            "webSite" to "",
            "address" to "",
            "city" to "")

        avisos.document().set(data13)

        val data14=hashMapOf(
            "namePublisher" to "Flavio Cancino",
            "pyme" to "BNAT",
            "category" to "Comestibles e Insumos",
            "active" to true,
            "ImageURL" to "",
            "title" to "Superalimentos	 suplementos y Frutos Secos",
            "description" to "Servicio a domicilio",
            "email" to "",
            "cellPhone" to "+56 9 4859 6515",
            "webSite" to "",
            "address" to "",
            "city" to "")

        avisos.document().set(data14)


        val data15=hashMapOf(
            "namePublisher" to "Martin Amador",
            "pyme" to "Fletes Beima",
            "category" to "Fletes y Mudanzas",
            "active" to true,
            "ImageURL" to "",
            "title" to "Servicio de Fletes y Mudanza",
            "description" to "",
            "email" to "",
            "cellPhone" to "+56 9 7166 2659",
            "webSite" to "",
            "address" to "",
            "city" to "")
        avisos.document().set(data15)

        val data16=hashMapOf(
            "namePublisher" to "Javiera Olivares",
            "pyme" to "Appetizers	 Tablas & Dis",
            "category" to "Comestibles e Insumos",
            "active" to true,
            "ImageURL" to "",
            "title" to "Tablas de picoteo dulces	 saladas y Mixtas",
            "description" to "Delivery",
            "email" to "",
            "cellPhone" to "+56 9 7636 7816",
            "webSite" to "",
            "address" to "",
            "city" to "")
        avisos.document().set(data16)

        val data17=hashMapOf(
            "namePublisher" to "Priscila Valenzuela",
            "pyme" to "Giraffa",
            "category" to "Textil",
            "active" to true,
            "ImageURL" to "",
            "title" to "Tejidos de Lana",
            "description" to "Banderines cuellos bufandas gorros de lana tejidos a mano",
            "email" to "",
            "cellPhone" to "+56 9 8828 8066",
            "webSite" to "",
            "address" to "",
            "city" to "")

        avisos.document().set(data17)


        val data18=hashMapOf(
            "namePublisher" to "Ayleen Espinoza",
            "pyme" to "Productos Pestañitas",
            "category" to "Textil",
            "active" to true,
            "ImageURL" to "",
            "title" to "Mascarillas reutilizables",
            "description" to "Con 3 capas de tela",
            "email" to "",
            "cellPhone" to "+56 9 5959 5028",
            "webSite" to "",
            "address" to "",
            "city" to "")
        avisos.document().set(data18)

        val data19=hashMapOf(
            "namePublisher" to "Ricardo Lagos",
            "pyme" to "La Huerta de Concón",
            "category" to "Comestibles e Insumos",
            "active" to true,
            "ImageURL" to "",
            "title" to "Frutas	 verduras	 huevos	 frutos secos y mas",
            "description" to "Delivery en Viï¿½a	 Valpo y Concon",
            "email" to "",
            "cellPhone" to "+56 9 7783 5588",
            "webSite" to "",
            "address" to "",
            "city" to "")
        avisos.document().set(data19)

        val data20=hashMapOf(
            "namePublisher" to "German Pichinao",
            "pyme" to "GP Music",
            "category" to "Clases Particulares",
            "active" to true,
            "ImageURL" to "",
            "title" to "Clases personalizadas y talleres de musica",
            "description" to "",
            "email" to "germanpichinao@gmail.com",
            "cellPhone" to "+56 9 6159 6782",
            "webSite" to "",
            "address" to "",
            "city" to "")
        avisos.document().set(data20)

    }

}
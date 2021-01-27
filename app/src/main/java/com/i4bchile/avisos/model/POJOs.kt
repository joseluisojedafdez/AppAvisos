package com.i4bchile.avisos.model

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "ad")
data class Ad(
    @PrimaryKey val namePublisher: String,
    val pyme: String,
    val category: String,
    @Embedded val details: Detail,
    val active: Boolean,
    val imageURL: String,
)

data class Detail(
    val title: String,
    val description: String,
    val email: String?,
    val cellPhone: String,
    val webSite: String?,
    val address: String,
    val city: String
)
@Entity(tableName="eval",primaryKeys=["namePublisher","userName"])
data class Evaluation(
    val namePublisher: String,
    val userName: String,
    val rating: Int,
    val comment: String
)

data class Ratings(val namePublisher:String,
                  val evaluations:Int,
                  val sumaRating:Int,

)

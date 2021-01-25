package com.i4bchile.avisos.model

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface AdsDao{

    @Insert(onConflict=OnConflictStrategy.REPLACE)
    suspend fun insertAds(lista:List<Ad>)

    @Query("SELECT * FROM ad")
    fun getAllAds(): LiveData<List<Ad>>

    @Query("SELECT category FROM ad GROUP BY category")
    fun getCategories():LiveData<List<String>>

    @Query("SELECT * FROM ad WHERE namePublisher=:publisher AND active=:pActive" )
    fun getAd(publisher:String, pActive:Boolean):LiveData<Ad>

    @Query("SELECT * FROM ad WHERE category=:category")
    fun getAdsbyCategory(category:String):LiveData<List<Ad>>

}

@Database(entities=[Ad::class],version=1)
abstract class AddDatabase:RoomDatabase(){
    abstract fun adsDao():AdsDao

}

class AdsApplication: Application() {

    companion object {
        var adsDatabase: AddDatabase? = null

    }

    override fun onCreate() {
        super.onCreate()

        adsDatabase = Room.databaseBuilder(this, AddDatabase::class.java, "super_db").build()
    }
}
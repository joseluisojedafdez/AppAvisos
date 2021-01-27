package com.i4bchile.avisos.model

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface AdsDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAds(lista: List<Ad>)

    @Query("SELECT * FROM ad")
    fun getAllAds(): LiveData<List<Ad>>

    @Query("SELECT category FROM ad GROUP BY category")
    fun getCategories(): LiveData<List<String>>

    @Query("SELECT * FROM ad WHERE namePublisher=:publisher AND active=1")
    fun getAd(publisher: String): LiveData<Ad>

    @Query("SELECT * FROM ad WHERE category=:pCategory AND active=1")
    fun getAdsbyCategory(pCategory: String): LiveData<List<Ad>>

}

@Database(entities = [Ad::class], version = 1)
abstract class AddDatabase : RoomDatabase() {
    abstract fun adsDao(): AdsDao

}

class AdsApplication : Application() {

    companion object {
        var adsDatabase: AddDatabase? = null

    }

    override fun onCreate() {
        super.onCreate()

        adsDatabase = Room.databaseBuilder(this, AddDatabase::class.java, "super_db").build()
    }
}
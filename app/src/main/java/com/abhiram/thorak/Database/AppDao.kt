package com.abhiram.thorak

import androidx.room.*


@Dao
interface AppDao {

    @Query("SELECT * FROM app_list ORDER BY appName ASC")
    fun getAll(): List<AppList>

    @Query("SELECT * From app_list WHERE is_fav LIKE 1 ORDER BY appName ASC")
    suspend fun getFav() : List<AppList>

    @Query("UPDATE app_list SET is_fav=1 WHERE appName LIKE :name")
    suspend fun setFav(name : String)

    @Query("UPDATE app_list SET is_fav=0 WHERE appName LIKE :name")
    suspend fun removeFav(name : String)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun add(app : AppList)

    @Delete
    suspend fun remove(app:AppList)
}
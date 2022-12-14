package com.abhiram.thorak

import androidx.room.*


@Dao
interface AppDao {

    @Query("SELECT * FROM app_list ORDER BY appName ASC")
    fun getAll(): List<AppList>

    @Query("SELECT is_fav FROM app_list WHERE appName LIKE :name")
    suspend fun isfav(name:String):Int

    @Query("SELECT appName FROM app_list")
    suspend fun getNames():List<String>

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

    @Query("DELETE FROM app_list WHERE pkg_name LIKE :pkg")
    suspend fun uninstalled(pkg:String)

    @Query("DELETE FROM app_list")
    suspend fun deleteAll()
}
package com.abhiram.thorak

import androidx.compose.foundation.shape.CornerBasedShape
import androidx.room.*
import com.abhiram.thorak.Database.ShapeIcon


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

    @Query("UPDATE app_list SET hide=1 WHERE appName LIKE :name")
    suspend fun hide(name : String)

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

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addShape(shape: ShapeIcon)
//
//    @Query("SELECT shape FROM icon_shape WHERE id LIKE 1")
//    suspend fun getShape() : CornerBasedShape
//
//    @Query("UPDATE icon_shape SET shape=:shape WHERE id LIKE 1")
//    suspend fun setShape(shape: CornerBasedShape)

}
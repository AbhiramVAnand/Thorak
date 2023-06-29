package com.abhiram.thorak

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "app_list")
data class AppList(
    @PrimaryKey(autoGenerate = false)val pkgName: String,
    @ColumnInfo(name = "appName")  val appName: String,
    @ColumnInfo(name = "is_fav") var isFav: Boolean,
    @ColumnInfo(name="hide") var hide: Boolean
)

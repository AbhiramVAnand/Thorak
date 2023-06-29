package com.abhiram.thorak.helpers

import android.content.Context
import com.abhiram.thorak.AppDao
import com.abhiram.thorak.AppDatabase
import com.abhiram.thorak.AppList
import com.abhiram.thorak.Database.ShapeIcon
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class RoomRepository(context: Context) {
    var appDatabase : AppDatabase
    var appDao : AppDao
    init {
        appDatabase = AppDatabase.getDatabse(context)
        appDao = appDatabase.appDao()
    }
    fun addApps(appList : AppList){
        GlobalScope.launch {
            appDao.add(appList)
        }
    }

    fun addShape(choice : Int){
        GlobalScope.launch {
            appDao.addShape(ShapeIcon(choice))
        }
    }
}
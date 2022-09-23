package com.abhiram.thorak

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [AppList :: class], version = 1)
abstract class AppDatabase : RoomDatabase(){

    abstract fun appDao() : AppDao

    companion object{

        @Volatile
        private var INSTANCE : AppDatabase? = null

        fun getDatabse(context: Context) : AppDatabase{
            val tempInstance = INSTANCE
            if(tempInstance != null){
                return tempInstance
            }
            synchronized(this){

                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "app_database"
                ).build()
                INSTANCE = instance
                return instance
            }
        }
    }

}
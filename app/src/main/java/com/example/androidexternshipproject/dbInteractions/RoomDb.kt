package com.example.androidexternshipproject.dbInteractions

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
@Database(entities = [CredentialsEntity::class], version = 1, exportSchema = false)
abstract class RoomDb : RoomDatabase() {
    abstract fun roomDbDao(): RoomDbDao
    companion object{
        @Volatile
        private var INSTANCE: RoomDatabase? = null
        fun getInstance(context: Context): RoomDatabase {
            synchronized(this) {
                var instance = INSTANCE
                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        RoomDatabase::class.java,
                        "logins_database"
                    ).fallbackToDestructiveMigration()
                        .build()
                    INSTANCE = instance
                }
                return instance
            }
        }
    }
}
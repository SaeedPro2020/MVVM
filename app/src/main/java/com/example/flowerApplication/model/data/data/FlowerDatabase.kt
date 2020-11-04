package com.example.flowerApplication.model.data.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [DataClass::class], version = 1, exportSchema = false)
abstract class FlowerDatabase : RoomDatabase() {

    abstract fun flowerDao(): FlowerDao

    companion object {
        @Volatile
        private var INSTANCE: FlowerDatabase? = null

        fun getDatabase(context: Context): FlowerDatabase {
            if (INSTANCE == null) {
                synchronized(this) {
                    INSTANCE = Room.databaseBuilder(
                        context.applicationContext,
                        FlowerDatabase::class.java,
                        "flowers.db",
                    ).build()
                }
            }
            return INSTANCE!!
        }
    }

}
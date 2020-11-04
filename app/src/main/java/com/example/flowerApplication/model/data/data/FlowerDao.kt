package com.example.flowerApplication.model.data.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.flowerApplication.model.data.data.DataClass

//Data access objects (Dao)
@Dao
interface FlowerDao {

    @Query("SELECT * from flowers")
    fun getAll(): List<DataClass>

    @Insert
    suspend fun insertFlower(flower: DataClass)

    @Insert
    suspend fun inserFlowers(flowers: List<DataClass>)

    @Query("DELETE from flowers")
    suspend fun deleteAll()

}
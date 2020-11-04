package com.example.flowerApplication.model.data.data

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.flowerApplication.model.data.utilities.ArrayUrl

@Entity(tableName = "flowers")
data class DataClass(
    @PrimaryKey(autoGenerate = true)
    val flowerId: Int,
    val imageFile: String,
    val flowerName: String,
    val description: String,
    val price: Double,
    val popularity: Float
){
    val imageUrl
        get() = ArrayUrl[imageFile]
}
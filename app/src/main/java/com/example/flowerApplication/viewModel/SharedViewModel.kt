package com.example.flowerApplication.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.flowerApplication.model.data.data.DataClass
import com.example.flowerApplication.model.data.data.Repository

class SharedViewModel(app: Application) : AndroidViewModel(app) {

    private val dataRepo = Repository(app)
    val flowerData = dataRepo.flowerData

    val selectedFlower = MutableLiveData<DataClass>()

    fun refreshData() {
        dataRepo.refreshDataFromWeb()
    }

}
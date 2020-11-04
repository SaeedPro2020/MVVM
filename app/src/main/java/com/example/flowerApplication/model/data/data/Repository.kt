package com.example.flowerApplication.model.data.data

import android.app.Application
import android.content.Context
import android.net.ConnectivityManager
import android.widget.Toast
import androidx.annotation.WorkerThread
import androidx.lifecycle.MutableLiveData
import com.example.flowerApplication.model.data.utilities.MYWEB_SERVICE_URL
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory


class Repository(val app: Application) {

    val flowerData = MutableLiveData<List<DataClass>>()
    private val flowerDao = FlowerDatabase.getDatabase(app).flowerDao()

    init {
        CoroutineScope(Dispatchers.IO).launch {
            val data = flowerDao.getAll()
            if (data.isEmpty()){
                refreshDataFromWeb()
            }else{
                flowerData.postValue(data)
                withContext(Dispatchers.Main){
                    Toast.makeText(app, "Using local data", Toast.LENGTH_LONG).show()
                }
            }
        }
    }
    @WorkerThread
    suspend fun callWebService() {
        if (networkAvailable()) {
            withContext(Dispatchers.Main){
                Toast.makeText(app, "Using remote data", Toast.LENGTH_LONG).show()
            }
            val retrofit = Retrofit.Builder()
                .baseUrl(MYWEB_SERVICE_URL)
                .addConverterFactory(MoshiConverterFactory.create())
                .build()
            val service = retrofit.create(Service::class.java)
            val serviceData = service.getFlowerData().body() ?: emptyList()
            flowerData.postValue(serviceData)
            flowerDao.deleteAll()
            flowerDao.inserFlowers(serviceData)
        }
    }

    @Suppress("DEPRECATION")
    private fun networkAvailable(): Boolean {
        val connectivityManager = app.getSystemService(Context.CONNECTIVITY_SERVICE)
                as ConnectivityManager
        val networkInfo = connectivityManager.activeNetworkInfo
        return networkInfo?.isConnectedOrConnecting ?: false
    }


    fun refreshDataFromWeb() {
        CoroutineScope(Dispatchers.IO).launch {
            callWebService()
        }
    }
}
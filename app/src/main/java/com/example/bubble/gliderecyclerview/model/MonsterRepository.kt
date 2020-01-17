package com.example.bubble.gliderecyclerview.model

import android.app.Application
import android.content.Context
import android.net.ConnectivityManager
import android.util.Log
import androidx.annotation.WorkerThread
import androidx.lifecycle.MutableLiveData
import com.example.bubble.gliderecyclerview.BASE_URL
import com.example.bubble.gliderecyclerview.LOG_TAG
import com.example.bubble.gliderecyclerview.api.MonsterService
import com.example.bubble.gliderecyclerview.utilities.FileHelper
import com.google.gson.Gson
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MonsterRepository(val app: Application) {

    val monsterData = MutableLiveData<MonsterGroup>()

    init {
        refreshData()
    }
    fun getMonsters(){
        val text = FileHelper.getTextFromAssets(app, "monster_data.json")
        monsterData.value = Gson().fromJson(text, MonsterGroup::class.java)
    }

    private fun networkAvailable() : Boolean{
        val connectivityManager = app.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val networkInfo = connectivityManager.activeNetworkInfo
        return networkInfo?.isConnectedOrConnecting ?:false
    }

    @WorkerThread
    suspend fun getRemoteMonsterData(){
        Log.i(LOG_TAG, "Remote Service called")
        if(networkAvailable()){
            val retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
            val service = retrofit.create(MonsterService::class.java)
            val serviceData = service.getMonsterData()
            monsterData.postValue(serviceData)
        }
    }

    fun refreshData() {
        CoroutineScope(Dispatchers.IO).launch{
            getRemoteMonsterData()
        }
    }
}
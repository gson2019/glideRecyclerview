package com.example.bubble.gliderecyclerview.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.bubble.gliderecyclerview.model.Monster
import com.example.bubble.gliderecyclerview.model.MonsterRepository

class SharedViewModel(app: Application) : AndroidViewModel(app) {

    private val dataRepo = MonsterRepository(app)
    val monsterData = dataRepo.monsterData
    var selectedMonster:Monster? = null;
//    val selectedMonster = MutableLiveData<Monster>()
    fun refreshData() {
        dataRepo.refreshData()
    }
}

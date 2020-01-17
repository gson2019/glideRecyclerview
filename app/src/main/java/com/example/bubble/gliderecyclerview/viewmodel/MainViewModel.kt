package com.example.bubble.gliderecyclerview.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.example.bubble.gliderecyclerview.model.MonsterRepository

class MainViewModel(app: Application) : AndroidViewModel(app) {

    private val dataRepo = MonsterRepository(app)
    val monsterData = dataRepo.monsterData

    fun refreshData() {
        dataRepo.refreshData();
    }
}

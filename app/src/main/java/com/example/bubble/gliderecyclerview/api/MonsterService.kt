package com.example.bubble.gliderecyclerview.api

import com.example.bubble.gliderecyclerview.model.MonsterGroup
import retrofit2.Response
import retrofit2.http.GET

interface MonsterService {
    @GET("/feed/monster_data.json")
    suspend fun getMonsterData(): MonsterGroup
}
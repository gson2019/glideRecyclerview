package com.example.bubble.gliderecyclerview.model

import android.os.Parcelable
import com.example.bubble.gliderecyclerview.IMAGE_BASE_URL
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Monster(
    val monsterName: String,
    val imageFile: String,
    val caption: String,
    val description: String,
    val price: Double,
    val scariness: Int
) : Parcelable{
    val imageUrl
        get() = "$IMAGE_BASE_URL/$imageFile.webp"
    val thumbnailUrl
        get() = "$IMAGE_BASE_URL/${imageFile}_tn.webp"
}
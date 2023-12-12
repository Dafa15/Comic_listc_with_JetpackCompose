package com.example.comislist.model

data class Comic(
    val id: Long,
    val name: String,
    val genre: String,
    val author: String,
    val score: Double,
    val image: Int,
    val description: String,
    var isFavorite: Boolean = false
)
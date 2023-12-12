package com.example.comislist.data

import com.example.comislist.model.Comic
import com.example.comislist.model.ComicData
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf

class ComicRepository {

    private val comicList = mutableListOf<Comic>()

    init {
        if (comicList.isEmpty()){
            ComicData.comic.forEach {
                comicList.add(it)
            }
        }
    }

    fun getComicId(comicId: Long): Comic {
        return comicList.first{
            it.id == comicId
        }
    }

    fun updateComic(comicId: Int, newState: Boolean): Flow<Boolean> {
        val index = comicList.indexOfFirst { it.id.toInt() == comicId }
        val result = if (index >= 0) {
            val comic = comicList[index]
            comicList[index] = comic.copy(isFavorite = newState)
            true
        } else {
            false
        }
        return flowOf(result)
    }

    fun getFavoriteComic(): Flow<List<Comic>> {
        return flowOf(comicList.filter { it.isFavorite })
    }

    fun searchComic(query: String) = flow {
        val data = comicList.filter {
            it.name.contains(query, ignoreCase = true)
        }
        emit(data)
    }

    companion object {
        @Volatile
        private var instance: ComicRepository? = null

        fun getInstance(): ComicRepository =
            instance ?: synchronized(this) {
                ComicRepository().apply {
                    instance = this
                }
            }
    }
}
package com.example.comislist.di

import com.example.comislist.data.ComicRepository

object Injection {
    fun provideRepository(): ComicRepository {
        return ComicRepository.getInstance()
    }
}
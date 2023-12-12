package com.example.comislist.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.comislist.common.UiState
import com.example.comislist.data.ComicRepository
import com.example.comislist.model.Comic
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class DetailViewModel (private val repository: ComicRepository)
    : ViewModel() {
    private val _uiState: MutableStateFlow<UiState<Comic>> =
        MutableStateFlow(UiState.Loading)
    val uiState: StateFlow<UiState<Comic>>
        get() = _uiState

    fun getComicById(comicId: Long) {
        viewModelScope.launch {
            _uiState.value = UiState.Loading
            _uiState.value = UiState.Success(repository.getComicId(comicId))
        }
    }
    fun updateComic(id: Int, newState: Boolean) = viewModelScope.launch {
        repository.updateComic(id, newState)
            .collect { isUpdated ->
                if (isUpdated) getComicById(id.toLong())
            }
    }
}
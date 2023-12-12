package com.example.comislist.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.comislist.common.UiState
import com.example.comislist.data.ComicRepository
import com.example.comislist.model.Comic
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch

class FavoriteViewModel (private val repository: ComicRepository)
    : ViewModel() {
    private val _uiState: MutableStateFlow<UiState<List<Comic>>> = MutableStateFlow(UiState.Loading)
    val uiState: StateFlow<UiState<List<Comic>>>
        get() = _uiState

    fun getFavoriteComic() = viewModelScope.launch {
        repository.getFavoriteComic()
            .catch {
                _uiState.value = UiState.Error(it.message.toString())
            }
            .collect {
                _uiState.value = UiState.Success(it)
            }
    }
}
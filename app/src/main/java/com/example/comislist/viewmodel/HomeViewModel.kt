package com.example.comislist.viewmodel

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.comislist.common.UiState
import com.example.comislist.data.ComicRepository
import com.example.comislist.model.Comic
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch

class HomeViewModel (private val repository: ComicRepository)
     : ViewModel() {
        private val _uiState: MutableStateFlow<UiState<List<Comic>>> = MutableStateFlow(UiState.Loading)
        val uiState: StateFlow<UiState<List<Comic>>>
        get() = _uiState


    private val _query = mutableStateOf("")
    val query: State<String> get() = _query
    fun search(newQuery: String) {
        _query.value = newQuery
        viewModelScope.launch {
            repository.searchComic(_query.value)
                .catch {
                    _uiState.value = UiState.Error(it.message.toString())
                }
                .collect { comicList ->
                    _uiState.value = UiState.Success(comicList)
                }
            }
        }
    }

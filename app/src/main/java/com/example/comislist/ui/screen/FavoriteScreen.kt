package com.example.comislist.ui.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.comislist.common.UiState
import com.example.comislist.di.Injection
import com.example.comislist.model.Comic
import com.example.comislist.viewmodel.FavoriteViewModel
import com.example.comislist.viewmodel.ViewModelFactory

@Composable
fun FavoriteScreen (
    navigateToDetail: (Long) -> Unit,
    viewModel: FavoriteViewModel = viewModel(
        factory = ViewModelFactory(Injection.provideRepository())
    )
) {
    viewModel.uiState.collectAsState(initial = UiState.Loading).value.let { uiState ->
        when (uiState) {
            is UiState.Loading -> {
                viewModel.getFavoriteComic()
            }
            is UiState.Success -> {
                GetFavoriteList(
                    comicList = uiState.data,
                    navigateToDetail = navigateToDetail
                )
            }
            is UiState.Error -> {}
        }
    }
}

@Composable
fun GetFavoriteList(
    comicList: List<Comic>,
    navigateToDetail: (Long) -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier
    ) {
        if (comicList.isNotEmpty()) {
            HomeContent(
                comicList = comicList,
                modifier = modifier,
                navigateToDetail = navigateToDetail,
            )
        } else {
            Box (
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.White)
            ) {
                Column (
                    modifier = Modifier
                        .align(Alignment.Center)
                ){
                    Text(text = "There is no favorite comic")
                }
            }

        }
    }
}



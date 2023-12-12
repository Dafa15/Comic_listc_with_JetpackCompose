    package com.example.comislist.ui.screen
    
    import androidx.annotation.DrawableRes
    import androidx.compose.foundation.Image
    import androidx.compose.foundation.background
    import androidx.compose.foundation.clickable
    import androidx.compose.foundation.layout.Arrangement
    import androidx.compose.foundation.layout.Box
    import androidx.compose.foundation.layout.Column
    import androidx.compose.foundation.layout.Row
    import androidx.compose.foundation.layout.Spacer
    import androidx.compose.foundation.layout.fillMaxSize
    import androidx.compose.foundation.layout.fillMaxWidth
    import androidx.compose.foundation.layout.height
    import androidx.compose.foundation.layout.padding
    import androidx.compose.foundation.layout.size
    import androidx.compose.foundation.rememberScrollState
    import androidx.compose.foundation.shape.RoundedCornerShape
    import androidx.compose.foundation.verticalScroll
    import androidx.compose.material.icons.Icons
    import androidx.compose.material.icons.filled.ArrowBack
    import androidx.compose.material.icons.filled.Favorite
    import androidx.compose.material.icons.filled.FavoriteBorder
    import androidx.compose.material3.FloatingActionButton
    import androidx.compose.material3.Icon
    import androidx.compose.material3.MaterialTheme
    import androidx.compose.material3.Text
    import androidx.compose.runtime.Composable
    import androidx.compose.runtime.collectAsState
    import androidx.compose.ui.Alignment
    import androidx.compose.ui.Modifier
    import androidx.compose.ui.draw.clip
    import androidx.compose.ui.graphics.Color
    import androidx.compose.ui.graphics.Color.Companion.Gray
    import androidx.compose.ui.graphics.Color.Companion.LightGray
    import androidx.compose.ui.graphics.Color.Companion.White
    import androidx.compose.ui.layout.ContentScale
    import androidx.compose.ui.res.painterResource
    import androidx.compose.ui.res.stringResource
    import androidx.compose.ui.text.font.FontWeight
    import androidx.compose.ui.text.style.TextAlign
    import androidx.compose.ui.unit.dp
    import androidx.compose.ui.unit.sp
    import androidx.lifecycle.viewmodel.compose.viewModel
    import com.example.comislist.R
    import com.example.comislist.common.UiState
    import com.example.comislist.di.Injection
    import com.example.comislist.viewmodel.DetailViewModel
    import com.example.comislist.viewmodel.ViewModelFactory

    @Composable
    fun DetailScreen (
        comicId: Long,
        viewModel: DetailViewModel = viewModel(
            factory = ViewModelFactory(
                Injection.provideRepository()
            )
        ),
        navigateBack: () -> Unit,
    ){
        viewModel.uiState.collectAsState(initial = UiState.Loading).value.let { uiState ->
            when (uiState) {
                is UiState.Loading -> {
                    viewModel.getComicById(comicId)
                }
                is UiState.Success -> {
                    val data = uiState.data
                    DetailContent(
                        data.id,
                        data.image,
                        data.name,
                        data.genre,
                        data.author,
                        data.description,
                        data.score,
                        data.isFavorite,
                        onBackClick = navigateBack,
                        onFavoriteIconClicked = { id, newState ->
                            viewModel.updateComic(id, newState)
                        },
                    )
                }
                is UiState.Error -> {}
            }
        }
    }
    
    @Composable
    fun DetailContent(
        id: Long,
        @DrawableRes image: Int,
        name: String,
        genre: String,
        author: String,
        description: String,
        score: Double,
        isFavorite: Boolean,
        onBackClick: () -> Unit,
        onFavoriteIconClicked: (id: Int, newState: Boolean) -> Unit,
        ) {
        Box (modifier = Modifier.fillMaxSize()) {
            Column(modifier = Modifier
                .fillMaxWidth()) {
                Column(
                    modifier = Modifier
                        .verticalScroll(rememberScrollState())
                        .weight(1f)
                ) {
                    Row {
                        Icon(
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = stringResource(R.string.back),
                            modifier = Modifier
                                .padding(16.dp)
                                .clickable { onBackClick() }
                        )
                    }
                    Row (
                        modifier = Modifier
                            .fillMaxWidth()
                    ) {
                        Box (
                            modifier = Modifier
                                .padding(start = 16.dp, bottom = 16.dp)
                                .size(162.dp, 243.dp)
                        ){
                            Image(
                                painter = painterResource(image),
                                contentDescription = null,
                                contentScale = ContentScale.Crop,
                                modifier = Modifier
                                    .fillMaxSize()
                                    .clip(RoundedCornerShape(20.dp))
                            )
                        }
                        Column(
                            modifier = Modifier.padding(start = 16.dp, end = 16.dp, bottom = 32.dp)
                        ) {
                            Box (
                                modifier = Modifier.align(Alignment.CenterHorizontally)
                            ){
                                Text(
                                    text = name,
                                    textAlign = TextAlign.Center,
                                    style = MaterialTheme.typography.headlineSmall.copy(
                                        fontWeight = FontWeight.Bold
                                    )
    
                                )
                            }
                            Spacer(Modifier.height(8.dp))
                            Box (
                                modifier = Modifier
                                    .align(Alignment.CenterHorizontally)
                                    .background(Color(0xFF804BDF))
                                    .padding(start = 16.dp, end = 16.dp, top = 3.dp, bottom = 3.dp)
                            ) {
                                Text(
                                    text = "SCORE",
                                    fontWeight = FontWeight.ExtraBold,
                                    color = White
                                )
                            }
                            Box (
                                modifier = Modifier
                                    .align(Alignment.CenterHorizontally)
                                    .padding(start = 16.dp, end = 16.dp, top = 3.dp, bottom = 3.dp)
                            ) {
                                Text(
                                    text = score.toString(),
                                    fontWeight = FontWeight.ExtraBold,
                                    fontSize = 36.sp
                                )
                            }
                            Spacer(Modifier.height(8.dp))
                            Row {
                                Text(
                                    text = "Genre: ",
                                    textAlign = TextAlign.Justify,
                                    color = Gray,
                                    fontWeight = FontWeight.ExtraBold,
                                )
                                Text(
                                    text = genre,
                                    textAlign = TextAlign.Justify,
                                )
                            }
    
                            Spacer(Modifier.height(8.dp))
                            Row {
                                Text(
                                    text = "Author: ",
                                    textAlign = TextAlign.Justify,
                                    color = Gray,
                                    fontWeight = FontWeight.ExtraBold,
                                )
                                Text(
                                    text = author,
                                    textAlign = TextAlign.Justify,
                                )
                            }
                        }
                    }
                    Row (
                        horizontalArrangement = Arrangement.Center,
                        modifier = Modifier
                            .fillMaxWidth()
                            .align(Alignment.CenterHorizontally)
                            .background(LightGray)
                            .padding(8.dp)
                    ) {
                        Text(
                            text = "Description",
                            fontWeight = FontWeight.Bold,
                            textAlign = TextAlign.Center,
                            modifier = Modifier
                        )
                    }
                    Text(
                        text = description,
                        modifier = Modifier.padding(16.dp)
                    )
                }
            }
            FloatingActionButton(
                onClick = { onFavoriteIconClicked(id.toInt(), !isFavorite)},
                modifier = Modifier
                    .align(Alignment.BottomEnd)
                    .padding(16.dp)
            )
            {
                Icon(imageVector = if (isFavorite) Icons.Default.Favorite else Icons.Default.FavoriteBorder,
                    contentDescription = "Favorite")
            }
        }

    }

package com.shakib.movieapp.android.ui.popular

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.items
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.annotation.RootNavGraph
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import com.shakib.movieapp.android.MyApplicationTheme
import com.shakib.movieapp.android.R
import com.shakib.movieapp.android.ui.destinations.FavoriteViewDestination
import com.shakib.movieapp.android.ui.destinations.MovieDetailViewDestination
import com.shakib.movieapp.android.ui.detail.map
import com.shakib.movieapp.data.dto.Movie

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@RootNavGraph(start = true)
@Destination
@Composable
fun PopularView(navigator: DestinationsNavigator, viewModel: PopularViewModel) {
    Scaffold(
        topBar = {
            TopAppBar(
                backgroundColor = MaterialTheme.colors.background,
                title = {
                    Text(
                        stringResource(R.string.popular),
                        color = MaterialTheme.colors.onSurface,
                        style = MaterialTheme.typography.body1,
                        fontSize = 32.sp
                    )
                },
                modifier = Modifier.safeDrawingPadding(),
                elevation = 0.dp,
                actions = {
                    IconButton(
                        onClick = {
                            navigator.navigate(FavoriteViewDestination())
                        }
                    ) {
                        Icon(
                            painterResource(R.drawable.ic_star_fill),
                            contentDescription = "Favorite",
                            tint = Color(0xFFFFD81A),
                            modifier = Modifier
                                .size(44.dp)
                                .padding(8.dp)
                        )
                    }
                }
            )
        }
    ) {
        val list = viewModel.movieList.collectAsLazyPagingItems()

        LazyColumn(
            modifier = Modifier.fillMaxWidth(),
            contentPadding = PaddingValues(4.dp)
        ) {
            items(list) { movie: Movie? ->
                movie?.let {
                    PopularItemView(it, viewModel) {
                        navigator.navigate(MovieDetailViewDestination(movie = movie.map()))
                    }
                }
            }

            list.apply {
                if (loadState.append is LoadState.Loading) {
                    item {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.Center
                        ) {
                            CircularProgressIndicator(
                                color = MaterialTheme.colors.primaryVariant,
                                strokeWidth = 4.dp
                            )
                        }
                    }
                }
            }
        }
    }
}

@Preview
@Composable
fun PopularPreview() {
    MyApplicationTheme {
        //PopularView()
    }
}
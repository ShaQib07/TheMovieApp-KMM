package com.shakib.movieapp.android.ui.favorite

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import com.shakib.movieapp.android.MyApplicationTheme
import com.shakib.movieapp.android.R
import com.shakib.movieapp.android.ui.destinations.MovieDetailViewDestination
import com.shakib.movieapp.android.ui.detail.map
import com.shakib.movieapp.android.ui.popular.PopularItemView
import com.shakib.movieapp.android.ui.popular.PopularViewModel

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Destination
@Composable
fun FavoriteView(navigator: DestinationsNavigator, viewModel: PopularViewModel) {
    Scaffold(
        topBar = {
            TopAppBar(
                backgroundColor = MaterialTheme.colors.background,
                title = {
                    Text(
                        stringResource(R.string.favorite),
                        color = MaterialTheme.colors.onSurface,
                        style = MaterialTheme.typography.body1
                    )
                },
                navigationIcon = {
                    IconButton(onClick = { navigator.navigateUp() }) {
                        Icon(
                            painterResource(R.drawable.ic_back),
                            contentDescription = "Back",
                        )
                    }
                },
                elevation = 0.dp,
                modifier = Modifier.safeDrawingPadding()
            )
        }
    ) {
        val list = viewModel.favoriteList

        LazyColumn(
            modifier = Modifier.fillMaxWidth(),
            contentPadding = PaddingValues(4.dp)
        ) {
            items(list) { movie ->
                PopularItemView(movie = movie, viewModel = viewModel) {
                    navigator.navigate(MovieDetailViewDestination(movie = movie.map()))
                }
            }
        }
    }
}

@Preview
@Composable
fun FavoriteViewPreview() {
    MyApplicationTheme {
        //FavoriteView()
    }
}

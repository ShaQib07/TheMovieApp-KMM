package com.shakib.movieapp.android.ui.popular

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.items
import com.shakib.movieapp.android.MyApplicationTheme
import com.shakib.movieapp.android.R
import com.shakib.movieapp.data.dto.Movie
import org.koin.androidx.compose.getViewModel

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun PopularView(viewModel: PopularViewModel = getViewModel()) {

    Scaffold(
        topBar = {
            TopAppBar(
                backgroundColor = MaterialTheme.colors.primaryVariant,
                title = { Text(stringResource(R.string.popular)) }
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
                    PopularItemView(movie = it)
                }
            }

            list.apply {
                if (loadState.append is LoadState.Loading) {
                    item {
                        Row(modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.Center) {
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
        PopularView()
    }
}
package com.shakib.movieapp.android.ui.popular

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.shakib.movieapp.android.MyApplicationTheme
import com.shakib.movieapp.android.R
import com.shakib.movieapp.data.dto.Movie
import com.shakib.movieapp.data.dto.getBackdrop
import org.koin.androidx.compose.getViewModel

@Composable
fun PopularItemView(movie: Movie, viewModel: PopularViewModel, onClick: () -> Unit) {
    var isFavorite = viewModel.checkFavorite(movie)
    val favIcon = remember {
        mutableStateOf(if (isFavorite) R.drawable.ic_star_fill else R.drawable.ic_star)
    }

    Card(
        modifier = Modifier
            .padding(5.dp)
            .fillMaxWidth()
            .wrapContentHeight()
            .clickable { onClick.invoke() },
        shape = RoundedCornerShape(8.dp),
        elevation = 8.dp,
        backgroundColor = MaterialTheme.colors.surface

    ) {
        AsyncImage(
            model = movie.getBackdrop(),
            contentDescription = movie.title,
            modifier = Modifier.size(220.dp),
            contentScale = ContentScale.Crop
        )

        Box(
            modifier = Modifier
                .size(220.dp)
                .background(
                    brush = Brush.verticalGradient(
                        colors = listOf(
                            Color.Transparent,
                            Color.Transparent,
                            Color(0xFF1F2023)
                        )
                    )
                )
        ) {
            IconButton(
                modifier = Modifier.align(Alignment.TopEnd),
                onClick = {
                    isFavorite = !isFavorite
                    if (isFavorite) {
                        viewModel.addToFavorite(movie)
                        favIcon.value = R.drawable.ic_star_fill
                    } else {
                        viewModel.removeFromFavorite(movie)
                        favIcon.value = R.drawable.ic_star
                    }
                }) {
                Icon(
                    painterResource(id = favIcon.value),
                    contentDescription = "Favorite",
                    tint = Color(0xFFFFD81A),
                    modifier = Modifier
                        .size(44.dp)
                        .padding(8.dp)
                )
            }

            Text(
                text = movie.title,
                textAlign = TextAlign.Center,
                color = Color.White,
                style = MaterialTheme.typography.body1,
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .padding(8.dp)
            )
        }
    }
}

@Preview
@Composable
fun PopularItemPreview() {
    MyApplicationTheme {
        PopularItemView(Movie(), getViewModel()) {}
    }
}

package com.shakib.movieapp.android.ui.popular

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.shakib.movieapp.android.MyApplicationTheme
import com.shakib.movieapp.data.dto.Movie
import com.shakib.movieapp.data.dto.getBackdrop

@Composable
fun PopularItemView(movie: Movie) {
    Card(
        modifier = Modifier
            .padding(4.dp)
            .fillMaxWidth()
            .wrapContentHeight(),
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
            modifier = Modifier.size(220.dp)
                .background(
                    brush = Brush.verticalGradient(
                        colors = listOf(
                            Color.Transparent,
                            Color.Transparent,
                            Color.Black
                        )
                    )
                )
        ) {
            Text(
                text = movie.title,
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.body1,
                modifier = Modifier.align(Alignment.BottomCenter)
                    .padding(8.dp)
            )
        }
    }
}

@Preview
@Composable
fun PopularItemPreview() {
    MyApplicationTheme {
        PopularItemView(Movie())
    }
}

package com.shakib.movieapp.android.ui.detail

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.shakib.movieapp.android.MyApplicationTheme
import com.shakib.movieapp.data.dto.Movie
import com.shakib.movieapp.data.dto.getPoster

@Composable
fun RecommendedItemView(movie: Movie) {

    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Card(
            modifier = Modifier
                .width(200.dp)
                .height(300.dp)
                .padding(6.dp),
            shape = RoundedCornerShape(8.dp),
            elevation = 8.dp,
            backgroundColor = MaterialTheme.colors.surface

        ) {
            AsyncImage(
                model = movie.getPoster(),
                contentDescription = movie.title,
                contentScale = ContentScale.Crop
            )
        }

        Text(
            text = movie.title,
            textAlign = TextAlign.Center,
            fontWeight = FontWeight.Light,
            fontSize = 16.sp,
            modifier = Modifier
                .padding(8.dp)
        )
    }
}

@Preview
@Composable
fun PopularItemPreview() {
    MyApplicationTheme {
        RecommendedItemView(Movie())
    }
}

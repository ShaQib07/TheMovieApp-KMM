package com.shakib.movieapp.android.ui.detail

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import com.shakib.movieapp.android.MyApplicationTheme
import com.shakib.movieapp.android.R
import org.koin.androidx.compose.getViewModel

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Destination
@Composable
fun MovieDetailView(
    navigator: DestinationsNavigator,
    movie: MovieUI,
    viewModel: MovieDetailViewModel = getViewModel()
) {
    rememberSystemUiController().setSystemBarsColor(
        color = Color.Transparent,
        darkIcons = true
    )

    LaunchedEffect(Unit) { viewModel.getRecommendations(movie.id) }

    Scaffold {
        val list = viewModel.movieList

        Column(modifier = Modifier.verticalScroll(rememberScrollState())) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(LocalConfiguration.current.screenHeightDp.dp * 75 / 100)
            ) {
                AsyncImage(
                    model = movie.poster_path,
                    contentDescription = movie.title,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.fillMaxSize()
                )

                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(
                            brush = Brush.verticalGradient(
                                colors = listOf(
                                    Color(0xBFFFFFFF),
                                    Color.Transparent,
                                    Color.Transparent
                                )
                            )
                        )
                ) {
                    IconButton(onClick = { navigator.navigateUp() }) {
                        Icon(
                            painterResource(R.drawable.ic_back),
                            contentDescription = "Back",
                            modifier = Modifier.padding(vertical = 40.dp, horizontal = 12.dp)
                        )
                    }

                    Text(
                        text = movie.title,
                        fontSize = 18.sp,
                        fontFamily = FontFamily.Default,
                        fontWeight = FontWeight.Medium,
                        textAlign = TextAlign.Center,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(40.dp)
                    )
                }
            }

            Text(
                text = movie.title.uppercase(),
                letterSpacing = 2.sp,
                fontSize = 28.sp,
                fontFamily = FontFamily.Default,
                fontWeight = FontWeight.Black,
                modifier = Modifier.padding(start = 16.dp, top = 8.dp)
            )

            Text(
                text = movie.overview,
                fontSize = 16.sp,
                fontFamily = FontFamily.Default,
                fontWeight = FontWeight.Light,
                lineHeight = 20.sp,
                modifier = Modifier.padding(horizontal = 12.dp, vertical = 4.dp)
            )

            Text(
                text = "RECOMMENDED MOVIES",
                fontSize = 20.sp,
                fontFamily = FontFamily.Default,
                fontWeight = FontWeight.Black,
                modifier = Modifier.padding(start = 16.dp, top = 8.dp)
            )

            LazyRow(
                modifier = Modifier.fillMaxWidth(),
                contentPadding = PaddingValues(4.dp)
            ) {
                items(list) { movie ->
                    RecommendedItemView(movie = movie)
                }
            }
        }
    }
}

@Preview
@Composable
fun MovieDetailPreview() {
    MyApplicationTheme {
        //MovieDetailView()
    }
}

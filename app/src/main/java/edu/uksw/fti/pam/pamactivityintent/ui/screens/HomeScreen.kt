package edu.uksw.fti.pam.pamactivityintent.ui.screens

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.base.R
import coil.compose.rememberImagePainter
import coil.size.Scale
import coil.transform.CircleCropTransformation
import edu.uksw.fti.pam.pamactivityintent.models.AnimeModel
import edu.uksw.fti.pam.pamactivityintent.models.AnimeViewModel
import edu.uksw.fti.pam.pamactivityintent.models.TodosViewModel
import edu.uksw.fti.pam.pamactivityintent.ui.theme.PAMActivityIntentTheme
import kotlin.math.log


@Composable
fun HomeScreen(name: String) {
    Text(text = "Hello $name!")
}

//@Preview(showBackground = true)
//@Composable
//fun DefaultPreview() {
//    val avm = AnimeViewModel()
//    val navController =
//    PAMActivityIntentTheme {
//        MainScreenView(avm)
//    }
//}

@Composable
fun MainScreenView(
    avm: AnimeViewModel,
    navController: NavController
) {
    LaunchedEffect(
        Unit,
        block = {
            avm.getAnimeList()
        }
    )
    Column {



        when {
            avm.errorMessage.isEmpty() -> {
                AvmList(avl = avm.animeList) {animeId,animeTitle,animeImgUrl, animeGenre, animeDeskripsi ->
                    Log.d("ClickItem", "this is anime id: $animeId")
                    navController.navigate("Detail?id=$animeId?title=$animeTitle?imgUrl=$animeImgUrl?genre=$animeGenre?Deskripsi=$animeDeskripsi")
                }

//                val random = (0..avm.animeList.size).random()
                Log.d("hero-item", "${avm.animeList.size}")



            }
            else -> Log.e("AVM", "Something happened")
        }


        when {

            avm.animeList.size>0 -> {
                val random = (0..avm.animeList.size).random()
                AvmHero(avm.animeList[random]) {id, name ->
                    Log.d("ClickItem", "this is anime id: $id")
                    navController.navigate("Detail?id=$id?name=$name")
                }
                Log.d("hero-item", "${avm.animeList[random]}")
            }

        }


        if (avm.errorMessage.isEmpty()) {
            LazyVerticalGrid(
                columns = GridCells.Adaptive(120.dp)
            ) {
                items(avm.animeList.size) { index ->
//                    Text(text = avm.animeList[index].title)
//                    Text(text = avm.animeList[index].imgUrl)
//                    Text(text = avm.animeList[index].Deskripsi)



                    Box(
                        modifier = Modifier
                            .padding(8.dp, 4.dp)
                            .fillMaxWidth()
                            .height(110.dp)
                    ) {
                        Surface() {

                            Row(
                                Modifier
                                    .padding(4.dp)
                                    .fillMaxSize()
                            ) {

                                Image(
                                    painter = rememberImagePainter(
                                        data = avm.animeList[index].imgUrl,

                                        builder = {
                                            scale(Scale.FILL)
                                            placeholder(R.drawable.notification_action_background)
                                            transformations(CircleCropTransformation())

                                        }
                                    ),
                                    contentDescription = avm.animeList[index].Deskripsi,
                                    modifier = Modifier
                                        .fillMaxHeight()
                                        .weight(0.2f)
                                )


                                Column(
                                    verticalArrangement = Arrangement.Center,
                                    modifier = Modifier
                                        .padding(4.dp)
                                        .fillMaxHeight()
                                        .weight(0.8f)
                                ) {
                                    Text(
                                        text = avm.animeList[index].title,
                                        style = MaterialTheme.typography.subtitle1,
                                        fontWeight = FontWeight.Bold
                                    )
                                    Text(
                                        text = avm.animeList[index].genre,
                                        style = MaterialTheme.typography.caption,
                                        modifier = Modifier
                                            .background(
                                                Color.LightGray
                                            )
                                            .padding(4.dp)
                                    )
                                    Text(
                                        text = avm.animeList[index].Deskripsi,
                                        style = MaterialTheme.typography.body1,
                                        maxLines = 2,
                                        overflow = TextOverflow.Ellipsis
                                    )

                                }
                            }
                        }
                    }
                }
            }
        }
        else {
            Text(text = avm.errorMessage)
        }
    }
}


@Composable
fun AvmHero(anime: AnimeModel, itemClick: (index: Int, title: String)-> Unit){
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp)
            .clickable { itemClick(anime.id, anime.title) }
    ){
        Row(modifier = Modifier
            .fillMaxWidth()
            .padding(5.dp)
        ) {
            Image(
                painter = rememberImagePainter(
                    data = anime.imgUrl,
                    builder = {
                        scale(Scale.FILL)
                        placeholder(R.drawable.notification_action_background)
                        transformations(CircleCropTransformation())
                    }
                ),
                contentDescription = anime.Deskripsi,
                modifier = Modifier
                    .fillMaxHeight()
                    .weight(0.2f)
            )
            Column(modifier = Modifier
                .padding(4.dp)
                .fillMaxHeight()
                .weight(0.8f),
                verticalArrangement = Arrangement.Center
            ) {
                Text(text = anime.title)
                Text(
                    text = anime.title,
                    style = MaterialTheme.typography.subtitle1,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = anime.genre,
                    style = MaterialTheme.typography.caption,
                    modifier = Modifier
                        .background(
                            Color.LightGray
                        )
                        .padding(4.dp)
                )
                Text(
                    text = anime.Deskripsi,
                    style = MaterialTheme.typography.body1,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis
                )
            }
        }
    }
}

@Composable
fun AvmList(avl: List<AnimeModel>, itemClick: (index: Int, title: String, imgUrl: String, genre: String, Deskripsi:String)-> Unit) {
    Box(modifier = Modifier
        .fillMaxWidth()
        .padding(10.dp)
    ) {
        LazyRow(modifier = Modifier
            .fillMaxWidth(),
            state = rememberLazyListState()
        ) {
            itemsIndexed(avl) {index, item ->
                Card(modifier = Modifier
                    .width(250.dp)
                    .height(150.dp)
                    .padding(5.dp)
                    .clickable {
                        itemClick(item.id, item.title, item.imgUrl, item.genre, item.Deskripsi)
                    },
                ) {
                    Row(modifier = Modifier
                        .fillMaxWidth()
                        .padding(5.dp),
//                        horizontalArrangement = Arrangement.Start
                    ) {
                        Image(
                            painter = rememberImagePainter(
                                data = item.imgUrl,
                                builder = {
                                    scale(Scale.FILL)
                                    placeholder(R.drawable.notification_action_background)
                                    transformations(CircleCropTransformation())
                                }
                            ),
                            contentDescription = item.Deskripsi,
                            modifier = Modifier
                                .fillMaxHeight()
                                .weight(0.2f)
                        )
                        Column(modifier = Modifier
                            .padding(4.dp)
                            .fillMaxHeight()
                            .weight(0.8f),
                            verticalArrangement = Arrangement.Center
                        ) {
                            Text(text = item.title)
                            Text(
                                text = item.title,
                                style = MaterialTheme.typography.subtitle1,
                                fontWeight = FontWeight.Bold
                            )
                            Text(
                                text = item.genre,
                                style = MaterialTheme.typography.caption,
                                modifier = Modifier
                                    .background(
                                        Color.LightGray
                                    )
                                    .padding(4.dp)
                            )
                            Text(
                                text = item.Deskripsi,
                                style = MaterialTheme.typography.body1,
                                maxLines = 2,
                                overflow = TextOverflow.Ellipsis
                            )
                        }
                    }
                }
            }
        }
    }
}

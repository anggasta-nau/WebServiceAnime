package edu.uksw.fti.pam.pamactivityintent.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import coil.compose.base.R
import coil.compose.rememberImagePainter
import coil.size.Scale
import coil.transform.CircleCropTransformation

@Composable
fun DetailScreen(id: String?, title: String?,imgUrl: String?, genre: String?, Deskripsi: String? ) {



    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier.fillMaxSize()
    ) {
        Column() {
            Image(
                painter = rememberImagePainter(
                    data = "$imgUrl",
                    builder = {
                        scale(Scale.FIT)
                    }
                ),
                contentDescription = "$Deskripsi",
//                modifier = Modifier
//                    .fillMaxHeight()
//                    .weight(0.2f)
            )
            Text(text = "Id = , $id")

            Text(text = "Title = , $title")
            Text(text = "Id = , $genre")

            Text(text = "Title = , $Deskripsi")
        }

    }
}
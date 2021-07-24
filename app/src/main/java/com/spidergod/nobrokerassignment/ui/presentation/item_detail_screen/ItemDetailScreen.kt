package com.spidergod.nobrokerassignment.ui.presentation.item_detail_screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.rememberImagePainter
import com.spidergod.nobrokerassignment.data.parcelables.NoBrokerParcelable
import com.spidergod.nobrokerassignment.ui.theme.SunsetOrange

@Composable
fun ItemDetailScreen(navController: NavController, itemDetail: NoBrokerParcelable) {

    val scrollState = rememberScrollState()
    Scaffold(
        topBar = {
            DetailScreenTopBar(onBackPressed = {
                navController.popBackStack()
            })
        }
    ) {
        Box(modifier = Modifier.padding(it)) {
            Column(
                modifier = Modifier
                    .padding(horizontal = 20.dp)
                    .verticalScroll(state = scrollState, enabled = true)
            ) {
                Image(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(230.dp),
                    painter = rememberImagePainter(data = itemDetail.image),
                    contentDescription = itemDetail.title,
                    contentScale = ContentScale.Fit
                )
                Spacer(modifier = Modifier.size(17.dp))
                Text(text = itemDetail.title)
                Spacer(modifier = Modifier.size(20.dp))
                Text(text = itemDetail.subTitle)
            }
        }
    }

}

@Composable
fun DetailScreenTopBar(onBackPressed: () -> Unit) {
    TopAppBar(
        elevation = 0.dp,
        backgroundColor = Color.White,
        navigationIcon = {
            IconButton(onClick = {
                onBackPressed()
            }) {
                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = "Back button",
                    tint = SunsetOrange
                )
            }
        },
        title = {
            Text(text = "Item Detail")
        }
    )
}
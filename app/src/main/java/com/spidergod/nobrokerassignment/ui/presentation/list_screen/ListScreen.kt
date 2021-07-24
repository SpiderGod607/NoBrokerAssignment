package com.spidergod.nobrokerassignment.ui.presentation.list_screen

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.rememberImagePainter
import com.spidergod.nobrokerassignment.data.local.entity.NoBrokerEntity
import com.spidergod.nobrokerassignment.ui.components.LoadingShimmerAnimation
import com.spidergod.nobrokerassignment.util.Resource
import androidx.compose.runtime.livedata.observeAsState as observeAsState1


@Composable
fun ListScreen(
    viewModel: ListScreenViewModel = hiltViewModel()
) {
    Scaffold {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 25.dp, start = 15.dp, end = 15.dp),
        ) {
            Text(text = "Item List")
            Spacer(modifier = Modifier.size(15.dp))
            SearchBar(
                currentQuery = viewModel.currentSearchQuery.value,
                onQueryChange = { newQuery ->
                    viewModel.currentSearchQuery.value = newQuery
                }
            )
            val responseData by viewModel.responseData.observeAsState1()

            responseData?.let { it1 ->
                ListOfResponses(
                    responseData = it1,
                    viewModel.currentSearchQuery.value
                )
            }

        }

    }
}

@Composable
fun ListOfResponses(responseData: Resource<List<NoBrokerEntity>>, currentSearchQuery: String) {
    if (responseData is Resource.Loading && (responseData.data == null || responseData.data.isEmpty())) {
        LoadingShimmerAnimation()
    } else if (responseData is Resource.Error && (responseData.data == null || responseData.data.isEmpty())) {
        Box(contentAlignment = Alignment.Center) {
            Text(text = "Check you internet connection")
        }
    } else {
        ResponseLazyList(responseData = responseData, currentSearchQuery = currentSearchQuery)
    }
}

@Composable
fun ResponseLazyList(responseData: Resource<List<NoBrokerEntity>>, currentSearchQuery: String) {
    LazyColumn {
        item {
            Box(modifier = Modifier.height(10.dp))
        }
        items(responseData.data?.size ?: 0) { currentItemIndex ->
            val currentItem = responseData.data?.get(currentItemIndex)
            if (currentItem != null) {
                if (currentSearchQuery.isEmpty() || currentItem.title.contains(currentSearchQuery, ignoreCase = true) || currentItem.subTitle.contains(
                        currentSearchQuery,ignoreCase = true
                    )
                ) {
                    ResponseItem(data = currentItem)
                }
            }

        }
    }
}


@Composable
fun ResponseItem(
    data: NoBrokerEntity
) {
    Box(modifier = Modifier.padding(vertical = 6.5.dp)) {
        Box(
            modifier = Modifier
                .height(53.dp)
                .fillMaxWidth()
        ) {
            Row(modifier = Modifier.fillMaxSize()) {
                ResponseImageBox(imageUrl = data.image, title = data.title)
                Spacer(modifier = Modifier.size(16.dp))
                Column(
                    verticalArrangement = Arrangement.SpaceEvenly
                ) {
                    Text(text = data.title)
                    Text(text = data.subTitle, overflow = TextOverflow.Ellipsis, maxLines = 1)
                }
            }
        }
    }
}

@Composable
fun ResponseImageBox(imageUrl: String, title: String) {
    val painter = rememberImagePainter(data = imageUrl)
    Box(
        modifier = Modifier
            .height(53.dp)
            .width(72.dp)
    ) {
        Image(modifier = Modifier.fillMaxSize(), painter = painter, contentDescription = title)
    }
}

@Composable
fun SearchBar(
    currentQuery: String,
    onQueryChange: (String) -> Unit
) {
    Box(
        modifier = Modifier
            .height(35.dp)
            .fillMaxWidth()
            .border(
                border = BorderStroke(
                    width = 1.dp,
                    color = Color.Black
                ),
                shape = RoundedCornerShape(16.dp)
            ),
        contentAlignment = Alignment.CenterStart
    ) {
        Row {
            Spacer(modifier = Modifier.size(8.dp))
            Icon(imageVector = Icons.Default.Search, contentDescription = "Search Icon")
            Spacer(modifier = Modifier.size(5.dp))
            BasicTextField(value = currentQuery, onValueChange = { newText ->
                onQueryChange(newText)
            })
        }
    }
}
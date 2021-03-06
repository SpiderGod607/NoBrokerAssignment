package com.spidergod.nobrokerassignment.ui.presentation.list_screen

import android.os.Bundle
import androidx.compose.foundation.*
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.rememberImagePainter
import coil.transform.CircleCropTransformation
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import com.spidergod.nobrokerassignment.R
import com.spidergod.nobrokerassignment.data.local.entity.NoBrokerEntity
import com.spidergod.nobrokerassignment.data.parcelables.NoBrokerParcelable
import com.spidergod.nobrokerassignment.ui.components.LoadingShimmerAnimation
import com.spidergod.nobrokerassignment.util.Constants.ITEM_DETAIL_SCREEN
import com.spidergod.nobrokerassignment.util.Constants.ITEM_DETAIL_SCREEN_ARGUMENT
import com.spidergod.nobrokerassignment.util.Resource

@Composable
fun ListScreen(
    navController: NavController,
    viewModel: ListScreenViewModel = hiltViewModel()
) {

    val responseData = viewModel.results.value

    Scaffold {
        val isRefresing =
            rememberSwipeRefreshState(isRefreshing = (responseData is Resource.Loading))
        SwipeRefresh(
            state = isRefresing,
            onRefresh = {
                viewModel.getData()
            }
        ) {
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


                responseData?.let { it1 ->
                    ListOfResponses(
                        responseData = it1,
                        viewModel.currentSearchQuery.value,
                        onItemClick = { dataToSendToNextScreen ->
                            val parcelableData = NoBrokerParcelable(
                                image = dataToSendToNextScreen.image,
                                title = dataToSendToNextScreen.title,
                                subTitle = dataToSendToNextScreen.subTitle
                            )

                            NavigateToItemDetailScreen(
                                navController = navController,
                                data = parcelableData
                            )
                        }
                    )
                }
            }
        }


    }
}

fun NavigateToItemDetailScreen(navController: NavController, data: NoBrokerParcelable) {
    navController.currentBackStackEntry?.arguments =
        Bundle().apply { putParcelable(ITEM_DETAIL_SCREEN_ARGUMENT, data) }
    navController.navigate(ITEM_DETAIL_SCREEN)
}

@Composable
fun ListOfResponses(
    responseData: Resource<List<NoBrokerEntity>>,
    currentSearchQuery: String,
    onItemClick: (NoBrokerEntity) -> Unit
) {


    val verticalScrollSate = rememberScrollState()

    if (responseData is Resource.Loading && (responseData.data == null || responseData.data.isEmpty())) {
        LoadingShimmerAnimation()
    } else if (responseData is Resource.Error && (responseData.data == null || responseData.data.isEmpty())) {

        Box(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(state = verticalScrollSate), contentAlignment = Alignment.Center
        ) {
            Text(text = "Check you internet connection")
        }
    } else {
        ResponseLazyList(
            responseData = responseData,
            currentSearchQuery = currentSearchQuery,
            onItemClick = onItemClick
        )
    }
}

@Composable
fun ResponseLazyList(
    responseData: Resource<List<NoBrokerEntity>>,
    currentSearchQuery: String,
    onItemClick: (NoBrokerEntity) -> Unit
) {
    LazyColumn {
        item {
            Box(modifier = Modifier.height(10.dp))
        }
        items(responseData.data?.size ?: 0) { currentItemIndex ->
            val currentItem = responseData.data?.get(currentItemIndex)
            if (currentItem != null) {
                if (currentSearchQuery.isEmpty() || currentItem.title.contains(
                        currentSearchQuery,
                        ignoreCase = true
                    ) || currentItem.subTitle.contains(
                        currentSearchQuery, ignoreCase = true
                    )
                ) {
                    ResponseItem(data = currentItem, onItemClick = onItemClick)
                }
            }

        }
    }
}

@Composable
fun ResponseItem(
    data: NoBrokerEntity,
    onItemClick: (NoBrokerEntity) -> Unit
) {
    Box(modifier = Modifier
        .padding(vertical = 6.5.dp)
        .clickable {
            onItemClick(data)
        }) {
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
    Box(
        modifier = Modifier
            .height(53.dp)
            .width(72.dp)
    ) {
        Image(
            modifier = Modifier.fillMaxSize(),
            painter = rememberImagePainter(data = imageUrl,
                builder = {
                    crossfade(true)
                    placeholder(R.drawable.ic_baseline_image_24)
                    transformations(CircleCropTransformation())
                }),
            contentDescription = title
        )
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
            Box(
                contentAlignment = Alignment.CenterStart
            ) {
                BasicTextField(
                    modifier = Modifier
                        .fillMaxWidth(),
                    value = currentQuery,
                    onValueChange = { newText ->
                        onQueryChange(newText)
                    },
                )

                if (currentQuery.isEmpty()) {
                    Text(text = "Search Title or Description")
                }
            }
        }
    }
}
package com.spidergod.nobrokerassignment.ui.components

import androidx.compose.animation.core.*
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.dp


@Composable
fun ShimmerCard(
    colors: List<Color>,
    xShimmer: Float,
    yShimmer: Float,
    gradientWidth: Float,
) {
    val brush = Brush.linearGradient(
        colors = colors,
        start = Offset(x = xShimmer - gradientWidth, y = yShimmer - gradientWidth),
        end = Offset(x = xShimmer, y = yShimmer)
    )
    Box(
        Modifier.padding(vertical = 6.5.dp)
    ) {
        androidx.compose.material.Surface(
            shape = MaterialTheme.shapes.small
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(53.dp)
            ) {
                Row(modifier = Modifier.fillMaxSize()) {
                    Box(
                        modifier = Modifier
                            .height(53.dp)
                            .width(72.dp)
                            .background(brush = brush)
                    )
                    Spacer(modifier = Modifier.size(16.dp))
                    Column(
                        modifier = Modifier.fillMaxHeight(),
                        verticalArrangement = Arrangement.SpaceEvenly
                    ) {
                        Box(
                            modifier = Modifier
                                .height(12.dp)
                                .width(100.dp)
                                .background(brush = brush)
                        )
                        Box(
                            modifier = Modifier
                                .height(12.dp)
                                .width(200.dp)
                                .background(brush = brush)
                        )
                    }
                }
            }
        }
    }
}


@Composable
fun LoadingShimmerAnimation() {

    BoxWithConstraints(modifier = Modifier.fillMaxSize()) {
        val cardWidthPx = with(LocalDensity.current) { (maxWidth).toPx() }
        val cardHightPx = with(LocalDensity.current) { (maxHeight).toPx() }
        val gradientWidth: Float = (0.2f * cardHightPx)
        val infiniteTansition = rememberInfiniteTransition()
        val xCardShimmer = infiniteTansition.animateFloat(
            initialValue = 0f,
            targetValue = (cardWidthPx + gradientWidth),
            animationSpec = infiniteRepeatable(
                animation = tween(
                    durationMillis = 1300,
                    easing = LinearEasing,
                    delayMillis = 300,
                ),
                repeatMode = RepeatMode.Restart
            )
        )

        val yCardShimmer = infiniteTansition.animateFloat(
            initialValue = 0f,
            targetValue = (cardHightPx + gradientWidth),
            animationSpec = infiniteRepeatable(
                animation = tween(
                    durationMillis = 1300,
                    easing = LinearEasing,
                    delayMillis = 300,
                ),
                repeatMode = RepeatMode.Restart
            )
        )

        val colors = listOf(
            Color.LightGray.copy(alpha = .9f),
            Color.LightGray.copy(alpha = .3f),
            Color.LightGray.copy(alpha = .9f)
        )

        LazyColumn {
            items(10) {
                ShimmerCard(
                    colors = colors,
                    xShimmer = xCardShimmer.value,
                    yShimmer = yCardShimmer.value,
                    gradientWidth = gradientWidth
                )
            }
        }

    }


}



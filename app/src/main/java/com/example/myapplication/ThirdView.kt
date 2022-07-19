package com.example.myapplication

import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material.icons.outlined.ShoppingCart
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.myapplication.ui.theme.Purple40
import com.example.myapplication.ui.theme.White
import kotlinx.coroutines.launch
import kotlin.math.roundToInt

@Preview(showBackground = true)
@Composable
fun ThirdView() {
    ScrollListView()
}

@Composable
private fun ScrollListView() {
    val listState = rememberLazyListState()
    val coroutineScope = rememberCoroutineScope()
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = White)
    ) {
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            state = listState
        ) {
            for (i in 0..50) {
                item {
                    val (isClicked, setClicked) = remember { mutableStateOf(false) }
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(15.dp, 5.dp)
                            .clip(RoundedCornerShape(15.dp))
                            .background(
                                brush = Brush.verticalGradient(listOf(Color.Red, Color.Blue)),
                                alpha = 0.4f
                            )
                            .padding(25.dp, 45.dp)
                    ) {
                        Text(
                            text = "Index  $i !",
                            fontSize = 24.sp,
                            color = White,
                        )
                        IconButton(
                            modifier = Modifier
                                .align(Alignment.CenterEnd)
                                .fillParentMaxSize(0.2f),
                            onClick = { setClicked(!isClicked) }
                        ) {
                            Icon(
                                imageVector = if (isClicked) Icons.Filled.ShoppingCart else Icons.Outlined.ShoppingCart,
                                contentDescription = "",
                                modifier = Modifier
                                    .fillMaxSize(),
                                tint = Purple40
                            )
                        }
                    }
                }
            }
        }

        val (xOffset, setXOffset) = remember { mutableStateOf(0f) }
        val (yOffset, setYOffset) = remember { mutableStateOf(0f) }

        Button(
            modifier = Modifier
                .offset { IntOffset(xOffset.roundToInt(), yOffset.roundToInt()) }
                .align(Alignment.BottomEnd)
                .padding(10.dp)
                .pointerInput(Unit) {
                    detectDragGestures { _, distance ->
                        setXOffset(distance.x)
                        setYOffset(distance.y)
                    }
                },
            onClick = {
                coroutineScope.launch {
                    listState.animateScrollToItem(index = 0)
                }
            }) {
            Text(
                text = "TOP ↑",
                color = White
            )
        }

    }
}
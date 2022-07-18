package com.example.myapplication

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.myapplication.ui.theme.Purple40
import com.example.myapplication.ui.theme.White

@Preview(showBackground = true)
@Composable
fun SecondView() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Purple40)
    ) {
        Box(
            modifier = Modifier
                .align(Alignment.Center)
                .padding(20.dp, 10.dp)
                .border(width = 2.dp, color = Purple40, shape = CircleShape)
                .shadow(elevation = 25.dp, shape = CircleShape, clip = false)
                .padding(30.dp, 10.dp)
        ) {
            Text(
                text = "Second View!",
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.headlineLarge,
                color = White,
                modifier = Modifier.align(Alignment.Center)
            )
        }
    }
}
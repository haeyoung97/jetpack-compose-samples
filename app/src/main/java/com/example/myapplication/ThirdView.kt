package com.example.myapplication

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import com.example.myapplication.ui.theme.Pink80
import com.example.myapplication.ui.theme.White

@Preview(showBackground = true)
@Composable
fun ThirdView() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Pink80)
    ) {
        Text(
            text = "Third View!",
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.headlineLarge,
            color = White,
            modifier = Modifier.align(Alignment.Center)
        )
    }
}
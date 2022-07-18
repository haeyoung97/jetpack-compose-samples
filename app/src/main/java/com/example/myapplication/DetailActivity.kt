package com.example.myapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import com.example.myapplication.ui.theme.MyApplicationTheme
import com.example.myapplication.ui.theme.PurpleGrey80

class DetailActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        var idx = 0
        if (intent.hasExtra("idx")) {
            idx = intent.getIntExtra("idx", 0)
        }
        setContent {
            MyApplicationTheme {
                DetailView(onBack = { this.finish() }, idx)
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailView(onBack: () -> Unit, idx: Int) {
    Scaffold(
        topBar = {
            SmallTopAppBar(
                title = { Text(text = "Detail View") },
                navigationIcon = {
                    IconButton(
                        onClick = onBack
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_back),
                            contentDescription = "Go To Back"
                        )
                    }
                },
            )
        },
    ) { contentPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(PurpleGrey80)
                .padding(contentPadding),
            contentAlignment = Alignment.Center
        ) {
            Text(text = "Detail Contents $idx")
        }
    }
}
package com.example.myapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.myapplication.ui.theme.*

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
private fun DetailView(onBack: () -> Unit, idx: Int) {
    val (openDialog, setOpenDialog) = remember { mutableStateOf(false) }
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
                actions = {
                    BadgedBox(
                        badge = {
                            Badge(
                                containerColor = Color.Red,
                                contentColor = White,
                            ) {
                                Text(text = "+10")
                            }
                        },
                        modifier = Modifier
                            .padding(30.dp, 20.dp)
                    ) {
                        Icon(
                            imageVector = Icons.Filled.Notifications,
                            contentDescription = "Notifications",
                            tint = Purple40
                        )
                    }
                }
            )
        },
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(PurpleGrey80)
                .padding(20.dp, 10.dp),
            contentAlignment = Alignment.Center
        ) {
            Text(
                modifier = Modifier.align(Alignment.TopStart),
                text = "Detail Contents $idx"
            )
            if (openDialog)
                AlertDialog(
                    title = { Text(text = "Alert Dialog!") },
                    onDismissRequest = {
                        setOpenDialog(false)
                    },
                    confirmButton = {
                        Button(
                            onClick = {
                                setOpenDialog(false)
                            }
                        ) {
                            Text(text = "confirmButton !")
                        }
                    }
                )
            Button(
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .padding(20.dp, 10.dp)
                    .clip(RoundedCornerShape(5.dp)),
                onClick = { setOpenDialog(true) }
            ) {
                Text(text = "Open Dialog!")
            }

            FloatingActionButton(
                modifier = Modifier
                    .align(Alignment.BottomEnd)
                    .padding(0.dp, 30.dp),
                onClick = {

                },
                shape = CircleShape,
                containerColor = PurpleGrey40,
                contentColor = White,
            ) {
                Text(text = "+")
            }
        }
    }
}
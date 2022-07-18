package com.example.myapplication

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.widget.Toast
import androidx.compose.animation.animateColor
import androidx.compose.animation.core.*
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.myapplication.ui.theme.*


@Preview(showBackground = true)
@Composable
fun FirstView() {
    MyApplicationTheme {
        // Surface 는 요소를 감싸는 컨테이너로써의 역할을 수행합니다.
        Surface(
            modifier = Modifier.fillMaxSize(),
        ) {
            MaterialComponent()
        }
    }
}

@SuppressLint("UnusedTransitionTargetStateParameter")
@OptIn(ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class)
@Composable
private fun MaterialComponent() {
    val context = LocalContext.current
    MaterialTheme {
        Scaffold(
            topBar = {
                CenterAlignedTopAppBar(
                    title = {
                        Text(
                            text = "Compose App",
                            color = Purple40
                        )
                    },
                    navigationIcon = {
                        IconButton(onClick = {
                            Toast.makeText(
                                context,
                                "Clicked Menu!",
                                Toast.LENGTH_LONG
                            ).show()
                        }) {
                            IconView(id = R.drawable.ic_menu)
                        }
                    },
                    actions = {
                        BarComponent()
                    }
                )
            },
//            bottomBar = {
//                Row(
//                    modifier = Modifier
//                        .fillMaxWidth()
//                        .height(30.dp)
//                        .background(
//                            color = Purple80
//                        ),
//                    verticalAlignment = Alignment.CenterVertically,
//                    horizontalArrangement = Arrangement.SpaceEvenly
//                ) {
//                    Text(text = "Bottom Bar")
//                }
//            }
        ) { contentPadding ->
            val modifier = Modifier
                .padding(10.dp)
                .border(width = 2.dp, color = Purple40, shape = CircleShape)
                .padding(25.dp, 10.dp)
            Column(
                modifier = Modifier.fillMaxSize()
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(PurpleGrey80)
                        .weight(1f)
                        .padding(8.dp),
                    contentAlignment = Alignment.TopCenter
                ) {
                    LazyColumn(
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        for (idx: Int in 1..10)
                            item {
                                IconHeartButton(
                                    context,
                                    contentPadding,
                                    idx,
                                    modifier.fillMaxWidth()
                                )
                            }
                    }
                }
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(Pink80)
                        .weight(1f)
                        .padding(8.dp),
                    contentAlignment = Alignment.Center
                ) {
                    LazyVerticalGrid(
                        cells = GridCells.Fixed(2),
                    ) {
                        for (idx: Int in 1..10)
                            item {
                                IconHeartButton(context, contentPadding, idx, modifier)
                            }
                    }
                }
            }
        }
    }
}

@SuppressLint("UnusedTransitionTargetStateParameter")
@Composable
private fun IconHeartButton(context: Context, contentPadding: PaddingValues, idx: Int, modifier: Modifier) {
    val (isChecked, setChecked) = remember { mutableStateOf(false) }
    Row(
        modifier = modifier.clickable {
            val intent = Intent(context, DetailActivity::class.java)
            intent.putExtra("idx", idx)
            context.startActivity(intent)
        },
        verticalAlignment = Alignment.CenterVertically
    ) {
        IconToggleButton(
            checked = isChecked,
            onCheckedChange = {
                setChecked(it)
            }
        ) {
            val transition = updateTransition(isChecked, label = "Checked indicator")
            val tint by transition.animateColor(
                label = "Tint"
            ) { isChecked ->
                if (isChecked) Pink40 else Purple40
            }

            val size by transition.animateDp(
                transitionSpec = {
                    if (false isTransitioningTo true) {
                        keyframes {
                            durationMillis = 250
                            30.dp at 0 with LinearOutSlowInEasing // for 0-15 ms
                            35.dp at 15 with FastOutLinearInEasing // for 15-75 ms
                            40.dp at 75 // ms
                            35.dp at 150 // ms
                        }
                    } else {
                        spring(stiffness = Spring.StiffnessVeryLow)
                    }
                },
                label = "Size"
            ) { 30.dp }

            Icon(
                imageVector = if (isChecked) Icons.Filled.Favorite else Icons.Filled.FavoriteBorder,
                contentDescription = null,
                tint = tint,
                modifier = Modifier.size(size)
            )
        }
        Column(
            modifier = Modifier
                .align(Alignment.CenterVertically)
                .padding(contentPadding),
            horizontalAlignment = Alignment.Start,
        ) {
            Text(text = "HEART $idx", modifier = Modifier.padding(contentPadding), fontSize = 15.sp)
            Spacer(
                modifier = Modifier
                    .height(3.dp)
                    .width(IntrinsicSize.Max)
            )
            Text(
                text = "I Pick U",
                modifier = Modifier.padding(contentPadding),
                fontSize = 12.sp
            )
        }
    }
}

@Composable
private fun IconView(id: Int) {
    Icon(painter = painterResource(id = id), contentDescription = "image resource")
}

@Composable
private fun BarComponent() {
    val context = LocalContext.current
    MyApplicationTheme {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            IconButton(onClick = {
                Toast.makeText(
                    context,
                    "Clicked Notification!",
                    Toast.LENGTH_LONG
                ).show()
            }) {
                IconView(id = R.drawable.ic_notifications)
            }
            IconButton(onClick = {
                Toast.makeText(
                    context,
                    "Clicked MyPage!",
                    Toast.LENGTH_LONG
                ).show()
            }) {
                IconView(id = R.drawable.ic_person)
            }
        }
    }
}
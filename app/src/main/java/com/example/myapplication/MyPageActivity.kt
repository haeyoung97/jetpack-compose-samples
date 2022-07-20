package com.example.myapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.unit.dp
import com.example.myapplication.ui.theme.*

class MyPageActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApplicationTheme {
                val (elements, setElements) = remember {
                    mutableStateOf(
                        listOf(
                            ChipState("item1", mutableStateOf(false)),
                            ChipState("item2", mutableStateOf(false)),
                            ChipState("item3", mutableStateOf(false)),
                            ChipState("item4", mutableStateOf(false))
                        )
                    )
                }
                Column(
                    modifier = Modifier.padding(10.dp),
                ) {
                    ChipsGroupView(
                        modifier = Modifier.padding(8.dp),
                        elements = elements,
                        onChipClicked = { _, _, idx ->
                            elements[idx].isSelected.value = !elements[idx].isSelected.value
                        },
                        onDeleteButtonClicked = { _, _, _ ->

                        }
                    )
                }
            }
        }
    }
}

data class ChipState(
    val text: String,
    val isSelected: MutableState<Boolean>
)

@Composable
private fun ChipsGroupView(
    modifier: Modifier = Modifier,
    elements: List<ChipState>,
    onChipClicked: (String, Boolean, Int) -> Unit,
    onDeleteButtonClicked: (String, Boolean, Int) -> Unit
) {
    LazyRow(modifier = modifier) {
        items(elements.size) { idx ->
            ChipItem(
                text = elements[idx].text,
                selected = elements[idx].isSelected.value,
                onChipClicked = { content, isSelected ->
                    onChipClicked(content, isSelected, idx)
                },
                onDeleteButtonClicked = { content, isSelected ->
                    onDeleteButtonClicked(content, isSelected, idx)
                }
            )
            Spacer(modifier = Modifier.padding(8.dp))
        }
    }
}

@Composable
private fun ChipItem(
    text: String,
    selected: Boolean,
    modifier: Modifier = Modifier,
    onChipClicked: (String, Boolean) -> Unit,
    onDeleteButtonClicked: (String, Boolean) -> Unit
) {
    Surface(
        color = when {
            selected -> Pink40
            else -> Pink80
        },
        contentColor = White,
        shape = RoundedCornerShape(16.dp),
        border = BorderStroke(
            width = 1.dp,
            color = when {
                selected -> Pink40
                else -> Pink80
            }
        ),
        modifier = modifier
    ) {
        Row(modifier = Modifier) {
            Text(
                text = text,
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier
                    .padding(8.dp)
                    .clickable { onChipClicked(text, selected) }
            )

            Image(
                imageVector = Icons.Default.Clear, contentDescription = "",
                modifier = Modifier
                    .padding(8.dp)
                    .size(20.dp)
                    .clickable { onDeleteButtonClicked(text, selected) },
                colorFilter = ColorFilter.tint(White)
            )
        }
    }

}
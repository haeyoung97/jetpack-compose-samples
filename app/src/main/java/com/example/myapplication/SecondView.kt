package com.example.myapplication

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.myapplication.ui.theme.Pink80
import com.example.myapplication.ui.theme.Purple40
import com.example.myapplication.ui.theme.White

@Preview(showBackground = true)
@Composable
fun SecondView() {
    val context = LocalContext.current
    val (id, setId) = remember { mutableStateOf("") }
    val (pwd, setPwd) = remember { mutableStateOf("") }
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Purple40)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(0.dp, 15.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Box(
                modifier = Modifier
                    .padding(20.dp, 10.dp)
                    .border(width = 2.dp, color = Purple40, shape = CircleShape)
                    .shadow(elevation = 25.dp, shape = CircleShape, clip = false)
                    .padding(45.dp, 10.dp)
            ) {
                Column(
                    modifier = Modifier.align(Alignment.Center),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Icon(
                        imageVector = Icons.Filled.CheckCircle,
                        contentDescription = "",
                        tint = Pink80
                    )
                    Text(
                        text = "Second View!",
                        textAlign = TextAlign.Center,
                        style = MaterialTheme.typography.headlineLarge,
                        color = White,
                    )
                }
            }

            Text(
                text = "INPUT : ID",
                modifier = Modifier
                    .padding(20.dp, 0.dp)
                    .align(Alignment.Start),
                fontSize = 18.sp,
                textAlign = TextAlign.Start,
                color = White
            )

            TextField(id, setId, true)

            Text(
                text = "INPUT : PWD",
                modifier = Modifier
                    .padding(20.dp, 0.dp)
                    .align(Alignment.Start),
                fontSize = 18.sp,
                textAlign = TextAlign.Start,
                color = White
            )

            TextField(pwd, setPwd, false)

        }

        Button(
            modifier = Modifier
                .padding(30.dp, 10.dp)
                .fillMaxWidth()
                .align(Alignment.BottomCenter)
                .clip(RoundedCornerShape(10.dp))
                .background(color = Pink80),
            contentPadding = PaddingValues(0.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Pink80),
            shape = RectangleShape,
            onClick = {
                Toast.makeText(context, "ID : $id, PWD : $pwd", Toast.LENGTH_LONG).show()
            },
        ) {
            Text(
                text = "CONFIRM !"
            )
        }
    }
}

@Composable
private fun TextField(value: String, setValue: (String) -> Unit, isVisible: Boolean = false) {
    BasicTextField(
        value = value,
        onValueChange = { setValue(it) },
        modifier = Modifier
            .padding(20.dp, 10.dp)
            .border(3.dp, color = Pink80, shape = CircleShape)
            .fillMaxWidth()
            .padding(20.dp, 10.dp),
        textStyle = TextStyle(
            color = White,
            fontSize = 16.sp,
            textAlign = TextAlign.Start,
        ),
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Email
        ),
        visualTransformation = if (isVisible) VisualTransformation.None else PasswordVisualTransformation(),
        cursorBrush = Brush.verticalGradient(listOf(White, White))
    )
}
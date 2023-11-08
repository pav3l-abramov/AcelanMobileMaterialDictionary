package com.example.acelanmobilematerials.Screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.sp
@Composable
fun Screen3(onClick: () -> Unit) {
    Column(modifier = Modifier.fillMaxSize()) {
        Text(
            text = "screen3",
            fontSize = 30.sp
        )
        Button(onClick = { onClick() }) {
            Text(text = "screen1")

        }

    }

}
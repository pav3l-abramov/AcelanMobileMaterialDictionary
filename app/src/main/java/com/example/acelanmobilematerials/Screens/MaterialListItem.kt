package com.example.acelanmobilematerials.Screens

import android.security.identity.AccessControlProfile
import android.text.Layout.Alignment
import androidx.compose.animation.AnimatedContentTransitionScope.SlideDirection.Companion.End
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.spring
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MaterialTheme.typography
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.DefaultShadowColor
import androidx.compose.ui.layout.layout
import androidx.compose.ui.modifier.modifierLocalMapOf
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.acelanmobilematerials.data.Material

@Composable
fun MaterialListItem(material: Material, navigateToProfile: (Material)->Unit) {

    val expanded = remember { mutableStateOf(false) }
    val extraPadding by animateDpAsState(
        if (expanded.value) 24.dp else 0.dp,
        animationSpec = spring(
            dampingRatio = Spring.DampingRatioMediumBouncy,
            stiffness = Spring.StiffnessLow
        ), label = ""
    )
    Card(
        modifier = Modifier
            .padding(horizontal = 8.dp, vertical = 8.dp),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 6.dp
        )
    ) {

        Row(modifier = Modifier.fillMaxWidth()) {
            Column(
                modifier = Modifier
                    .padding(16.dp)
                    .weight(1f)
                    .clickable { expanded.value = !expanded.value },

                ) {
                Text(
                    text = material.title, style = typography.headlineMedium,
                    color = MaterialTheme.colorScheme.primary
                )
            }
            Button(
                modifier= Modifier
                    .padding(16.dp)
                ,
                onClick = { navigateToProfile(material) }) {
                Text("Show table")
            }


        }
        if (expanded.value) {

            Column(
                modifier = Modifier.padding(16.dp)

            ) {
                Text(
                    text = (if (material.typeMat == 1) "d33=" + material.piezoelectricModulus[0].toString() else "isotrop"),
                    style = typography.labelLarge
                )
            }
        }

    }
}



package com.example.acelanmobilematerials.Screens

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.DarkGray
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.acelanmobilematerials.data.Material

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun ProfileScreen(material: Material) {
    val pad = 10;
    val width = with(LocalDensity.current) {
        ((LocalConfiguration.current.screenWidthDp - pad * 2) / 6)
    }
    if (material.typeMat == 1) {
        //для анизотропных материалов
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
        ) {
            item {
                ProfileHeader(material = material)
            }
            item {
                Text(text = "C", textAlign = TextAlign.Center)
                FlowRow(maxItemsInEachRow = 6, modifier = Modifier.padding(horizontal = pad.dp)) {
                    SampleContent("C", 6, 6, width, material.elasticModulus)
                }
            }
            item {
                Text(text = "e", textAlign = TextAlign.Center)
                FlowRow(
                    maxItemsInEachRow = 3,
                    modifier = Modifier.padding(horizontal = (1.5 * width + pad).dp)
                ) {
                    SampleContent("e", 3, 3, width, material.dielectricConstant)
                }
            }
            item {
                Text(text = "d", textAlign = TextAlign.Center)
                FlowRow(maxItemsInEachRow = 6, modifier = Modifier.padding(horizontal = pad.dp)) {
                    SampleContent("d", 3, 6, width, material.piezoelectricModulus)
                }
            }
        }
    }
    else
    {
        //для изотропных материалов
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
        ) {
            item {
                ProfileHeader(material = material)
            }
            item {
                Text(text = "C", textAlign = TextAlign.Center)
                FlowRow(maxItemsInEachRow = 2, modifier = Modifier.padding(horizontal = (width*2+ pad).dp)) {
                    SampleContent("C", 1, 2, width, material.elasticModulus)
                }
            }
            item {
                Text(text = "e", textAlign = TextAlign.Center)
                FlowRow(
                    maxItemsInEachRow = 1,
                    modifier = Modifier.padding(horizontal = (2.5 * width + pad).dp)
                ) {
                    SampleContent("e", 1, 1, width, material.dielectricConstant)
                }
            }
            item {
                Text(text = "d-not exist", textAlign = TextAlign.Center)
            }
        }

    }
}


@Composable
fun ProfileHeader(material: Material) {
    Column(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth()

    ) {
        Text(
            modifier = Modifier.align(Alignment.CenterHorizontally),
            text = material.title,
            style = MaterialTheme.typography.headlineLarge,
            color = MaterialTheme.colorScheme.primary
        )
    }

}

@Composable
fun profileContent(material: Material) {
    Column(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth()

    ) {
        Text(
            modifier = Modifier.align(Alignment.CenterHorizontally),
            text = "C",
            style = MaterialTheme.typography.headlineMedium,
            color = MaterialTheme.colorScheme.primary
        )
        SixBySixTable(material.elasticModulus)
    }
    Column(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth()

    ) {
        Text(
            modifier = Modifier.align(Alignment.CenterHorizontally),
            text = "e",
            style = MaterialTheme.typography.headlineMedium,
            color = MaterialTheme.colorScheme.primary
        )
        //ThreeByThreeTable(material.dielectricConstant)
    }


    //ThreeBySixTable(material.piezoelectricModulus)


}


@Composable
fun SixBySixTable(item: MutableList<Double>) {

    val data = (0..35).toList()
    LazyVerticalGrid(
        columns = GridCells.Fixed(6),
        contentPadding = PaddingValues(all = 16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {

        items(data) { index ->
            val rowIndex = index / 6
            val colIndex = index % 6
            Text(
                text = "C"
            )
            Box(
                modifier = Modifier.padding(12.dp)
            ) {
                Text(
                    text = "${rowIndex + 1}${colIndex + 1}",
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.primary
                )
            }
        }

    }
}

@Composable
internal fun SampleContent(
    param: String,
    row: Int,
    col: Int,
    widt: Int,
    item: MutableList<Double>
) {
    repeat(row * col) {
        if (item.size > it) {
            Box(
                modifier = Modifier
                    .size(widt.dp)
                    .border(width = 1.dp, color = DarkGray),
                contentAlignment = Alignment.Center,
            ) {
                Text(
                    text = item[it].toString()
                )

            }
        } else {
            Box(
                modifier = Modifier
                    .size(widt.dp)
                    .border(width = 1.dp, color = DarkGray),
                contentAlignment = Alignment.Center,
            ) {
                val rowIndex = it / row
                val colIndex = it % col
                //Text(it.toString())
                Text(
                    text = param
                )

                Text(
                    text = "${rowIndex + 1}${colIndex + 1}",
                    style = MaterialTheme.typography.bodySmall,
                    //color = MaterialTheme.colorScheme.primary
                    modifier = Modifier
                        .align(Alignment.BottomEnd)
                        .padding(1.dp)
                )

            }
        }

    }
}
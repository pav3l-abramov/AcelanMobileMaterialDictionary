package com.example.acelanmobilematerials.Screens

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.unit.dp
import com.example.acelanmobilematerials.data.DataProvider

@Composable
fun LazyListMaterials() {
    val materials = remember {DataProvider.materialList}
    LazyColumn(contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp)){
items(
    items=materials,
    itemContent = {MaterialListItem(material=it)}

)

    }

}

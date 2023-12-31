package com.example.acelanmobilematerials.Screens

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color.Companion.DarkGray
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.dp
import com.example.acelanmobilematerials.autorization.data.ApiInterface
//import com.example.acelanmobilematerials.autorization.model.LoginRequest
//import com.example.acelanmobilematerials.autorization.model.LoginResponse
import com.example.acelanmobilematerials.data.Material
import com.google.gson.GsonBuilder
import com.google.gson.JsonParser
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.json.JSONObject
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.RequestBody.Companion.toRequestBody


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
                .background(MaterialTheme.colorScheme.background)

        ) {
            item {
                ProfileHeader(material = material)
            }
            item {
                Column(
                    modifier = Modifier
                        .padding(16.dp)
                        .fillMaxWidth()

                ) {
                    Text(
                        modifier = Modifier.align(Alignment.Start),
                        text = "density=" + material.densit,
                        style = MaterialTheme.typography.headlineLarge,
                        color = MaterialTheme.colorScheme.primary
                    )
                }
            }
            item {
                LineString("C")
                FlowRow(maxItemsInEachRow = 6, modifier = Modifier.padding(horizontal = pad.dp)) {
                    SampleContent("C", 6, 6, width, material.elasticModulus)
                }
            }
            item {
                LineString("e")
                FlowRow(
                    maxItemsInEachRow = 3,
                    modifier = Modifier.padding(horizontal = (1.5 * width + pad).dp)
                ) {
                    SampleContent("e", 3, 3, width, material.dielectricConstant)
                }
            }
            item {
                LineString("d")
                FlowRow(maxItemsInEachRow = 6, modifier = Modifier.padding(horizontal = pad.dp)) {
                    SampleContent("d", 3, 6, width, material.piezoelectricModulus)
                }
            }
            item {
                Column(
                    modifier = Modifier
                        .padding(16.dp)
                        .fillMaxWidth()

                ) {
                    Text(
                        modifier = Modifier.align(Alignment.Start),
                        text = "source: " + material.sourceConst,
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.primary
                    )
                }
            }
        }
    } else {
        //для изотропных материалов
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background)
        ) {
            item {
                ProfileHeader(material = material)
            }
            item {
                Column(
                    modifier = Modifier
                        .padding(16.dp)
                        .fillMaxWidth()

                ) {
                    Text(
                        modifier = Modifier.align(Alignment.Start),
                        text = "density=" + material.densit,
                        style = MaterialTheme.typography.headlineLarge,
                        color = MaterialTheme.colorScheme.primary
                    )
                }
            }
            item {
                LineString("C")
                FlowRow(
                    maxItemsInEachRow = 2,
                    modifier = Modifier.padding(horizontal = (width * 2 + pad).dp)
                ) {
                    SampleContent("C", 1, 2, width, material.elasticModulus)
                }
            }
            item {
                LineString("e")
                FlowRow(
                    maxItemsInEachRow = 1,
                    modifier = Modifier.padding(horizontal = (2.5 * width + pad).dp)
                ) {
                    SampleContent("e", 1, 1, width, material.dielectricConstant)
                }
            }
            item {
                Column(
                    modifier = Modifier
                        .padding(16.dp)
                        .fillMaxWidth()

                ) {
                    Text(
                        modifier = Modifier.align(Alignment.CenterHorizontally),
                        text = "d-not exist",
                        style = MaterialTheme.typography.headlineMedium,
                        color = MaterialTheme.colorScheme.primary
                    )
                }
            }
            item {
                Column(
                    modifier = Modifier
                        .padding(16.dp)
                        .fillMaxWidth()

                ) {
                    Text(
                        modifier = Modifier.align(Alignment.Start),
                        text = "source: " + material.sourceConst,
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.primary
                    )
                }
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
            style = MaterialTheme.typography.displayMedium,
            color = MaterialTheme.colorScheme.primary
        )
    }

}

@Composable
fun LineString(param: String) {
    Column(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth()

    ) {
        Text(
            modifier = Modifier.align(Alignment.CenterHorizontally),
            text = param + "ᵢⱼ",
            style = MaterialTheme.typography.headlineMedium,
            color = MaterialTheme.colorScheme.primary
        )
    }

}
@Composable
internal fun SampleContent(
    param: String, row: Int, col: Int, widt: Int, item: MutableList<Double>
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
                    text = item[it].toString(), color = MaterialTheme.colorScheme.primary
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
                    text = param, color = MaterialTheme.colorScheme.primary,
                )

                Text(
                    text = "${rowIndex + 1}${colIndex + 1}",
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.primary,
                    modifier = Modifier
                        .align(Alignment.BottomEnd)
                        .padding(1.dp)
                )

            }
        }

    }
}
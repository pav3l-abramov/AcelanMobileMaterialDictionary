package com.example.acelanmobilematerials

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

import com.example.acelanmobilematerials.Screens.LazyListMaterials
import com.example.acelanmobilematerials.data.Material
import com.example.acelanmobilematerials.ui.theme.AcelanMobileMaterialsTheme
import com.google.accompanist.systemuicontroller.rememberSystemUiController

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AcelanMobileMaterialsTheme {
                val systemUiController = rememberSystemUiController()

                MyApp(){
                    startActivity(ProfileActivity.newIntent(this,it))

                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyApp(navigateToProfile:(Material)->Unit){
    Scaffold(
        content = { padding ->
            Column(modifier = Modifier.padding(padding)){ LazyListMaterials(navigateToProfile=navigateToProfile)}
        })
}

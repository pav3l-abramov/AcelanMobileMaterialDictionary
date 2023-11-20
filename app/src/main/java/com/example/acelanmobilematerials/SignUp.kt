package com.example.acelanmobilematerials

import android.content.Context
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
import com.example.acelanmobilematerials.Screens.ProfileScreen
import com.example.acelanmobilematerials.Screens.RecyclerScreen.LazyListMaterials
import com.example.acelanmobilematerials.Screens.SignUpScreen
import com.example.acelanmobilematerials.data.Material
import com.example.acelanmobilematerials.data.Users
import com.example.acelanmobilematerials.ui.theme.AcelanMobileMaterialsTheme
import com.example.makeitso.screens.login.LoginScreen

class SignUp : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AcelanMobileMaterialsTheme {
                // A surface container using the 'background' color from the theme

                SignApp(){startActivity(RecyclerView.newIntent(this,it))}
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SignApp(navigateToRecycler:(Users)->Unit){
    Scaffold(
        content = { padding ->
            Column(modifier = Modifier.padding(padding)){ SignUpScreen(navigateToRecycler =navigateToRecycler) }
        })
}



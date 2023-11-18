package com.example.acelanmobilematerials

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.Text
import com.example.acelanmobilematerials.Screens.ProfileScreen
import com.example.acelanmobilematerials.data.Material
import com.example.acelanmobilematerials.ui.theme.AcelanMobileMaterialsTheme

class ProfileActivity : ComponentActivity() {
    private val material: Material by lazy { intent?.getSerializableExtra(MATERIAL_ID) as Material }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AcelanMobileMaterialsTheme {
                // A surface container using the 'background' color from the theme
                ProfileScreen(material = material)
            }
        }
    }

    companion object {
        private const val MATERIAL_ID = "material_id"
        fun newIntent(context: Context, material: Material) =
            Intent(context, ProfileActivity::class.java).apply {
                putExtra(MATERIAL_ID, material)

            }

    }
}

package com.example.acelanmobilematerials.data

import androidx.compose.ui.text.input.TextFieldValue
import java.io.Serializable

data class Material(
    val id: Int,
    val typeMat: Int,
    val title: String,
    val elasticModulus: MutableList<Double>,
    val densit: Double,
    val dielectricConstant: MutableList<Double>,
    val piezoelectricModulus: MutableList<Double>,
    val sourceConst: String

):Serializable

data class Users(
    val email: String,
    val password: String,
    val token:String

):Serializable
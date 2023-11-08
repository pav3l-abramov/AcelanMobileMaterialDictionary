package com.example.acelanmobilematerials.data

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

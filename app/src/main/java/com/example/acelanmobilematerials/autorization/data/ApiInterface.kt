package com.example.acelanmobilematerials.autorization.data


import com.example.acelanmobilematerials.autorization.model.LoginRequest
import com.example.acelanmobilematerials.autorization.model.LoginResponse
import com.example.acelanmobilematerials.autorization.utils.Util
import retrofit2.Call
import retrofit2.Response
import retrofit2.Retrofit

import retrofit2.http.Body
import retrofit2.http.POST

interface ApiInterface {

   @POST("/api/token")
   fun login(@Body loginRequest: LoginRequest): Call<LoginResponse>
}
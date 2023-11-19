package com.example.acelanmobilematerials.autorization.data


//import com.example.acelanmobilematerials.autorization.model.LoginRequest
//import com.example.acelanmobilematerials.autorization.model.LoginResponse
import okhttp3.RequestBody
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Response
import retrofit2.Retrofit

import retrofit2.http.Body
import retrofit2.http.POST

interface ApiInterface {

   @POST("/api/token")
   suspend fun login (@Body requestBody: RequestBody): Response<ResponseBody>
   //fun login(@Body loginRequest: LoginRequest): Call<LoginResponse>
}
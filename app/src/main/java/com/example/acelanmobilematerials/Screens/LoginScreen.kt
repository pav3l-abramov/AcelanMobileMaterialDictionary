/*
Copyright 2022 Google LLC

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    https://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
 */

package com.example.makeitso.screens.login

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.R
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.acelanmobilematerials.autorization.data.ApiInterface
import com.example.acelanmobilematerials.data.Users
import com.google.gson.GsonBuilder
import com.google.gson.JsonParser
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.RequestBody.Companion.toRequestBody
import org.json.JSONObject
import org.json.JSONTokener
import retrofit2.Retrofit




@Composable
fun LoginScreen(  navigateToRecycler: (Users)->Unit

) {

    val context = LocalContext.current
    val email = remember { mutableStateOf(TextFieldValue()) }
    val emailErrorState = remember { mutableStateOf(false) }
    val passwordErrorState = remember { mutableStateOf(false) }
    val password = remember { mutableStateOf(TextFieldValue()) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
    ) {

      Image(
            painter = painterResource(id =if (isSystemInDarkTheme()) com.example.acelanmobilematerials.R.drawable.logo_dark_theme else com.example.acelanmobilematerials.R.drawable.logo_light_theme ),
            contentDescription = "",
            modifier = Modifier
                .size(200.dp)
                .align(Alignment.CenterHorizontally)
        )

        Text(text = buildAnnotatedString {
            withStyle(style = SpanStyle(color = MaterialTheme.colorScheme.onSurfaceVariant)) {
                append("S")
            }
            withStyle(style = SpanStyle(color = MaterialTheme.colorScheme.primary)) {
                append("ign")
            }

            withStyle(style = SpanStyle(color = MaterialTheme.colorScheme.onSurfaceVariant)) {
                append(" I")
            }
            withStyle(style = SpanStyle(color = MaterialTheme.colorScheme.primary)) {
                append("n")
            }
        }, fontSize = 30.sp)
        Spacer(Modifier.size(16.dp))
        OutlinedTextField(
            value = email.value,
            onValueChange = {
                if (emailErrorState.value) {
                    emailErrorState.value = false
                }
                email.value = it
            },
            isError = emailErrorState.value,
            modifier = Modifier.fillMaxWidth(),
            label = {
                Text(text = "Enter Email")
            },
        )
        if (emailErrorState.value) {
            Text(text = "Required", color = Color.Red)
        }
        Spacer(Modifier.size(16.dp))
        val passwordVisibility = remember { mutableStateOf(true) }
        OutlinedTextField(
            value = password.value,
            onValueChange = {
                if (passwordErrorState.value) {
                    passwordErrorState.value = false
                }
                password.value = it
            },
            isError = passwordErrorState.value,
            modifier = Modifier.fillMaxWidth(),

            trailingIcon = {
                IconButton(onClick = {
                    passwordVisibility.value = !passwordVisibility.value
                }) {

                }
            },
            //visualTransformation = if (passwordVisibility.value) PasswordVisualTransformation() else VisualTransformation.None
            label = {
                Text(text = "Enter Password")
            },
        )
        if (passwordErrorState.value) {
            Text(text = "Required", color = Color.Red)
        }
        Spacer(Modifier.size(16.dp))
        Button(
            onClick = {
                when {
                    email.value.text.isEmpty() -> {
                        emailErrorState.value = true
                    }
                    password.value.text.isEmpty() -> {
                        passwordErrorState.value = true
                    }

                    else -> {
                        val retrofit = Retrofit.Builder()
                            .baseUrl("https://acelan.ru")
                            .build()

                        // Create Service
                        val service = retrofit.create(ApiInterface::class.java)

                        // Create JSON using JSONObject
                        val jsonObject = JSONObject()
                        jsonObject.put("email", email.value.text)
                        jsonObject.put("password", password.value.text)

                        // Convert JSONObject to String
                        val jsonObjectString = jsonObject.toString()

                        // Create RequestBody ( We're not using any converter, like GsonConverter, MoshiConverter e.t.c, that's why we use RequestBody )
                        val requestBody = jsonObjectString.toRequestBody("application/json".toMediaTypeOrNull())

                        CoroutineScope(Dispatchers.IO).launch {

                            // Do the POST request and get response
                            val response = service.login(requestBody)

                            withContext(Dispatchers.Main) {
                                if (response.isSuccessful) {

                                    // Convert raw JSON to pretty JSON using GSON library
                                    val gson = GsonBuilder().setPrettyPrinting().create()
                                    val prettyJson = gson.toJson(
                                        JsonParser.parseString(
                                            response.body()
                                                ?.string() // About this thread blocking annotation : https://github.com/square/retrofit/issues/3255
                                        )
                                    )
                                    passwordErrorState.value = false
                                    emailErrorState.value = false
                                    val jsonObject = JSONTokener(prettyJson).nextValue() as JSONObject
                                    val token = jsonObject.getString("token")
                                    Log.d("Pretty Printed JSON :", token)
                                    val users=Users(email.value.text,password.value.text,token)
                                    navigateToRecycler(users)
                                    Toast.makeText(
                                        context,
                                        "Login successfully",
                                        Toast.LENGTH_SHORT
                                    ).show()


                                } else {

                                    Log.e("RETROFIT_ERROR", response.code().toString())
                                    Toast.makeText(
                                        context,
                                        "This account does not exist or check your email or password",
                                        Toast.LENGTH_SHORT
                                    ).show()

                                }

                            }

                        }


                    }
                }

            },
            content = {
                Text(text = "Login")
            },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(Modifier.size(16.dp))
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center) {
            TextButton(onClick = {

            }) {
                Text(text = "Register ?", color = Color.Red)
            }
        }
    }
}


/*
fun rawJSON(email:String,password:String): Boolean {

    // Create Retrofit
    val retrofit = Retrofit.Builder()
        .baseUrl("https://acelan.ru")
        .build()

    // Create Service
    val service = retrofit.create(ApiInterface::class.java)

    // Create JSON using JSONObject
    val jsonObject = JSONObject()
    jsonObject.put("email", email)
    jsonObject.put("password", password)

    // Convert JSONObject to String
    val jsonObjectString = jsonObject.toString()

    // Create RequestBody ( We're not using any converter, like GsonConverter, MoshiConverter e.t.c, that's why we use RequestBody )
    val requestBody = jsonObjectString.toRequestBody("application/json".toMediaTypeOrNull())

    CoroutineScope(Dispatchers.IO).launch {

        // Do the POST request and get response
        val response = service.login(requestBody)

        withContext(Dispatchers.Main) {
            if (response.isSuccessful) {

                // Convert raw JSON to pretty JSON using GSON library
                val gson = GsonBuilder().setPrettyPrinting().create()
                val prettyJson = gson.toJson(
                    JsonParser.parseString(
                        response.body()
                            ?.string() // About this thread blocking annotation : https://github.com/square/retrofit/issues/3255
                    )
                )

                Log.d("Pretty Printed JSON :", prettyJson)
                check=true

            } else {

                Log.e("RETROFIT_ERROR", response.code().toString())
                check=false

            }

        }

    }
    return(check)
}


 */
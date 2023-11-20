package com.example.acelanmobilematerials.Screens

import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.net.Uri
import android.text.TextUtils
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
import androidx.core.content.ContextCompat.startActivity
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.example.acelanmobilematerials.MainActivity
import com.example.acelanmobilematerials.Screens.SignUpScreen
import com.example.acelanmobilematerials.SignUp
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
fun SignUpScreen(
    navigateToRecycler: (Users) -> Unit

) {


    val context = LocalContext.current
    val email = remember { mutableStateOf(TextFieldValue()) }
    val emailErrorStateVal = remember { mutableStateOf(false) }
    val checkOnline = remember { mutableStateOf(false) }
    val emailErrorStateIn = remember { mutableStateOf(false) }
    val passwordErrorStateVal = remember { mutableStateOf(false) }
    val passwordErrorStateRep = remember { mutableStateOf(false) }
    val password1 = remember { mutableStateOf(TextFieldValue()) }
    val password2 = remember { mutableStateOf(TextFieldValue()) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
    ) {

        Image(
            painter = painterResource(id = if (isSystemInDarkTheme()) com.example.acelanmobilematerials.R.drawable.logo_dark_theme else com.example.acelanmobilematerials.R.drawable.logo_light_theme),
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
                append(" U")
            }
            withStyle(style = SpanStyle(color = MaterialTheme.colorScheme.primary)) {
                append("p")
            }
        }, fontSize = 30.sp)
        Spacer(Modifier.size(16.dp))
        OutlinedTextField(
            singleLine= true,
            value = email.value,
            onValueChange = {
                if (emailErrorStateVal.value) {
                    emailErrorStateVal.value = false
                }
                if (!email.value.text.isEmailValid()) {
                    emailErrorStateIn.value = false
                }
                email.value = it
            },
            isError = emailErrorStateVal.value||emailErrorStateIn.value,
            modifier = Modifier.fillMaxWidth(),
            label = {
                Text(text = "Enter Email")
            },
        )
        if (emailErrorStateVal.value) {
            Text(text = "Required", color = Color.Red)
        }
        if (emailErrorStateIn.value) {
            Text(text = "This is an incorrect email", color = Color.Red)
        }
        Spacer(Modifier.size(16.dp))
        val passwordVisibility = remember { mutableStateOf(true) }
        OutlinedTextField(
            singleLine= true,
            value = password1.value,
            onValueChange = {
                if (passwordErrorStateVal.value) {
                    passwordErrorStateVal.value = false
                }
                password1.value = it
            },
            isError = passwordErrorStateVal.value,
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
        if (passwordErrorStateVal.value) {
            Text(text = "Required", color = Color.Red)
        }
        Spacer(Modifier.size(16.dp))
        OutlinedTextField(
            singleLine= true,
            value = password2.value,
            onValueChange = {
                if (passwordErrorStateVal.value) {
                    passwordErrorStateVal.value = false
                }
                if (password2.value.text ==password1.value.text) {
                    passwordErrorStateRep.value = false
                }
                password2.value = it
            },
            isError = passwordErrorStateVal.value||passwordErrorStateRep.value,
            modifier = Modifier.fillMaxWidth(),

            trailingIcon = {
                IconButton(onClick = {
                    passwordVisibility.value = !passwordVisibility.value
                }) {

                }
            },
            //visualTransformation = if (passwordVisibility.value) PasswordVisualTransformation() else VisualTransformation.None
            label = {
                Text(text = "Repeat Password")
            },
        )
        if (passwordErrorStateVal.value) {
            Text(text = "Required", color = Color.Red)
        }
        if (passwordErrorStateRep.value) {
            Text(text = "your passwords don't match", color = Color.Red)
        }
        Spacer(Modifier.size(16.dp))
        Button(
            onClick = {
                when {
                    !isOnline(context)-> {
                        checkOnline.value = true
                        Toast.makeText(
                            context,
                            "You have a problem connecting to the Internet",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                    email.value.text.isEmpty() -> {
                        emailErrorStateVal.value = true
                    }

                    password1.value.text.isEmpty() -> {
                        passwordErrorStateVal.value = true
                    }
                    password2.value.text.isEmpty() -> {
                        passwordErrorStateVal.value = true
                    }
                    !email.value.text.isEmailValid()-> {
                        emailErrorStateIn.value = true
                    }
                    password1.value.text !=password2.value.text -> {
                        passwordErrorStateRep.value = true
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
                        jsonObject.put("password", password2.value.text)

                        // Convert JSONObject to String
                        val jsonObjectString = jsonObject.toString()

                        // Create RequestBody ( We're not using any converter, like GsonConverter, MoshiConverter e.t.c, that's why we use RequestBody )
                        val requestBody =
                            jsonObjectString.toRequestBody("application/json".toMediaTypeOrNull())

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
                                    passwordErrorStateVal.value = false
                                    passwordErrorStateRep.value = false
                                    emailErrorStateVal.value = false
                                    emailErrorStateIn.value = false
                                    checkOnline.value= false
                                    val jsonObject =
                                        JSONTokener(prettyJson).nextValue() as JSONObject
                                    val token = jsonObject.getString("token")
                                    Log.d("Pretty Printed JSON :", token)
                                    val users = Users(email.value.text, password2.value.text, token)
                                    navigateToRecycler(users)
                                    Toast.makeText(
                                        context,
                                        "Sign Up successfully",
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
                Text(text = "Create Account")
            },
            modifier = Modifier.fillMaxWidth()
        )

    }
}

fun String.isEmailValid(): Boolean {
    return !TextUtils.isEmpty(this) && android.util.Patterns.EMAIL_ADDRESS.matcher(this).matches()
}
fun isOnline(context: Context): Boolean {
    val connectivityManager =
        context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    if (connectivityManager != null) {
        val capabilities =
            connectivityManager.getNetworkCapabilities(connectivityManager.activeNetwork)
        if (capabilities != null) {
            if (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR)) {
                Log.i("Internet", "NetworkCapabilities.TRANSPORT_CELLULAR")
                return true
            } else if (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI)) {
                Log.i("Internet", "NetworkCapabilities.TRANSPORT_WIFI")
                return true
            } else if (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET)) {
                Log.i("Internet", "NetworkCapabilities.TRANSPORT_ETHERNET")
                return true
            }
        }
    }
    return false
}

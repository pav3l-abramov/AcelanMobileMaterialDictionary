plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
}

android {
    namespace = "com.example.acelanmobilematerials"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.acelanmobilematerials"
        minSdk = 24
        targetSdk = 33
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.4.3"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {
    implementation("com.google.accompanist:accompanist-systemuicontroller:0.30.1")
    implementation("androidx.navigation:navigation-compose:2.7.5")
    implementation("androidx.core:core-ktx:1.12.0")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.6.2")
    implementation("androidx.activity:activity-compose:1.8.1")
    implementation(platform("androidx.compose:compose-bom:2023.03.00"))
    implementation("androidx.compose.ui:ui")
    implementation("androidx.compose.ui:ui-graphics")
    implementation("androidx.compose.ui:ui-tooling-preview")
    implementation("androidx.compose.material3:material3:1.1.2")
    implementation(platform("androidx.compose:compose-bom:2023.03.00"))
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
    androidTestImplementation(platform("androidx.compose:compose-bom:2023.03.00"))
    androidTestImplementation("androidx.compose.ui:ui-test-junit4")
    androidTestImplementation(platform("androidx.compose:compose-bom:2023.03.00"))
    debugImplementation("androidx.compose.ui:ui-tooling")
    debugImplementation("androidx.compose.ui:ui-test-manifest")


    implementation("androidx.compose.runtime:runtime-livedata:1.5.4")
    // retrofit2
    implementation("com.squareup.retrofit2:retrofit:2.9.0")
    implementation("com.squareup.retrofit2:converter-gson:2.9.0")
    //implementation("com.squareup.retrofit2:converter-gson:2.9.0")
    //moshi
    //implementation("com.squareup.moshi:moshi-kotlin:1.14.0")
    //implementation("com.squareup.moshi:moshi-adapters:1.15.0")
    //implementation("com.squareup.okhttp3:okhttp:4.11.0")
    //implementation("io.kotlintest:kotlintest:2.0.7")

    //implementation("io.springfox:springfox-boot-starter:3.0.0")

    // Okhttp3 for the POST requests
    implementation("com.squareup.okhttp3:okhttp:4.11.0")

    // Gson to convert raw JSON to pretty JSON

    implementation("androidx.lifecycle:lifecycle-viewmodel-compose:2.6.2")
    implementation("androidx.core:core-ktx:1.12.0")

    implementation("org.json:json:20220320")
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.4.1")
    implementation ("com.google.code.gson:gson:2.9.0")

    testImplementation(kotlin("test"))

}
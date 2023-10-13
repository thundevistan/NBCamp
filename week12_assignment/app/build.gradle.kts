import com.android.build.gradle.internal.cxx.configure.gradleLocalProperties

plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    kotlin("kapt")
    id("kotlin-parcelize")
    id("com.google.dagger.hilt.android")
}

fun getApiKey(propertyKey: String): String {
    return gradleLocalProperties(rootDir).getProperty(propertyKey)
}

android {
    namespace = "bootcamp.sparta.disneym"
    compileSdk = 33

    defaultConfig {
        applicationId = "bootcamp.sparta.disneym"
        minSdk = 24
        targetSdk = 33
        versionCode = 1
        versionName = "1.0"

        buildConfigField("String", "YOUTUBE_API_KEY", getApiKey("youtube_api_key"))

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
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

    viewBinding {
        enable = true
    }
    buildFeatures {
        buildConfig = true
    }

}

dependencies {
    // Retrofit
    implementation ("com.squareup.retrofit2:retrofit:2.9.0")
    // Converter ( JSON 타입 결과를 객체로 매핑 )
    implementation ("com.squareup.retrofit2:converter-gson:2.9.0")
    // okhttp3
    implementation ("com.squareup.okhttp3:logging-interceptor:4.11.0")

    // Coroutines
    implementation ("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.7.2")
    implementation ("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.7.2")

    // ViewModel
    implementation ("androidx.lifecycle:lifecycle-viewmodel-ktx:2.6.2")
    implementation("androidx.activity:activity-ktx:1.7.2")
    implementation("androidx.fragment:fragment-ktx:1.6.1")

    // Glide
    implementation ("com.github.bumptech.glide:glide:4.16.0")
    implementation("androidx.fragment:fragment:1.6.1")
    annotationProcessor ("com.github.bumptech.glide:compiler:4.12.0")

    //Hilt
    implementation("com.google.dagger:hilt-android:2.44")
    kapt("com.google.dagger:hilt-android-compiler:2.44")


    implementation("androidx.core:core-ktx:1.9.0")
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.9.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")


    // Player
    implementation ("com.pierfrancescosoffritti.androidyoutubeplayer:core:11.0.1")
}
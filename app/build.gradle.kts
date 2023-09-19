plugins {
	id("com.android.application")
	id("org.jetbrains.kotlin.android")
}

android {
	namespace = "com.kotdev99.android.searchimages"
	compileSdk = 33

	defaultConfig {
		applicationId = "com.kotdev99.android.searchimages"
		minSdk = 24
		targetSdk = 33
		versionCode = 1
		versionName = "1.0"

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

	buildFeatures {
		viewBinding = true
	}
}

dependencies {

	implementation("androidx.core:core-ktx:1.9.0")
	implementation("androidx.appcompat:appcompat:1.6.1")
	implementation("com.google.android.material:material:1.9.0")
	implementation("androidx.constraintlayout:constraintlayout:2.1.4")
	testImplementation("junit:junit:4.13.2")
	androidTestImplementation("androidx.test.ext:junit:1.1.5")
	androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")

	// viewpager
	implementation("androidx.viewpager2:viewpager2:1.0.0")

	// fragment
	implementation("androidx.fragment:fragment-ktx:1.5.5")

	// retrofit
	implementation("com.squareup.retrofit2:retrofit:2.9.0")
	implementation("com.google.code.gson:gson:2.10.1")
	implementation("com.squareup.retrofit2:converter-gson:2.9.0")
}
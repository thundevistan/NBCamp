package com.example.android_supporters_sns_project

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity

class SplashActivity : AppCompatActivity() {
	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView(R.layout.activity_splash)

		splashScreen()
	}

	private fun splashScreen() {
		Handler(Looper.getMainLooper()).postDelayed({
			val intent = Intent(this, LoginActivity::class.java)
			startActivity(intent)
			finish()
		}, 500)
	}
}
package com.example.android_supporters_sns_project

import android.os.Bundle
import android.view.LayoutInflater
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity

class MainPageActivity : AppCompatActivity() {
	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView(R.layout.activity_main_page)

		val container = findViewById<LinearLayout>(R.id.main_addView_container)
		val inflater = LayoutInflater.from(this@MainPageActivity)

		for (i in 0..4) {
			val mainPost = inflater.inflate(R.layout.activity_main_page_post, null)
			container.addView(mainPost)
		}
	}
}

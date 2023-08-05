package com.android.introduceapp

import android.media.Image
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import java.security.AccessController.getContext
import kotlin.random.Random


class HomeActivity : AppCompatActivity() {
	val img = arrayOf(      // drawable 리소스
		R.drawable.rtan,
		R.drawable.rtan2,
		R.drawable.rtan3,
		R.drawable.rtan4,
		R.drawable.rtan5
	)

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView(R.layout.activity_home)

		randomImages()

		val strData = intent.getStringExtra("ID")       // "ID"의 값 수신
		val textViewId = findViewById<TextView>(R.id.textViewId)        // 아이디
		textViewId.text = "아이디 : " + strData        // 아이디 : strData 출력

		val btn_close = findViewById<Button>(R.id.btn_close)        // 종료 버튼
		btn_close.setOnClickListener {      // 버튼 클릭
			finish()
		}
	}

	fun randomImages() {
		val random = Random
		val index = random.nextInt(img.size)        // 리소스 개수의 max

		val imgRandom = findViewById<ImageView>(R.id.img_random)
		imgRandom.setImageResource(img[index])
	}
}
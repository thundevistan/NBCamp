package com.android.introduceapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class HomeActivity : AppCompatActivity() {
	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView(R.layout.activity_home)

		val strData = intent.getStringExtra("ID")       // "ID"의 값 수신
		val textViewId = findViewById<TextView>(R.id.textViewId)        // 아이디
		textViewId.text = "아이디 : " + strData        // 아이디 : strData 출력

		val btn_close = findViewById<Button>(R.id.btn_close)        // 종료 버튼
		btn_close.setOnClickListener {      // 버튼 클릭
			finish()
		}
	}
}
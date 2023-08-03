package com.android.introduceapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class SingleActivity : AppCompatActivity() {
	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView(R.layout.activity_single)

		val btn_Home = findViewById<Button>(R.id.btn_Home)      // 로그인 버튼 객체 주입
		btn_Home.setOnClickListener {       // 로그인 버튼 클릭
			val edit_textId = findViewById<EditText>(R.id.editTextId)       // 아이디 EditText 객체 주입
			val strId = edit_textId.text.toString()    // 입력된 아이디를 대입

			val edit_textPassword =
				findViewById<EditText>(R.id.editTextPassword)       // 비밀번호 EditText 객체 주입
			val strPassword = edit_textPassword.text.toString()     // 입력된 비밀번호를 대입

			val intent = Intent(this, HomeActivity::class.java)     // 소개 페이지 Intent 객체 주입
			intent.putExtra("ID", strId)        // ID-strId 값 전달
			startActivity(intent)

			// 아이디, 비밀번호 !== null 일 경우 startActivity(intent) 실행
//			if (true) startActivity(intent)
		}
	}
	// 회원가입 버튼 클릭
	fun doOnbtn_SignUpClick() {
		val intent = Intent(this, SignUpActivity::class.java)
		startActivity(intent)
	}
}
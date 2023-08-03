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

		val btn_Home = findViewById<Button>(R.id.btn_Home)      // 로그인 버튼
		btn_Home.setOnClickListener {       // 로그인 버튼 클릭
			val edit_textId = findViewById<EditText>(R.id.editTextId)       // 아이디 입력 텍스트
			val strId = edit_textId.text.toString()    // 아이디

			val edit_textPassword =
				findViewById<EditText>(R.id.editTextPassword)       // 비밀번호 입력 텍스트
			val strPassword = edit_textPassword.text.toString()     // 비밀번호

			val intent = Intent(this, HomeActivity::class.java)     // HomeActivity Intent
			intent.putExtra("ID", strId)        // ID-strId 전달

			// 아이디 & 비밀번호 입력 시 로그인 버튼 활성
			if (strId.isEmpty() || strPassword.isEmpty()) {
				Toast.makeText(
					this,
					"아이디/비밀번호를 확인해주세요",
					Toast.LENGTH_SHORT
				).show()
			} else {
				Toast.makeText(this, "로그인 성공", Toast.LENGTH_SHORT).show()
				startActivity(intent)       // HomeActivity 이동
			}
		}

		val btn_SignUp = findViewById<Button>(R.id.btn_SignUp)      // 회원가입 버튼
		btn_SignUp.setOnClickListener {     // 회원가입 버튼 클릭
			val intent = Intent(this, SignUpActivity::class.java)       // SignUpActivity Intent
			startActivity(intent)       // SignUpActivity 이동
		}
	}

//	fun doOnClick() {
//		val intent = Intent(this, SignUpActivity::class.java)
//		startActivity(intent)
//	}
}


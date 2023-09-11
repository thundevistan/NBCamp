package com.android.introduceapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts

class SingleActivity : AppCompatActivity() {
	private lateinit var resultLauncher: ActivityResultLauncher<Intent>

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView(R.layout.activity_single)

		val edit_textId = findViewById<EditText>(R.id.editTextId)       // 아이디 입력 텍스트
		val strId = edit_textId.text.toString()    // 아이디

		val edit_textPassword =
			findViewById<EditText>(R.id.editTextPassword)       // 비밀번호 입력 텍스트
		val strPassword = edit_textPassword.text.toString()     // 비밀번호

		val btn_Home = findViewById<Button>(R.id.btn_Home)      // 로그인 버튼
		// 로그인 버튼 클릭
		btn_Home.setOnClickListener {
			val intent = Intent(this, HomeActivity::class.java)     // HomeActivity Intent
			intent.putExtra("ID", strId)        // ID-strId 전달

			val strId = edit_textId.text.toString()    // 아이디
			val strPassword = edit_textPassword.text.toString()     // 비밀번호

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
		// 회원가입 버튼 클릭
		btn_SignUp.setOnClickListener {
			val intent = Intent(this, SignUpActivity::class.java)       // SignUpActivity Intent
			resultLauncher.launch(intent)       // SignUpActivity 이동 및 ActivityResult 수신
		}

		resultLauncher =
			registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->     // it -> result
				if (result.resultCode == RESULT_OK) {
					val id = result.data?.getStringExtra("id") ?: ""        // 아이디 수신 혹은 ""
					val password = result.data?.getStringExtra("password") ?: ""  // 비밀번호 수신 혹은 ""

					edit_textId.setText(id)     // 아이디에 입력
					edit_textPassword.setText(password)     // 비밀번호에 입력
				}
			}

	}
}


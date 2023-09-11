package com.android.introduceapp

import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class SignUpActivity : AppCompatActivity() {
	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView(R.layout.activity_sign_up)

		val btn_Single = findViewById<Button>(R.id.btn_Single)      // 회원가입 버튼
		btn_Single.setOnClickListener {     // 회원가입 버튼 클릭
			val editText_name = findViewById<EditText>(R.id.editText_name)
			val strName = editText_name.text.toString()

			val editText_id = findViewById<EditText>(R.id.editText_id)
			val strId = editText_id.text.toString()

			val editText_paasword = findViewById<EditText>(R.id.editText_password)
			val strPassword = editText_paasword.text.toString()

			val isName = strName.isEmpty()          // 이름 텍스트가 비어있는지 확인, 초기값 : true
			val isId = strId.isEmpty()              // 아이디 텍스트가 비어있는지 확인, 초기값 : true
			val isPassword = strPassword.isEmpty()  // 비밀번호 텍스트가 비어있는지 확인, 초기값 : true

			// 이름, 이아디, 비밀번호 모두 입력 시 finish() 호출
			if (isName || isId || isPassword) {
				Toast.makeText(this, "입력되지 않은 정보가 있습니다.", Toast.LENGTH_SHORT).show()
			} else {
				intent.putExtra("id", strId)        // 아이디 전달
				intent.putExtra("password", strPassword)        // 비밀번호 전달
				setResult(RESULT_OK, intent)        // RESULT_OK 전달하여 ActivityResult 수신 구문 실행
				finish()
			}
		}
	}
}
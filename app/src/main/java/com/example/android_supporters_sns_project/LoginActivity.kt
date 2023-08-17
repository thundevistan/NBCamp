package com.example.android_supporters_sns_project

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class LoginActivity : AppCompatActivity() {

    private lateinit var loginButton: Button
    private lateinit var signupButton: Button
    private lateinit var emailEditText: EditText
    private lateinit var passwordEditText: EditText
    private lateinit var emailWarningMessage: TextView
    private lateinit var passwordWarningMessage: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        loginButton = findViewById(R.id.login_signin_button)
        signupButton = findViewById(R.id.login_signup_button)
        emailEditText = findViewById(R.id.login_id_edittext)
        passwordEditText = findViewById(R.id.login_password_edittext)
        emailWarningMessage = findViewById(R.id.login_id_message)
        passwordWarningMessage = findViewById(R.id.login_password_message)

        loginButton.setOnClickListener {
            login()
        }

        signupButton.setOnClickListener {
            intent = Intent(this, SignupActivity::class.java)
            startActivity(intent)
            overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left)
        }

    }

    private fun checkEmailEditText() {
        if (emailEditText.text.toString().trim().isEmpty()) {
            emailWarningMessage.text = "이메일을 입력해주세요."
            emailWarningMessage.visibility = TextView.VISIBLE
        } else {
            emailWarningMessage.visibility = TextView.INVISIBLE
        }
    }

    private fun checkPasswordEditText() {
        if (passwordEditText.text.toString().trim().isEmpty()) {
            passwordWarningMessage.text = "비밀번호를 입력해주세요."
            passwordWarningMessage.visibility = TextView.VISIBLE
        } else {
            passwordWarningMessage.visibility = TextView.INVISIBLE

        }
    }

    private fun login() {
        val email = emailEditText.text.toString().trim()
        Log.d("LoginActivity", email)
        val password = passwordEditText.text.toString().trim()
        Log.d("LoginActivity", password)
        if (email.isNotEmpty() && password.isNotEmpty()) {
            emailWarningMessage.visibility = TextView.INVISIBLE
            passwordWarningMessage.visibility = TextView.INVISIBLE
            val member = MemberManager.getMemberList().find { it.email == email }
            if (member != null) {
                if (member.password == password) {
                    // 로그인 성공
                    Toast.makeText(this, "로그인 성공!", Toast.LENGTH_SHORT).show()
                    intent = Intent(this, MainPageActivity::class.java)
                    startActivity(intent)
                } else {
                    // 비밀번호가 일치하지 않음
                    passwordWarningMessage.text = "비밀번호가 일치하지 않습니다."
                    passwordWarningMessage.visibility = TextView.VISIBLE
                    Log.d("LoginActivity", "비밀번호가 일치하지 않습니다.")
                }
            } else {
                // 이메일이 존재하지 않음
                emailWarningMessage.text = "존재하지 않는 아이디입니다."
                emailWarningMessage.visibility = TextView.VISIBLE
                Log.d("LoginActivity", "존재하지 않는 아이디입니다.")
            }
        } else {
            checkPasswordEditText()
            checkEmailEditText()
        }
    }
}


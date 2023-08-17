package com.example.android_supporters_sns_project

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.example.android_supporters_sns_project.dataclass.Member

class LoginActivity : AppCompatActivity() {

    private lateinit var loginButton: Button
    private lateinit var signupButton: Button
    private lateinit var emailEditText: EditText
    private lateinit var passwordEditText: EditText
    private lateinit var emailWarningMessage: TextView
    private lateinit var passwordWarningMessage: TextView
    private lateinit var activityResultLauncher: ActivityResultLauncher<Intent>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
8
        MemberManager.addMember(
            Member("c", "a", "이충환", "충환", null )
        )

        loginButton = findViewById(R.id.login_signin_button)
        signupButton = findViewById(R.id.login_signup_button)
        emailEditText = findViewById(R.id.login_id_edittext)
        passwordEditText = findViewById(R.id.login_password_edittext)
        emailWarningMessage = findViewById(R.id.login_id_message)
        passwordWarningMessage = findViewById(R.id.login_password_message)

        activityResultLauncher =
            registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
                if (it.resultCode == RESULT_OK) {
                    val userId = it.data?.getStringExtra("id") ?: ""
                    val userPw = it.data?.getStringExtra("pw") ?: ""
                    emailEditText.setText(userId)
                    passwordEditText.setText(userPw)
                }
            }

        loginButton.setOnClickListener {
            login()
        }

        signupButton.setOnClickListener {
            emailWarningMessage.visibility = TextView.INVISIBLE
            passwordWarningMessage.visibility = TextView.INVISIBLE
            val intent = Intent(this, SignupActivity::class.java)
            activityResultLauncher.launch(intent)
            overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left)
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
                checkLogin(member, password)
            } else {
                emailNotExists()
            }
        } else {
            notFilled()
        }
    }

    private fun checkLogin(
        member: Member, password: String
    ) {
        if (member.password == password) {
            // 로그인 성공
//            Toast.makeText(this, "로그인 성공!", Toast.LENGTH_SHORT).show()
            intent = Intent(this, MainPageActivity::class.java)
            intent.putExtra("email", member.email)
            startActivity(intent)
        } else {
            // 비밀번호가 일치하지 않음
            passwordWarningMessage.text = "비밀번호가 일치하지 않습니다."
            passwordWarningMessage.visibility = TextView.VISIBLE
            Log.d("LoginActivity", "비밀번호가 일치하지 않습니다.")
        }
    }

    private fun emailNotExists() {
        // 이메일이 존재하지 않음
        emailWarningMessage.text = "존재하지 않는 아이디입니다."
        emailWarningMessage.visibility = TextView.VISIBLE
        Log.d("LoginActivity", "존재하지 않는 아이디입니다.")
    }

    private fun notFilled() {
        if (emailEditText.text.toString().trim().isEmpty()) {
            emailWarningMessage.text = "이메일을 입력해주세요."
            emailWarningMessage.visibility = TextView.VISIBLE
        }
        if (passwordEditText.text.toString().trim().isEmpty()) {
            passwordWarningMessage.text = "비밀번호를 입력해주세요."
            passwordWarningMessage.visibility = TextView.VISIBLE
        }
    }

}


package com.example.android_supporters_sns_project

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.MotionEvent
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
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
        MemberManager.addMember(
            Member("c", "a", "이충환", "충환", null, "상태메세지")
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
        val password = passwordEditText.text.toString().trim()
        val member = MemberManager.getMemberList().find { it.email == email }
        emailWarningMessage.visibility = TextView.INVISIBLE
        passwordWarningMessage.visibility = TextView.INVISIBLE
        if (email.isNotEmpty() && password.isNotEmpty()) {
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
            intent = Intent(this, MainPageActivity::class.java)
            intent.putExtra("email", member.email)
            startActivity(intent)
            overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left)
        } else {
            // 비밀번호가 일치하지 않음
            passwordWarningMessage.text = getString(R.string.login_passwordWarningMessage)
            passwordWarningMessage.visibility = TextView.VISIBLE
            Log.d("LoginActivity", getString(R.string.login_passwordWarningMessage))
        }
    }

    private fun emailNotExists() {
        if (!emailEditText.text.contains("@")) {
            emailWarningMessage.text = getString(R.string.login_emailWarningMessage)
            emailWarningMessage.visibility = TextView.VISIBLE
            passwordWarningMessage.visibility = TextView.INVISIBLE
        }
    }

    private fun notFilled() {
        if (emailEditText.text.toString().trim().isEmpty()) {
            emailWarningMessage.text = getString(R.string.login_emailEnterMessage)
            emailWarningMessage.visibility = TextView.VISIBLE
        }
        if (passwordEditText.text.toString().trim().isEmpty()) {
            passwordWarningMessage.text = getString(R.string.login_passwordEnterMessage)
            passwordWarningMessage.visibility = TextView.VISIBLE
        }
    }

    override fun dispatchTouchEvent(ev: MotionEvent): Boolean {
        val imm: InputMethodManager =
            getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(currentFocus?.windowToken, 0)
        return super.dispatchTouchEvent(ev)
    }

}


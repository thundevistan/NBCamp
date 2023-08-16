package com.example.android_supporters_sns_project

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.Button
import android.widget.EditText
import android.view.View
import android.widget.TextView


class LoginActivity : AppCompatActivity() {

    private lateinit var loginButton : Button
    private lateinit var signupButton : Button
    private lateinit var emailEditText : EditText
    private lateinit var passwordEditText: EditText
    private lateinit var emailWarningMessage : TextView
    private lateinit var passwordWarningMessage : TextView

    var isEmail = false
    var isPassword = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        loginButton = findViewById(R.id.login_signin_button)
        signupButton = findViewById(R.id.login_signup_button)
        emailEditText = findViewById(R.id.login_id_edittext)
        passwordEditText = findViewById(R.id.login_password_edittext)

    }

    private val loginTextWatcher = object : TextWatcher {
        emailWarningMessage = findViewById(R.id.login_id_message)
        passwordWarningMessage = findViewById(R.id.login_password_message)
    }

    private val loginEmailTextWatcher = object : TextWatcher {

        override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

        }
        override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

        }
        override fun afterTextChanged(p0: Editable?) {


        }
    }
    private fun checkEmailEditText() {
        val emailText = emailEditText.toString().trim()
        if(emailText.isNotEmpty()) {

        }
        else {

        }
    }
    private fun checkPasswordEditText() {

    }

    private fun login() {

            checkEmailEditText()
        }
    }

    private val loginPasswordTextWatcher = object : TextWatcher {
        override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

        }

        override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

        }

        override fun afterTextChanged(p0: Editable?) {
            checkPasswordEditText()
        }

    }
    private fun checkEmailEditText() {
        val emailText = emailEditText.toString().trim()
        if (emailText.isNotEmpty()) {
            isEmail = true
        }
        else if (emailText.isEmpty()) {
            isEmail = false
        }
    }
    private fun checkPasswordEditText() {
        val passwordText = passwordEditText.toString().trim()

        if (passwordText.isNotEmpty()) {
            isPassword = true
        }
        else if (passwordText.isEmpty()) {
            isPassword = false
        }
    }

    private fun login() {
        loginButton.isEnabled = isEmail&&isPassword

    }
}
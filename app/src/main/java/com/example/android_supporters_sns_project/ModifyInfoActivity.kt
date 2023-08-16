package com.example.android_supporters_sns_project

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.TextView
import com.google.android.material.textfield.TextInputLayout

class ModifyInfoActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_modify_info)

        passwordTextWatcher()
    }

    private fun isPasswordValid(password: String): Boolean {
        return !password.contains(" ") && password.matches("^(?=.*[A-Za-z])(?=.*[0-9])[A-Za-z0-9]{8,16}\$".toRegex())
    }

    private fun passwordTextWatcher() {
        val passwordLayout = findViewById<TextInputLayout>(R.id.modify_info_password_textinputlayout)
        val errorMessageView = findViewById<TextView>(R.id.modify_info_password_error_message)

        passwordLayout.editText?.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }
            override fun onTextChanged(s: CharSequence?, p1: Int, p2: Int, p3: Int) {
                if ((s?.length ?: 0) in 1..7 || (s?.length ?: 0) > 16 || !isPasswordValid(s.toString())) {
                    passwordLayout.isErrorEnabled = true
                    errorMessageView.visibility = View.VISIBLE
                    errorMessageView.text = getString(R.string.modifyinfo_password_warning_text)
                } else {
                    passwordLayout.isErrorEnabled = false
                    errorMessageView.visibility = View.INVISIBLE
                }
            }
            override fun afterTextChanged(s: Editable?) {

                if ((s?.length ?: 0) in 1..7 || (s?.length ?: 0) > 16 || !isPasswordValid(s.toString()))  {
                    passwordLayout.isErrorEnabled = true
                    errorMessageView.visibility = View.VISIBLE
                    errorMessageView.text = getString(R.string.modifyinfo_password_warning_text)
                } else {
                    passwordLayout.isErrorEnabled = false
                    errorMessageView.visibility = View.INVISIBLE
                }
            }
        })
    }
}
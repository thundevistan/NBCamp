package com.example.android_supporters_sns_project

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class ModifyInfoActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_modify_info)
    }

    private fun isPasswordValid(password: String): Boolean {
        return !password.contains(" ") && password.matches("^(?=.*[A-Za-z])(?=.*[0-9])[A-Za-z0-9]{8,16}\$".toRegex())
    }
}
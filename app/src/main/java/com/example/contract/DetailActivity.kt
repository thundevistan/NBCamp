package com.example.contract

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView

class DetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        // 인텐트로부터 데이터 받기
        val profileImage = intent.getIntExtra("profileImage", R.drawable.img_profile)
        val listName = intent.getStringExtra("listName")
        val phoneNumber = intent.getStringExtra("phoneNumber")
        val email = intent.getStringExtra("email")
        val event = intent.getStringExtra("event")

        // 받은 데이터를 사용하여 UI 업데이트
        val profileImageView = findViewById<ImageView>(R.id.profileImageView)
        val listNameTextView = findViewById<TextView>(R.id.listNameTextView)
        val phoneNumberTextView = findViewById<TextView>(R.id.phoneNumberTextView)
        val emailTextView = findViewById<TextView>(R.id.emailTextView)
        val eventTextView = findViewById<TextView>(R.id.eventTextView)

        profileImageView.setImageResource(profileImage)
        listNameTextView.text = listName
        phoneNumberTextView.text = phoneNumber
        emailTextView.text = email
        eventTextView.text = event
    }
}

package com.example.contract

import androidx.appcompat.app.AppCompatActivity
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import com.example.contract.R

class DetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        // 좋아요 버튼에 대한 클릭 리스너 추가
        val likeButton = findViewById<ImageView>(R.id.ll_detail_like)
        var isLiked = false // 초기 상태는 좋아요가 되지 않은 상태

        likeButton.setOnClickListener {
            // 아이콘 토글
            if (isLiked) {
                likeButton.setImageResource(R.drawable.ic_love_empty)
            } else {
                likeButton.setImageResource(R.drawable.ic_love_filled)
            }

            isLiked = !isLiked // 상태를 토글
        }

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

        // 전화 걸기 버튼에 클릭 리스너 추가
        val callButton = findViewById<Button>(R.id.btn_call)
        callButton.setOnClickListener {
            // 전화 번호를 가져와서 전화 걸기 인텐트 생성
            val phone = phoneNumberTextView.text.toString()
            val intent = Intent(Intent.ACTION_CALL)
            intent.data = Uri.parse("tel:$phone")

            // 전화 걸기 권한이 있는지 확인
            if (checkSelfPermission(android.Manifest.permission.CALL_PHONE) == PackageManager.PERMISSION_GRANTED) {
                // 권한이 있으면 전화 걸기 인텐트 실행
                startActivity(intent)
            } else {
                // 권한이 없으면 권한 요청
                requestPermissions(arrayOf(android.Manifest.permission.CALL_PHONE), 1)
            }
        }

        val messageButton = findViewById<Button>(R.id.btn_message)
        messageButton.setOnClickListener {
            // 전화 번호를 가져와서 문자 메시지 보내기 인텐트 생성
            val phone = findViewById<TextView>(R.id.phoneNumberTextView).text.toString()
            val intent = Intent(Intent.ACTION_SENDTO)
            intent.data = Uri.parse("smsto:$phone")

            // 문자 메시지 앱을 실행
            startActivity(intent)
        }
    }
}

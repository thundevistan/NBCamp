package com.example.contract

import androidx.appcompat.app.AppCompatActivity
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.widget.Button
import com.example.contract.databinding.ActivityDetailBinding
import com.example.contract.sampledata.ContactManager

class DetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // 인텐트로부터 데이터 받기
        val position = intent.getIntExtra("position", 0)
        val profileImage = ContactManager.getContact()[position].profileImage
        val listName = intent.getStringExtra("listName")
        val phoneNumber = intent.getStringExtra("phoneNumber")
        val email = intent.getStringExtra("email")
        val event = intent.getStringExtra("event")
        val isfaVorite = intent.getBooleanExtra("isFavorite", false) // isFavorite 값을 받아옴
        var isLiked = isfaVorite

        // 받은 데이터를 사용하여 UI 업데이트
        val profileImageView = binding.profileImageView
        val listNameTextView = binding.listNameTextView
        val phoneNumberTextView = binding.phoneNumberTextView
        val emailTextView = binding.emailTextView
        val eventTextView = binding.eventTextView
        val like = binding.like

        like.setOnClickListener {
            // 아이콘 토글
            if (isLiked) {
                like.setImageResource(R.drawable.ic_love_filled)
            } else {
                like.setImageResource(R.drawable.ic_love_empty)
            }
            isLiked = !isLiked // 상태를 토글
        }

        profileImageView.setImageURI(profileImage)
        listNameTextView.text = listName
        phoneNumberTextView.text = phoneNumber
        emailTextView.text = email
        eventTextView.text = event

        // 좋아요 버튼 상태 업데이트
        like.setImageResource(if (isLiked) R.drawable.ic_love_filled else R.drawable.ic_love_empty)

        // 전화 걸기 버튼에 클릭 리스너 추가
        val callButton = binding.btnCall

        val phone = phoneNumberTextView.text.toString()

        callButton.setOnClickListener {
            // 전화 번호를 가져와서 전화 걸기 인텐트 생성
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
            val intent = Intent(Intent.ACTION_SENDTO)
            intent.data = Uri.parse("smsto:$phone")

            // 문자 메시지 앱을 실행
            startActivity(intent)
        }

        binding.backButton.setOnClickListener {
            finish()
        }
    }
}

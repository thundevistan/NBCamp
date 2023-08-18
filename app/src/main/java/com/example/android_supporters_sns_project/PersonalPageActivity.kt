package com.example.android_supporters_sns_project

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.Button
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity

class PersonalPageActivity : AppCompatActivity() {

    private lateinit var backButton: ImageButton
    private lateinit var moveToEditButton: ImageView
    private lateinit var logoutButton: Button

    private lateinit var btnDetailContent1: ImageButton
    private lateinit var btnDetailContent2: ImageButton
    private lateinit var btnDetailContent3: ImageButton

    private lateinit var profilePicture: ImageView
    private lateinit var profileName: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_personal_page)

        val emailData: String? = intent.getStringExtra("email")

        backButton = findViewById(R.id.personalPage_backSpace_imageButton)
        moveToEditButton = findViewById(R.id.personalPage_profileEdit_imageView)
        logoutButton = findViewById(R.id.personalPage_logout_button)

        btnDetailContent1 = findViewById(R.id.personalPage_examPic1_imageButton)
        btnDetailContent2 = findViewById(R.id.personalPage_examPic2_imageButton)
        btnDetailContent3 = findViewById(R.id.personalPage_examPic3_imageButton)

        profilePicture = findViewById(R.id.personalPage_profile_imageView)
        profileName = findViewById(R.id.personalPage_ID_textView)

        btnDetailContent1.setOnClickListener {
            val intent = Intent(this, DetailContentViewActivity::class.java)
            intent.putExtra("intDataFromTeamMateDetail", 1)
            startActivity(intent)
            overridePendingTransition(R.anim.fade_in, R.anim.fade_out)
        }
        btnDetailContent2.setOnClickListener {
            val intent = Intent(this, DetailContentViewActivity::class.java)
            intent.putExtra("intDataFromTeamMateDetail", 2)
            startActivity(intent)
            overridePendingTransition(R.anim.fade_in, R.anim.fade_out)
        }
        btnDetailContent3.setOnClickListener {
            val intent = Intent(this, DetailContentViewActivity::class.java)
            intent.putExtra("intDataFromTeamMateDetail", 3)
            startActivity(intent)
            overridePendingTransition(R.anim.fade_in, R.anim.fade_out)
        }

        moveToEditButton.setOnClickListener {
            val intent = Intent(this, ModifyInfoActivity::class.java)
            intent.putExtra("email", emailData)
            startActivity(intent)
            overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left)
        }

        backButton.setOnClickListener {
            finish()
            overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right)
        }


        profileName.text = emailData?.let { MemberManager.getMemberByEmail(it)?.name }

        logoutButton.setOnClickListener {
            //로그아웃 버튼을 누르면 로그아웃 여부를 묻는 AlertDialog가 뜸.
            AlertDialog.Builder(this).setTitle("로그아웃").setMessage("로그아웃 하시겠습니까?")
                .setPositiveButton("확인") { _, _ ->
                    // 프로그레스 바를 나타내는 AlertDialog 생성
                    val progressDialog = AlertDialog.Builder(this).apply {
                        setCancelable(false)
                        setView(R.layout.progress_dialog)
                        setTitle("로그아웃 중입니다.")
                    }.create()
                    progressDialog.show()

                    // 비동기 작업을 수행하는 동안 프로그레스바가 표시됩니다(2초 대기)
                    Handler(Looper.getMainLooper()).postDelayed({
                        progressDialog.dismiss()
                        //확인을 누르면 로그인 화면으로 이동
                        AlertDialog.Builder(this).setTitle("로그아웃").setMessage("로그아웃 되었습니다.")
                            .setPositiveButton("확인") { _, _ ->
                                val intent = Intent(this, LoginActivity::class.java)
                                startActivity(intent)
                                overridePendingTransition(
                                    R.anim.slide_in_left, R.anim.slide_out_right
                                )
                            }.show()
                    }, 1500)

                }.setNegativeButton("취소") { _, _ -> }.show()
        }
    }
}
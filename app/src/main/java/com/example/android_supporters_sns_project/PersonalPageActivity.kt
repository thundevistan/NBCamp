package com.example.android_supporters_sns_project

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import android.widget.ImageView
import com.example.android_supporters_sns_project.dataclass.Member

class PersonalPageActivity : AppCompatActivity() {

    private lateinit var backButton: ImageButton
    private lateinit var moveToEditButton : ImageView

    private lateinit var btnDetailContent1 : ImageButton
    private lateinit var btnDetailContent2 : ImageButton
    private lateinit var btnDetailContent3 : ImageButton

    private lateinit var profilePicture : ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_personal_page)

        val emailData : String? = intent.getStringExtra("email")

        backButton = findViewById(R.id.personalPage_backSpace_imageButton)
        moveToEditButton = findViewById(R.id.personalPage_profileEdit_imageView)

        btnDetailContent1 = findViewById(R.id.personalPage_examPic1_imageButton)
        btnDetailContent2 = findViewById(R.id.personalPage_examPic2_imageButton)
        btnDetailContent3 = findViewById(R.id.personalPage_examPic3_imageButton)

        profilePicture = findViewById(R.id.personalPage_profile_imageView)

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

    }
}
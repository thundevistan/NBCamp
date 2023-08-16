package com.example.android_supporters_sns_project

import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageButton
import android.widget.TextView

class TeamMateDetailPageActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_team_mate_detail_page)

        //각 변수와 이미지 버튼 연동 (변수명은 임의로 설정했는데 작성 룰이 있다면 변경 부탁드립니다)
        val btnDetailContent1 = findViewById<ImageButton>(R.id.teamMateDetail_examPic1_imageButton)
        val btnDetailContent2 = findViewById<ImageButton>(R.id.teamMateDetail_examPic2_imageButton)
        val btnDetailContent3 = findViewById<ImageButton>(R.id.teamMateDetail_examPic3_imageButton)

        //각 버튼 클릭 시 해당하는 intData 전달
        btnDetailContent1.setOnClickListener {
            val intent = Intent(this, DetailContentViewActivity::class.java)
            intent.putExtra("intDataFromTeamMateDetail",1)
            startActivity(intent)
        }
        btnDetailContent2.setOnClickListener {
            val intent = Intent(this, DetailContentViewActivity::class.java)
            intent.putExtra("intDataFromTeamMateDetail",2)
            startActivity(intent)
        }
        btnDetailContent3.setOnClickListener {
            val intent = Intent(this, DetailContentViewActivity::class.java)
            intent.putExtra("intDataFromTeamMateDetail",3)
            startActivity(intent)
        }
    }
}
package com.example.android_supporters_sns_project

import android.graphics.Bitmap
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView

class DetailContentViewActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_content_view)

        //받아온 intData 저장 (디폴트 0)
        val intData: Int = intent.getIntExtra("intDataFromTeamMateDetail", 0)

        //Data가 필요한 View 연동
        val imgProfile = findViewById<ImageView>(R.id.detailContentView_profile_imageView)
        val txtID = findViewById<TextView>(R.id.detailContentView_id_textview)
        val imgContent = findViewById<ImageView>(R.id.detailContentView_contentimage_imageview)
        val txtContent = findViewById<TextView>(R.id.detailContentView_content_textview)

        //intData에 따라 각 Data 설정
        when(intData) {
            1 -> {
                imgProfile.setImageResource(R.drawable.ic_teammatedetail_person)
                txtID.setText("아이디1")
                imgContent.setImageResource(R.drawable.exampic1_teammatedetail_imageview)
                txtContent.setText("게시물 텍스트1")
            }
            2 -> {
                imgProfile.setImageResource(R.drawable.ic_teammatedetail_person)
                txtID.setText("아이디2")
                imgContent.setImageResource(R.drawable.exampic2_teammatedetail_imageview)
                txtContent.setText("게시물 텍스트2")
            }
            3 -> {
                imgProfile.setImageResource(R.drawable.ic_teammatedetail_person)
                txtID.setText("아이디3")
                imgContent.setImageResource(R.drawable.exampic3_teammatedetail_imageview)
                txtContent.setText("게시물 텍스트3")
            }
        }
    }
}
package com.example.android_supporters_sns_project

import android.graphics.Bitmap
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageButton
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
        val backButton = findViewById<ImageButton>(R.id.detailContentView_backSpace_imageButton)
        val txtSeeMore = findViewById<TextView>(R.id.detailContentView_seeMore_textview)

        backButton.setOnClickListener {
            finish()
            overridePendingTransition(R.anim.fade_in, R.anim.fade_out)
        }

        //intData에 따라 각 Data 설정
        when(intData) {
            1 -> {
                imgProfile.setImageResource(R.drawable.img_profile1)
                txtID.setText("아이디1")
                imgContent.setImageResource(R.drawable.img_post1)
                txtContent.setText("더보기 전용 예시 텍스트입니다. 더보기 전용 예시 텍스트입니다. 더보기 전용 예시 텍스트입니다. 더보기 전용 예시 텍스트입니다. 더보기 전용 예시 텍스트입니다. 더보기 전용 예시 텍스트입니다. 더보기 전용 예시 텍스트입니다.")
            }
            2 -> {
                imgProfile.setImageResource(R.drawable.img_profile2)
                txtID.setText("아이디2")
                imgContent.setImageResource(R.drawable.img_post2)
                txtContent.setText("sad bmo😢")
            }
            3 -> {
                imgProfile.setImageResource(R.drawable.img_profile3)
                txtID.setText("아이디3")
                imgContent.setImageResource(R.drawable.img_post3)
                txtContent.setText("팝콘 냠냠🍿")
            }
        }
        seeMoreView(txtContent, txtSeeMore)
    }
    private fun seeMoreView(txtContent: TextView, txtSeeMore: TextView) {
        txtContent.post {
            val lineCount = txtContent.layout.lineCount
            if(lineCount > 0) {
                if(txtContent.layout.getEllipsisCount(lineCount - 1) > 0) {
                    //더보기 표시
                    txtSeeMore.visibility = View.VISIBLE

                    txtSeeMore.setOnClickListener {
                        txtContent.maxLines = Int.MAX_VALUE
                        txtSeeMore.visibility = View.GONE
                    }
                }
            }
        }
    }
}
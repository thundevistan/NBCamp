package com.example.android_supporters_sns_project

import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.result.ActivityResultLauncher

class TeamMateDetailPageActivity : AppCompatActivity() {

	private lateinit var activityResultLauncher: ActivityResultLauncher<Intent>
	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView(R.layout.activity_team_mate_detail_page)

		var id = intent.getStringExtra("id")


		//각 변수와 이미지 버튼 연동 (변수명은 임의로 설정했는데 작성 룰이 있다면 변경 부탁드립니다)
		val btnDetailContent1 = findViewById<ImageButton>(R.id.teamMateDetail_examPic1_imageButton)


		//각 버튼 클릭 시 해당하는 intData 전달
		btnDetailContent1.setOnClickListener {
			when(id) {
				"이충환" -> {
					val intent = Intent(this, DetailContentViewActivity::class.java)
					intent.putExtra("intDataFromTeamMateDetail", 1)
					startActivity(intent)
					overridePendingTransition(R.anim.fade_in, R.anim.fade_out)
				}
				"이소연" -> {
					val intent = Intent(this, DetailContentViewActivity::class.java)
					intent.putExtra("intDataFromTeamMateDetail", 2)
					startActivity(intent)
					overridePendingTransition(R.anim.fade_in, R.anim.fade_out)
				}
				"윤승재" -> {
					val intent = Intent(this, DetailContentViewActivity::class.java)
					intent.putExtra("intDataFromTeamMateDetail", 3)
					startActivity(intent)
					overridePendingTransition(R.anim.fade_in, R.anim.fade_out)
				}
				"손현준" -> {
					val intent = Intent(this, DetailContentViewActivity::class.java)
					intent.putExtra("intDataFromTeamMateDetail", 4)
					startActivity(intent)
					overridePendingTransition(R.anim.fade_in, R.anim.fade_out)
				}
				"김민준" -> {
					val intent = Intent(this, DetailContentViewActivity::class.java)
					intent.putExtra("intDataFromTeamMateDetail", 5)
					startActivity(intent)
					overridePendingTransition(R.anim.fade_in, R.anim.fade_out)
				}
			}
		}

		// MainPage 로부터 아이디 수신하여 프로필 이미지/아이디 결정
		val profileImage = findViewById<ImageView>(R.id.teamMateDetail_profile_imageView)
		val profileId = findViewById<TextView>(R.id.teamMateDetail_ID_textView)
		val stateMessage = findViewById<TextView>(R.id.teamMateDetail_area_textView)


		when (id) {
			"이충환" -> {
				profileImage.setImageResource(R.drawable.img_profile1)
				stateMessage.text = "자리 비움"
				btnDetailContent1.setImageResource(R.drawable.img_post1)
			}

			"이소연" -> {
				profileImage.setImageResource(R.drawable.img_profile2)
				stateMessage.text = "자리 비움"
				btnDetailContent1.setImageResource(R.drawable.img_post2)
			}

			"윤승재" -> {
				profileImage.setImageResource(R.drawable.img_profile3)
				stateMessage.text = "휴식 중"
				btnDetailContent1.setImageResource(R.drawable.img_post3)
			}

			"손현준" -> {
				profileImage.setImageResource(R.drawable.img_profile4)
				stateMessage.text = "자리 비움"
				btnDetailContent1.setImageResource(R.drawable.img_post4)
			}

			"김민준" -> {
				profileImage.setImageResource(R.drawable.img_profile5)
				stateMessage.text = "과제 중.."
				btnDetailContent1.setImageResource(R.drawable.img_post5)
			}
		}

		// 뒤로 가기 및 본인 프로필 편집
		val btnBackSpace = findViewById<ImageButton>(R.id.teamMateDetail_backSpace_imageButton)

		btnBackSpace.setOnClickListener {
			finish()
			overridePendingTransition(R.anim.fade_in, R.anim.fade_out)
		}

	}
}
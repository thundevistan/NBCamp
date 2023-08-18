package com.example.android_supporters_sns_project

import android.content.Intent
import android.content.res.Configuration
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity


class MainPageActivity : AppCompatActivity() {
	private var arrId = arrayListOf(
		"이충환",
		"이소연",
		"윤승재",
		"손현준",
		"김민준"
	)

	private var arrIdContent = arrayOf(
		"이충환",
		"이소연",
		"윤승재"
	)

	private var arrIcon = arrayListOf(
		R.drawable.img_profile1,
		R.drawable.img_profile2,
		R.drawable.img_profile3,
		R.drawable.img_profile4,
		R.drawable.img_profile5
	)

	private var arrImage = arrayListOf(
		R.drawable.img_post1,
		R.drawable.img_post2,
		R.drawable.img_post3,
		R.drawable.img_post4,
		R.drawable.img_post5
	)

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)

		if (resources.configuration.orientation == Configuration.ORIENTATION_PORTRAIT) {
			setContentView(R.layout.activity_main_page)
		} else {
			setContentView(R.layout.activity_main_page_land)
		}

		val emailData : String? = intent.getStringExtra("email")

		val intentDetail = Intent(this, TeamMateDetailPageActivity::class.java)
		val intentPersonal = Intent(this,PersonalPageActivity::class.java)

		val myPageIcon = findViewById<ImageView>(R.id.main_myPageIcon)
		val userIcon = findViewById<ImageView>(R.id.main_userIcon)
		val userIcon2 = findViewById<ImageView>(R.id.main_userIcon2)
		val userIcon3 = findViewById<ImageView>(R.id.main_userIcon3)
		val userIcon4 = findViewById<ImageView>(R.id.main_userIcon4)
		val userIcon5 = findViewById<ImageView>(R.id.main_userIcon5)

		// TeamMateDetailPage 이동 및 데이터 전달
		myPageIcon.setOnClickListener {
			intentPersonal.putExtra("email", emailData)
			startActivity(intentPersonal)
			overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left)
		}

		userIcon.setOnClickListener {
			intentDetail.putExtra("id", arrId[0])
			startActivity(intentDetail)
			overridePendingTransition(R.anim.fade_in, R.anim.fade_out)
		}

		userIcon2.setOnClickListener {
			intentDetail.putExtra("id", arrId[1])
			startActivity(intentDetail)
			overridePendingTransition(R.anim.fade_in, R.anim.fade_out)
		}

		userIcon3.setOnClickListener {
			intentDetail.putExtra("id", arrId[2])
			startActivity(intentDetail)
			overridePendingTransition(R.anim.fade_in, R.anim.fade_out)
		}

		userIcon4.setOnClickListener {
			intentDetail.putExtra("id", arrId[3])
			startActivity(intentDetail)
			overridePendingTransition(R.anim.fade_in, R.anim.fade_out)
		}

		userIcon5.setOnClickListener {
			intentDetail.putExtra("id", arrId[4])
			startActivity(intentDetail)
			overridePendingTransition(R.anim.fade_in, R.anim.fade_out)
		}

		val postContainer = findViewById<LinearLayout>(R.id.main_addView_postContainer)
		val inflater = LayoutInflater.from(this@MainPageActivity)

		// 게시글 addView
		for (i in arrIdContent.indices) {
			val mainPost = inflater.inflate(R.layout.activity_main_page_post, null)
			val postId = mainPost.findViewById<TextView>(R.id.main_postUserId)
			val postIcon = mainPost.findViewById<ImageView>(R.id.main_postUserIcon)
			val postImageView = mainPost.findViewById<ImageView>(R.id.main_postImage)

			postId.text = arrId[i]
			postIcon.setImageResource(arrIcon[i])
			postImageView.setImageResource(arrImage[i])
			postContainer.addView(mainPost)

			postImageView.setOnClickListener {
				val detailIntent = Intent(this, DetailContentViewActivity::class.java)
				detailIntent.putExtra("intDataFromTeamMateDetail", i+1)
				startActivity(detailIntent)
				overridePendingTransition(R.anim.fade_in, R.anim.fade_out)
			}
		}
	}
}
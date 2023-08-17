package com.example.android_supporters_sns_project

import android.content.Intent
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

	private var arrIcon = arrayListOf(
		R.drawable.ic_main_ex,
		R.drawable.ic_main_ex2,
		R.drawable.ic_main_ex3,
		R.drawable.ic_main_ex4,
		R.drawable.ic_main_ex5
	)

	private var arrImage = arrayListOf(
		R.drawable.ic_main_ex,
		R.drawable.ic_main_ex2,
		R.drawable.ic_main_ex3,
		R.drawable.ic_main_ex4,
		R.drawable.ic_main_ex5
	)

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView(R.layout.activity_main_page)

		val intentDetail = Intent(this, TeamMateDetailPageActivity::class.java)

		val myPageIcon = findViewById<ImageView>(R.id.main_myPageIcon)
		val userIcon = findViewById<ImageView>(R.id.main_userIcon)
		val userIcon2 = findViewById<ImageView>(R.id.main_userIcon2)
		val userIcon3 = findViewById<ImageView>(R.id.main_userIcon3)
		val userIcon4 = findViewById<ImageView>(R.id.main_userIcon4)
		val userIcon5 = findViewById<ImageView>(R.id.main_userIcon5)

		// TeamMateDetailPage 이동 및 데이터 전달
		myPageIcon.setOnClickListener {
			intentDetail.putExtra("loginId", "login")
			startActivity(intentDetail)
		}

		userIcon.setOnClickListener {
			intentDetail.putExtra("id", arrId[0])
			startActivity(intentDetail)
		}

		userIcon2.setOnClickListener {
			intentDetail.putExtra("id", arrId[1])
			startActivity(intentDetail)
		}

		userIcon3.setOnClickListener {
			intentDetail.putExtra("id", arrId[2])
			startActivity(intentDetail)
		}

		userIcon4.setOnClickListener {
			intentDetail.putExtra("id", arrId[3])
			startActivity(intentDetail)
		}

		userIcon5.setOnClickListener {
			intentDetail.putExtra("id", arrId[4])
			startActivity(intentDetail)
		}

		val postContainer = findViewById<LinearLayout>(R.id.main_addView_postContainer)
		val inflater = LayoutInflater.from(this@MainPageActivity)

		// 게시글 addView
		for (i in 0 until arrId.size) {
			val mainPost = inflater.inflate(R.layout.activity_main_page_post, null)
			val postId = mainPost.findViewById<TextView>(R.id.main_postUserId)
			val postIcon = mainPost.findViewById<ImageView>(R.id.main_postUserIcon)
			val postImageView = mainPost.findViewById<ImageView>(R.id.main_postImage)

			postId.text = arrId[i]
			postIcon.setImageResource(arrIcon[i])
			postImageView.setImageResource(arrImage[i])
			postContainer.addView(mainPost)
		}
	}
}
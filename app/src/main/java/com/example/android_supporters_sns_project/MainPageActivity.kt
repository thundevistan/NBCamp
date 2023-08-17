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

		val intent = Intent(this, TeamMateDetailPageActivity::class.java)
		val userIcon = findViewById<ImageView>(R.id.main_userIcon)
		val userIcon2 = findViewById<ImageView>(R.id.main_userIcon2)
		val userIcon3 = findViewById<ImageView>(R.id.main_userIcon3)
		val userIcon4 = findViewById<ImageView>(R.id.main_userIcon4)
		val userIcon5 = findViewById<ImageView>(R.id.main_userIcon5)

		// 팀원 클릭 시 TeamMateDetailPage 이동 및 데이터 전달
		userIcon.setOnClickListener {
			intent.putExtra("id", arrId[0])
			startActivity(intent)
		}

		userIcon2.setOnClickListener {
			intent.putExtra("id", arrId[1])
			startActivity(intent)
		}

		userIcon3.setOnClickListener {
			intent.putExtra("id", arrId[2])
			startActivity(intent)
		}

		userIcon4.setOnClickListener {
			intent.putExtra("id", arrId[3])
			startActivity(intent)
		}

		userIcon5.setOnClickListener {
			intent.putExtra("id", arrId[4])
			startActivity(intent)
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
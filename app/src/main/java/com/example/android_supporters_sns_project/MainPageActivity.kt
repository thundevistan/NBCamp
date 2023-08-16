package com.example.android_supporters_sns_project

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
package com.example.android_supporters_sns_project

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class DetailContentViewActivity : AppCompatActivity() {
	@SuppressLint("SetTextI18n")
	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView(R.layout.activity_detail_content_view)

		val emailData: String? = intent.getStringExtra("email")

		//받아온 intData 저장 (디폴트 0)
		val intData: Int = intent.getIntExtra("intDataFromTeamMateDetail", 0)

		//Data가 필요한 View 연동
		val imgProfile = findViewById<ImageView>(R.id.detailContentView_profile_imageView)
		val txtID = findViewById<TextView>(R.id.detailContentView_id_textview)
		val imgContent = findViewById<ImageView>(R.id.detailContentView_contentimage_imageview)
		val txtContent = findViewById<TextView>(R.id.detailContentView_content_textview)
		val backButton = findViewById<ImageButton>(R.id.detailContentView_backSpace_imageButton)
		val likeButton = findViewById<ImageButton>(R.id.like_button)
		val txtSeeMore = findViewById<TextView>(R.id.detailContentView_seeMore_textview)

		//addview
		val contentLayout = findViewById<LinearLayout>(R.id.detailContentView_comment_layout)
		val inflater = LayoutInflater.from(this@DetailContentViewActivity)
		val commentButton = findViewById<Button>(R.id.detailContentView_replyConfirm_button)
		val commentEditText = findViewById<EditText>(R.id.detailContentView_reply_edittext)

		commentButton.setOnClickListener {
			val innerContent = inflater.inflate(R.layout.item_comment, null)
			val innerContentText = innerContent.findViewById<EditText>(R.id.item_comment_content)
			val writerName = innerContent.findViewById<TextView>(R.id.writerName)
			innerContentText.setText(commentEditText.text.toString())
			writerName.text = emailData?.let { it1 -> MemberManager.getMemberByEmail(it1)?.name}
			contentLayout.addView(innerContent)
			commentEditText.text.clear()

			val deleteButton = innerContent.findViewById<ImageView>(R.id.delete_comment_button)
			deleteButton.setOnClickListener {
				contentLayout.removeView(innerContent) // 댓글 삭제 로직
			}
		}

		var isLiked = false // 초기 상태: 좋아요X

		//intData에 따라 각 Data 설정
		when (intData) {
			1 -> {
				imgProfile.setImageResource(R.drawable.img_profile1)
				txtID.setText("이충환")
				imgContent.setImageResource(R.drawable.img_post1)
				txtContent.setText("vertical, horizontal로 2가지 가이드 라인을 만든다. 이 두 가이드 라인은 퍼센트를 활용하여 만들었기 때문에 어떤 화면에서든 같은 비율로 존재한다. 이를 바탕으로 constraint를 지정해주면 반응형 UI를 만들 수 있다. ")
			}

			2 -> {
				imgProfile.setImageResource(R.drawable.img_profile2)
				txtID.setText("이소연")
				imgContent.setImageResource(R.drawable.img_post2)
				txtContent.setText("tea time☕")
			}

			3 -> {
				imgProfile.setImageResource(R.drawable.img_profile3)
				txtID.setText("윤승재")
				imgContent.setImageResource(R.drawable.img_post3)
				txtContent.setText("진짜 재밌습니다... 무조건 보세요!")
			}

			4 -> {
				imgProfile.setImageResource(R.drawable.img_profile4)
				txtID.setText("손현준")
				imgContent.setImageResource(R.drawable.img_post4)
				txtContent.setText("제주도 여행")
			}

			5 -> {
				imgProfile.setImageResource(R.drawable.img_profile5)
				txtID.setText("김민준")
				imgContent.setImageResource(R.drawable.img_post5)
				txtContent.setText("팝콘 냠냠\uD83C\uDF7F")
			}
		}

		likeButton.setOnClickListener {
			isLiked = !isLiked // 상태 토글
			likeButton.isSelected = isLiked // isLiked에 따라 버튼의 선택 상태 설정
		}

		backButton.setOnClickListener {
			finish()
			overridePendingTransition(R.anim.fade_in, R.anim.fade_out)
		}
		seeMoreView(txtContent, txtSeeMore)
	}

	//더보기 기능 함수
	private fun seeMoreView(txtContent: TextView, txtSeeMore: TextView) {
		txtContent.post {
			val lineCount = txtContent.layout.lineCount
			if (lineCount > 0) {
				if (txtContent.layout.getEllipsisCount(lineCount - 1) > 0) {
					txtSeeMore.visibility = View.VISIBLE

					txtSeeMore.setOnClickListener {
						txtContent.maxLines = Int.MAX_VALUE
						txtSeeMore.visibility = View.GONE
					}
				}
			}
		}
	}
	override fun dispatchTouchEvent(ev: MotionEvent): Boolean {
		val imm: InputMethodManager =
			getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
		imm.hideSoftInputFromWindow(currentFocus?.windowToken, 0)
		return super.dispatchTouchEvent(ev)
	}
}
package com.kotdev99.android.week7_assignment

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.Snackbar
import com.kotdev99.android.week7_assignment.databinding.ActivityDetailBinding

class DetailActivity : AppCompatActivity() {

	private val binding by lazy { ActivityDetailBinding.inflate(layoutInflater) }
	private var isLike: Boolean = false

	private val position: Int by lazy {
		intent.getIntExtra("INDEX", 0)
	}

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView(binding.root)

		val product = intent.getParcelableExtra<Product>("product")


		binding.ivThumbDetail.setImageResource(product!!.thumb)
		binding.tvSeller.text = product.seller
		binding.tvAddrDetail.text = product.addr
		binding.tvNameDetail.text = product.name
		binding.tvDescription.text = product.description
		emoji()

		binding.ivBack.setOnClickListener {
			exit()
		}

		isLike = product.isFav
		binding.ivFavDetail.setImageResource(if (isLike) {R.drawable.ic_isfav} else {R.drawable.ic_fav})

		binding.ivFavDetail.setOnClickListener {
			if (!isLike) {
				binding.ivFavDetail.setImageResource(R.drawable.ic_isfav)
				Snackbar.make(binding.detailLayout, "관심 목록에 추가되었습니다.", Snackbar.LENGTH_SHORT).show()
				isLike = true
			} else {
				binding.ivFavDetail.setImageResource(R.drawable.ic_fav)
				isLike = false
			}
		}
	}

	// 이모지
	private fun emoji() {
		val unicode = 0x1F61C
		val emoji = String(Character.toChars(unicode))

		binding.tvEmoji.text = emoji
	}

	private fun exit() {
		val intent = Intent(this, MainActivity::class.java).apply {
			putExtra("INDEX", position)
			putExtra("isLike", isLike)
		}
		setResult(RESULT_OK, intent)
		if (!isFinishing) finish()
	}

	override fun onBackPressed() {
		exit()
	}
}
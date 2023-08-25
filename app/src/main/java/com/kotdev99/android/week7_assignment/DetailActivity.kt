package com.kotdev99.android.week7_assignment

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.kotdev99.android.week7_assignment.databinding.ActivityDetailBinding

class DetailActivity : AppCompatActivity() {

	private lateinit var binding: ActivityDetailBinding

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)

		binding = ActivityDetailBinding.inflate(layoutInflater)
		setContentView(binding.root)

		var product = intent.getParcelableExtra<Product>("product")

		binding.ivThumbDetail.setImageResource(product!!.thumb)
		binding.tvSeller.text = product.seller
		binding.tvAddrDetail.text = product.addr
		binding.tvNameDetail.text = product.name
		binding.tvDescription.text = product.description
		emoji()

		binding.ivBack.setOnClickListener {
			finish()
		}

	}

	// 이모지
	private fun emoji() {
		val unicode = 0x1F61C
		val emoji = String(Character.toChars(unicode))

		binding.tvEmoji.text = emoji
	}
}
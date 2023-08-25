package com.kotdev99.android.week7_assignment

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.kotdev99.android.week7_assignment.databinding.ActivityDetailBinding

class DetailActivity : AppCompatActivity() {

	private lateinit var binding: ActivityDetailBinding
	private lateinit var productList: ArrayList<Product>

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)

		binding = ActivityDetailBinding.inflate(layoutInflater)
		setContentView(binding.root)

		productList = intent.getParcelableArrayListExtra<Product>("product") as ArrayList<Product>
		val unicode = 0x1F61C


		binding.ivThumbDetail.setImageResource(productList[0].thumb)
		binding.tvSeller.text = productList[0].seller
		binding.tvAddrDetail.text = productList[0].addr
		val emoji = String(Character.toChars(unicode))
		binding.tvEmoji.text = emoji    // 이모지
		binding.tvNameDetail.text = productList[6].name
		binding.tvDescription.text = productList[6].description
	}
}
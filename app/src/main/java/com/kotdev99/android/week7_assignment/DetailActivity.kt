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
		binding.tvTitle.text = productList[0].name

	}
}
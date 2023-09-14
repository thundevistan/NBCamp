package com.kotdev99.android.lifecycle

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.kotdev99.android.lifecycle.databinding.ActivityDetailBinding

class DetailActivity : AppCompatActivity() {

	private val binding by lazy { ActivityDetailBinding.inflate(layoutInflater) }

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView(binding.root)

		// Glide 로 GIF 출력
		Glide.with(this).load(R.drawable.gif_sample).into(binding.ivSample)
	}

	override fun onStart() {
		super.onStart()
		Log.d("DetailActivity", "onStart")
	}

	override fun onResume() {
		super.onResume()
		Log.d("DetailActivity", "onResume")
	}

	override fun onPause() {
		super.onPause()
		Log.d("DetailActivity", "onPause")
	}

	override fun onStop() {
		super.onStop()
		Log.d("DetailActivity", "onStop")
	}

	override fun onDestroy() {
		super.onDestroy()
		Log.d("DetailActivity", "onDestroy")
	}
}
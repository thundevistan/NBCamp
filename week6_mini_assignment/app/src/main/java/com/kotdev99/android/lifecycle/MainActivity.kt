package com.kotdev99.android.lifecycle

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.commit
import com.kotdev99.android.lifecycle.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

	private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }
	private val fragmentManager = supportFragmentManager

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView(binding.root)
		Log.d("MainActivity", "onCreate")

		// DetailActivity 인텐트 버튼
		binding.btnDetail.setOnClickListener {
			startActivity(Intent(this, DetailActivity::class.java))
		}

		// MainFragment 추가, 제거 버튼
		binding.btnMainFrag.setOnClickListener {
			addMainFrag()
			rmMainFrag()

			// 버튼 텍스트 제어
			val currentFrag = fragmentManager.findFragmentByTag("currentFrag")
			if (currentFrag != null) binding.btnMainFrag.text = getString(R.string.Fragment_ADD)
			if (currentFrag == null) binding.btnMainFrag.text = getString(R.string.Fragment_REMOVE)
		}
	}

	// MainFragment 추가
	private fun addMainFrag() {
		val currentFrag = fragmentManager.findFragmentByTag("currentFrag")

		fragmentManager.commit {
			if (currentFrag == null) {
				setReorderingAllowed(true)
				add(R.id.fragmentContainerView, MainFragment(), "currentFrag")
			}
		}
	}

	// MainFragment 제거
	private fun rmMainFrag() {
		val currentFrag = fragmentManager.findFragmentByTag("currentFrag")

		fragmentManager.commit {
			if (currentFrag != null) {
				remove(currentFrag)
			}
		}
	}

	override fun onStart() {
		super.onStart()
		Log.d("MainActivity", "onStart")
	}

	override fun onResume() {
		super.onResume()
		Log.d("MainActivity", "onResume")
	}

	override fun onPause() {
		super.onPause()
		Log.d("MainActivity", "onPause")
	}

	override fun onStop() {
		super.onStop()
		Log.d("MainActivity", "onStop")
	}

	override fun onDestroy() {
		super.onDestroy()
		Log.d("MainActivity", "onDestroy")
	}

	// 확장 함수
	fun showToast(msg: String, time: Int = Toast.LENGTH_SHORT) {
		Toast.makeText(this, msg, time).show()
	}
}
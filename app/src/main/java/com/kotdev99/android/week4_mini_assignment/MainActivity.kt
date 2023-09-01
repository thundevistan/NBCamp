package com.kotdev99.android.week4_mini_assignment

import android.content.res.Configuration
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.kotdev99.android.week4_mini_assignment.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

	private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView(binding.root)
		Log.d("MainActivity", "onCreate")

		setFragmentPortrait(TitleFragment())
	}

	override fun onConfigurationChanged(newConfig: Configuration) {
		super.onConfigurationChanged(newConfig)

		if (newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE) {
			setFragmentPortrait(TitleFragment())
			Toast.makeText(this, "가로", Toast.LENGTH_SHORT).show()
		} else if (newConfig.orientation == Configuration.ORIENTATION_PORTRAIT) {
			Toast.makeText(this, "세로", Toast.LENGTH_SHORT).show()
		}
	}

	private fun setFragmentPortrait(frag: Fragment) {
		Log.d("MainActivity", "setFragmentPortrait")

		val fragmentManager = supportFragmentManager
		val transaction = fragmentManager.beginTransaction()
		transaction.replace(R.id.fragment_content, frag)
		transaction.setReorderingAllowed(true)
		transaction.commit()
	}

	/* 화면 회전 구현 실패
	private fun setFragmentLandscape(fragPort: Fragment, fragLand: Fragment) {
		Log.d("MainActivity", "setFragmentLandscape")
		val fragmentManager = supportFragmentManager

		val transaction = fragmentManager.beginTransaction()
		transaction.replace(R.id.fragment_content, fragPort)
		transaction.setReorderingAllowed(true)
		transaction.commit()

		val transaction1 = fragmentManager.beginTransaction()
		transaction1.replace(R.id.fragment_sub, fragLand)
		transaction1.setReorderingAllowed(true)
		transaction1.commit()
	} */
}
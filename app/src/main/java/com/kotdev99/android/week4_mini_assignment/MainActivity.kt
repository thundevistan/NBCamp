package com.kotdev99.android.week4_mini_assignment

import android.content.res.Configuration
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.kotdev99.android.week4_mini_assignment.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

	private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView(binding.root)
		Log.d("MainActivity", "onCreate()")

		setFragmentPortrait(TitleFragment())
	}

	override fun onConfigurationChanged(newConfig: Configuration) {
		super.onConfigurationChanged(newConfig)
		setContentView(R.layout.activity_main)
	}

	private fun setFragmentPortrait(frag: Fragment) {
		val fragmentManager = supportFragmentManager
		val transaction = fragmentManager.beginTransaction()
		transaction.add(R.id.fragment_content, frag)
		transaction.setReorderingAllowed(true)
		transaction.commit()

		Log.d("MainActivity", "setFragmentPortrait()")
	}

//	private fun setFragmentLandscape(fragPort: Fragment, fragLand: Fragment) {
//		val fragmentManager = supportFragmentManager
//		val transaction = fragmentManager.beginTransaction()
//		transaction.add(R.id.fragment_content_title, fragPort)
//		transaction.setReorderingAllowed(true)
//		transaction.commit()
//
//		val transaction1 = fragmentManager.beginTransaction()
//		transaction1.add(R.id.fragment_content_article, fragLand)
//		transaction1.setReorderingAllowed(true)
//		transaction1.commit()
//
//		Log.d("MainActivity", "setFragmentLandscape()")
//	}
}
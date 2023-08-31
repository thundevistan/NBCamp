package com.kotdev99.android.week4_mini_assignment

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

		setFragment(TitleFragment())
	}

	private fun setFragment(frag: Fragment) {
		val fragmentManager = supportFragmentManager
		val transaction = fragmentManager.beginTransaction()
		transaction.add(R.id.fragment_content, frag)
		transaction.setReorderingAllowed(true)
		transaction.commit()

		Log.d("MainActivity", "setFragment()")
	}
}
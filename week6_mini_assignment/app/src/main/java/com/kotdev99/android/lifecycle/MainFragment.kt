package com.kotdev99.android.lifecycle

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.kotdev99.android.lifecycle.databinding.FragmentMainBinding

class MainFragment : Fragment() {

	private lateinit var binding: FragmentMainBinding

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		Log.d("MainFragment", "onCreate")
	}

	override fun onCreateView(
		inflater: LayoutInflater, container: ViewGroup?,
		savedInstanceState: Bundle?
	): View {
		Log.d("MainFragment", "onCreateView")
		binding = FragmentMainBinding.inflate(inflater, container, false)
		return binding.root
	}

	override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
		super.onViewCreated(view, savedInstanceState)
		Log.d("MainFragment", "onViewCreated")
	}

	override fun onViewStateRestored(savedInstanceState: Bundle?) {
		super.onViewStateRestored(savedInstanceState)
		Log.d("MainFragment", "onViewStateRestored")
	}

	override fun onStart() {
		super.onStart()
		Log.d("MainFragment", "onStart")
	}

	override fun onResume() {
		super.onResume()
		Log.d("MainFragment", "onResume")

		// 토스트 출력 버튼
		binding.btnShowToast.setOnClickListener {
			if (binding.evTime.text.isNotEmpty()) {
				var time = binding.evTime.text.toString()   // EditText 의 내용

				when (time) {
					"S" -> {
						(activity as MainActivity).showToast("짧은 토스트 출력")
					}

					"L" -> {
						(activity as MainActivity).showToast("긴 토스트 출력", Toast.LENGTH_LONG)
					}
				}
			}
		}
	}

	override fun onStop() {
		super.onStop()
		Log.d("MainFragment", "onStop")
	}

	override fun onSaveInstanceState(outState: Bundle) {
		super.onSaveInstanceState(outState)
		Log.d("MainFragment", "onSaveInstanceState")
	}

	override fun onDestroyView() {
		super.onDestroyView()
		Log.d("MainFragment", "onDestroyView")
	}

	override fun onDestroy() {
		super.onDestroy()
		Log.d("MainFragment", "onDestroy")
	}
}
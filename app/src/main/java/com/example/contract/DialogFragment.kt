package com.example.contract

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import androidx.fragment.app.DialogFragment
import com.example.contract.databinding.FragmentDialogBinding

class DialogFragment : DialogFragment() {

	private lateinit var binding: FragmentDialogBinding

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)

		isCancelable = true
	}

	override fun onCreateView(
		inflater: LayoutInflater,
		container: ViewGroup?,
		savedInstanceState: Bundle?
	): View? {
		dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
		dialog?.window?.requestFeature(Window.FEATURE_NO_TITLE)
		binding = FragmentDialogBinding.inflate(inflater, container, false)
		return binding.root
	}

	override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
		super.onViewCreated(view, savedInstanceState)

		val profile = binding.ivDialogProfile
		val name = binding.editDialogName
		val phone = binding.editDialogPhone
		val email = binding.editDialogEmail
		val cancel = binding.btnDialogCancel
		val save = binding.btnDialogSave

		profile.clipToOutline = true

		cancel.setOnClickListener {
			dismiss()
		}
	}
}
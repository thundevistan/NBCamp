package com.example.contract

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.text.Editable
import android.text.TextUtils
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import com.example.contract.databinding.FragmentDialogBinding

class DialogFragment : DialogFragment() {

	private lateinit var binding: FragmentDialogBinding
	private val profile by lazy { binding.ivDialogProfile }
	private val name by lazy { binding.editDialogName }
	private val phone by lazy { binding.editDialogPhone }
	val email by lazy { binding.editDialogEmail }
	private val cancel by lazy { binding.btnDialogCancel }
	val save by lazy { binding.btnDialogSave }

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

		save.isEnabled = false
		profile.clipToOutline = true

		email.addTextChangedListener(object : TextWatcher {
			override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

			}

			override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

			}

			override fun afterTextChanged(s: Editable?) {
				if (!android.util.Patterns.EMAIL_ADDRESS.matcher(s.toString()).matches()) {
					email.setBackgroundResource(R.drawable.shape_dialog_edit_npe)
					save.isEnabled = false
				} else {
					email.setBackgroundResource(R.drawable.selector_dialog_edit)
					addContact()
				}
				save.setOnClickListener {
					if (android.util.Patterns.EMAIL_ADDRESS.matcher(s.toString()).matches()) {
						Toast.makeText(activity, "저장 완료", Toast.LENGTH_SHORT).show()
						// 데이터 add
					}
				}
			}
		})

		cancel.setOnClickListener {
			dismiss()
		}
	}

	fun addContact() {
		var isName = TextUtils.isEmpty(name.text.toString())
		var isPhone = TextUtils.isEmpty(phone.text.toString())
		var isEmail = TextUtils.isEmpty(email.text.toString())

		if (!isName && !isPhone && !isEmail) {
			save.isEnabled = true
		}
	}
}
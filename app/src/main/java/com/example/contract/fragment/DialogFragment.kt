package com.example.contract.fragment

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.text.Editable
import android.text.TextUtils
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import com.example.contract.R
import com.example.contract.sampledata.ContactItem
import com.example.contract.databinding.FragmentDialogBinding
import com.example.contract.sampledata.ContactManager

class DialogFragment : DialogFragment() {

	private lateinit var binding: FragmentDialogBinding
	private val profile by lazy { binding.ivDialogProfile }
	private val name by lazy { binding.editDialogName }
	private val phone by lazy { binding.editDialogPhone }
	private val email by lazy { binding.editDialogEmail }
	private val event by lazy { binding.editDialogEvent }
	private val cancel by lazy { binding.btnDialogCancel }
	private val save by lazy { binding.btnDialogSave }

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
					isSaved()
				}
				save.setOnClickListener {
					if (android.util.Patterns.EMAIL_ADDRESS.matcher(s.toString()).matches()) {
						Toast.makeText(activity, "저장 완료", Toast.LENGTH_SHORT).show()
						addContact()
						dismiss()
					}
				}
			}
		})

		cancel.setOnClickListener {
			dismiss()
		}
	}

	fun isSaved() {
		var isName = TextUtils.isEmpty(name.text.toString())
		var isPhone = TextUtils.isEmpty(phone.text.toString())
		var isEmail = TextUtils.isEmpty(email.text.toString())

		if (!isName && !isPhone && !isEmail) {
			save.isEnabled = true
		}
	}

	fun addContact() {
		val name = this.name.text.toString()
		val phone = this.phone.text.toString()
		val email = this.email.text.toString()
		val event = this.event.text.toString()
		Log.d("addContact", "name: $name, phone: $phone, email: $email, event: $event")

		ContactManager.addContact(
			ContactItem(
				R.drawable.ic_sample,
				name,
				null,
				false,
				phone,
				email,
				event
			)
		)
	}
}
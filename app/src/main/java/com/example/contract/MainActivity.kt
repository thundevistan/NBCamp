package com.example.contract

import android.Manifest
import android.content.pm.PackageManager
import android.database.Cursor
import android.os.Bundle
import android.provider.ContactsContract
import android.util.Log
import android.view.View
import android.widget.PopupMenu
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.example.contract.adapter.ContractAdapter
import com.example.contract.databinding.ActivityMainBinding
import com.example.contract.fragment.DialogFragment
import com.example.contract.fragment.ExitDialogFragment
import com.example.contract.sampledata.ContactItem
import com.example.contract.sampledata.ContactManager
import com.google.android.material.tabs.TabLayoutMediator


class MainActivity : AppCompatActivity() {
	private lateinit var binding: ActivityMainBinding
	private lateinit var viewPager2: ViewPager2

	private val CONTACTS_PERMISSION_CODE = 101

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		requestContactsPermission()
		initView()
		fabButton()
	}

	private fun initView() {
		binding = ActivityMainBinding.inflate(layoutInflater)
		setContentView(binding.root)
		setSupportActionBar(binding.toolbar)
		supportActionBar?.title = null

		val tabLayout = binding.tabLayout
		viewPager2 = binding.viewPager

		// 어댑터 생성
		val adapter = ContractAdapter(this)

		// ViewPager2에 어댑터 설정
		viewPager2.adapter = adapter

		tabLayout.tabRippleColor = null

		// 연락처 불러오기
		readContacts()

		// TabLayoutMediator를 사용하여 ViewPager2와 TabLayout을 연결
		TabLayoutMediator(tabLayout, viewPager2) { tab, position ->
			when (position) {
				0 -> {
					tab.text = "Contact"
				}

				1 -> {
					tab.text = "My Page"
				}
			}
		}.attach()

		// ViewPager2의 페이지 변경 리스너 설정
		viewPager2.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
			override fun onPageSelected(position: Int) {
				super.onPageSelected(position)
				when (position) {
					0 -> {
						binding.fabButton.show()
						binding.menuButton.visibility = View.VISIBLE
						binding.backButton.setOnClickListener {
							onBackPressed()
						}
						binding.menuButton.setOnClickListener { view ->
							showPopupMenu(view)
						}
					}

					1 -> {
						binding.fabButton.hide()
						binding.menuButton.visibility = View.INVISIBLE
					}
				}
			}
		})
	}

	private fun fabButton() {
		binding.fabButton.setOnClickListener {
			DialogFragment().show(
				supportFragmentManager, "DialogFrag"
			)
		}
	}

	@Deprecated("Deprecated in Java")
	override fun onBackPressed() {
		if (viewPager2.currentItem == 0) {
			val dialogFragment = ExitDialogFragment()
			dialogFragment.show(supportFragmentManager, "ExitDialogFragment")
		} else {
			viewPager2.currentItem = 0
		}
	}

	override fun onRequestPermissionsResult(
		requestCode: Int,
		permissions: Array<out String>,
		grantResults: IntArray
	) {
		super.onRequestPermissionsResult(requestCode, permissions, grantResults)
		if (requestCode == CONTACTS_PERMISSION_CODE && grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
			readContacts()
		} else {
			Log.d("test", "권한 거부됨")
		}
	}

	private fun readContacts() {
		val contentResolver = contentResolver
		val projection = arrayOf(
			ContactsContract.Contacts._ID,
			ContactsContract.Contacts.DISPLAY_NAME
		)

		val cursor: Cursor? = contentResolver.query(
			ContactsContract.Contacts.CONTENT_URI,
			projection,
			null,
			null,
			null
		)

        if (cursor != null) {
            val idColumnIndex = cursor.getColumnIndex(ContactsContract.Contacts._ID)
            val nameColumnIndex = cursor.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME)
            val phoneProjection = arrayOf(ContactsContract.CommonDataKinds.Phone.NUMBER)
            val emailProjection = arrayOf(ContactsContract.CommonDataKinds.Email.DATA)

            while (cursor.moveToNext()) {
                val contactId = cursor.getLong(idColumnIndex)
                val contactName = cursor.getString(nameColumnIndex)
                val phoneNumber = getPhoneNumber(contactId)
                val emailAddress = getEmailAddress(contactId)

                Log.d(
                    "test",
                    "ID: $contactId, Name: $contactName, Phone: $phoneNumber, Email: $emailAddress"
                )

                ContactManager.addContact(
                    ContactItem(
                        0,
                        contactName,
                        "",
                        false,
                        phoneNumber,
                        emailAddress!!,
                        ""
                    )
                )
            }

			cursor.close()
		}
	}

	private fun getEmailAddress(contactId: Long): String? {
		val contentResolver = contentResolver
		val emailProjection = arrayOf(ContactsContract.CommonDataKinds.Email.DATA)

		val selection = "${ContactsContract.CommonDataKinds.Email.CONTACT_ID} = ?"
		val selectionArgs = arrayOf(contactId.toString())

		val emailCursor: Cursor? = contentResolver.query(
			ContactsContract.CommonDataKinds.Email.CONTENT_URI,
			emailProjection,
			selection,
			selectionArgs,
			null
		)

		var emailAddress: String? = null

		if (emailCursor != null) {
			val emailColumnIndex =
				emailCursor.getColumnIndex(ContactsContract.CommonDataKinds.Email.DATA)
			if (emailColumnIndex != -1 && emailCursor.moveToFirst()) {
				emailAddress = emailCursor.getString(emailColumnIndex)
			}
			emailCursor.close()
		}

		return emailAddress
	}

	private fun getPhoneNumber(contactId: Long): String? {
		val contentResolver = contentResolver
		val phoneProjection = arrayOf(ContactsContract.CommonDataKinds.Phone.NUMBER)
		val selection = "${ContactsContract.CommonDataKinds.Phone.CONTACT_ID} = ?"
		val selectionArgs = arrayOf(contactId.toString())

		val phoneCursor: Cursor? = contentResolver.query(
			ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
			phoneProjection,
			selection,
			selectionArgs,
			null
		)

		var phoneNumber: String? = null

		if (phoneCursor != null) {
			val numberColumnIndex =
				phoneCursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER)
			if (numberColumnIndex != -1 && phoneCursor.moveToFirst()) {
				phoneNumber = phoneCursor.getString(numberColumnIndex)
			}
			phoneCursor.close()
		}

		return phoneNumber
	}

	private fun requestContactsPermission() {
		if (ContextCompat.checkSelfPermission(
				this,
				Manifest.permission.READ_CONTACTS
			) != PackageManager.PERMISSION_GRANTED
		) {
			ActivityCompat.requestPermissions(
				this,
				arrayOf(Manifest.permission.READ_CONTACTS),
				CONTACTS_PERMISSION_CODE
			)
		}
	}

	private fun showPopupMenu(view: View) {
		val popupMenu = PopupMenu(this, view)
		popupMenu.menuInflater.inflate(R.menu.menu_list_type, popupMenu.menu)
		val contactRv = binding.root.findViewById<RecyclerView>(R.id.contactRv)

		popupMenu.setOnMenuItemClickListener { menuItem ->
			when (menuItem.itemId) {
				R.id.menu_grid -> {
					val spanCount = 4
					contactRv.layoutManager = GridLayoutManager(this, spanCount)
					true
				}

				R.id.menu_list -> {
					contactRv.layoutManager = LinearLayoutManager(this)
					true
				}

				else -> false
			}
		}
		popupMenu.show()
	}
}
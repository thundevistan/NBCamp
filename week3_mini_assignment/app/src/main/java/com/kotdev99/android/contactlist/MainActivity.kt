package com.kotdev99.android.contactlist

import android.annotation.SuppressLint
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.provider.ContactsContract
import android.util.Log
import android.view.View
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.kotdev99.android.contactlist.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
	private lateinit var binding: ActivityMainBinding
	private lateinit var requestLauncher: ActivityResultLauncher<Intent>
	private lateinit var contactAdapter: RecyclerAdapter
	private var data = ArrayList<ContactData>()


	@SuppressLint("NotifyDataSetChanged")
	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		binding = ActivityMainBinding.inflate(layoutInflater)
		setContentView(binding.root)

		initRecycler()
		permission()
		fabClick()

		requestLauncher =
			registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
				if (it.resultCode == RESULT_OK) {
					val cursor = contentResolver.query(
						it.data!!.data!!,
						arrayOf<String>(
//							ContactsContract.CommonDataKinds.Phone.PHOTO_ID,    // 아마도 비트맵 처리 해야 하는 듯?
							ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME,
							ContactsContract.CommonDataKinds.Phone.NUMBER
						),
						null,
						null,
						null
					)
					Log.d("requestLauncher", "cursor size : ${cursor?.count}")

					if (cursor!!.moveToFirst()) {
//						val profile = cursor.getInt(0)
						val name = cursor.getString(0)
						val tel = cursor.getString(1)
						data.add(
							ContactData(
								R.drawable.ic_launcher_background,
								name,
								tel,
								ViewType.view_type1
							)
						)
						contactAdapter.notifyDataSetChanged()
					}
				}
			}
	}

	private fun permission() {
		val permission = ContextCompat.checkSelfPermission(this, "android.permission.READ_CONTACTS")
		if (permission == PackageManager.PERMISSION_GRANTED) {
			Log.d("permission", "PERMISSION_GRANTED")
		} else {
			ActivityCompat.requestPermissions(
				this,
				arrayOf("android.permission.READ_CONTACTS"),
				100
			)
		}
	}

	override fun onRequestPermissionsResult(
		requestCode: Int,
		permissions: Array<out String>,
		grantResults: IntArray
	) {
		super.onRequestPermissionsResult(requestCode, permissions, grantResults)
		if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
			Log.d("permission", "PERMISSION_GRANTED")
		} else {
			Log.d("permission", "PERMISSION_DENIED")
		}
	}

	private fun initRecycler() {
		contactAdapter = RecyclerAdapter()

		binding.rvMain.apply {
			setHasFixedSize(true)
			layoutManager = LinearLayoutManager(
				this@MainActivity,
				LinearLayoutManager.VERTICAL,
				false
			)
			addItemDecoration(
				DividerItemDecoration(
					this@MainActivity,
					LinearLayoutManager.VERTICAL
				)
			)
			adapter = contactAdapter
		}

		contactAdapter.itemClick = object : RecyclerAdapter.ItemClick {
			override fun onClick(view: View, position: Int) {
				val intent = Intent(Intent.ACTION_DIAL)
				intent.data = Uri.parse("tel:${data[position].tel}")
				startActivity(intent)
			}
		}

		data.apply {
			add(ContactData(R.drawable.profile1, "장첸", "010-2512-9631", ViewType.view_type1))
			add(ContactData(R.drawable.profile2, "이중구", "010-4323-4134", ViewType.view_type1))
			add(ContactData(R.drawable.profile3, "곽철용", "010-5455-6453", ViewType.view_type2))
			add(ContactData(R.drawable.profile4, "셜록", "010-6747-1116", ViewType.view_type1))
			add(ContactData(R.drawable.profile5, "오일남", "010-5788-3666", ViewType.view_type1))
			add(ContactData(R.drawable.profile6, "장이수", "010-9000-1668", ViewType.view_type2))
			add(ContactData(R.drawable.profile7, "장경철", "010-4356-6575", ViewType.view_type2))
			add(ContactData(R.drawable.profile8, "박두만", "010-4777-2199", ViewType.view_type1))
			add(ContactData(R.drawable.profile9, "면가", "010-6578-3547", ViewType.view_type2))
			add(ContactData(R.drawable.profile10, "강해상", "010-5678-4385", ViewType.view_type1))
		}
		contactAdapter.setData(list = data)
	}

	private fun fabClick() {
		binding.fab.setOnClickListener {
			val intent =
				Intent(Intent.ACTION_PICK, ContactsContract.CommonDataKinds.Phone.CONTENT_URI)
			requestLauncher.launch(intent)
		}
	}
}
package com.kotdev99.android.contactlist

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.kotdev99.android.contactlist.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
	private lateinit var binding: ActivityMainBinding
	private lateinit var contactAdapter: RecyclerAdapter
	private var data = ArrayList<ContactData>()

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		binding = ActivityMainBinding.inflate(layoutInflater)
		setContentView(binding.root)

		initRecycler()
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
			add(ContactData(R.drawable.profile1, "김민준", "010-2512-9631", ViewType.view_type1))
			add(ContactData(R.drawable.profile2, "배해남", "010-4323-4134", ViewType.view_type1))
			add(ContactData(R.drawable.profile3, "장준재", "010-5455-6453", ViewType.view_type2))
			add(ContactData(R.drawable.profile4, "하인영", "010-6747-1116", ViewType.view_type1))
			add(ContactData(R.drawable.profile5, "김인철", "010-5788-3666", ViewType.view_type1))
			add(ContactData(R.drawable.profile6, "유창하", "010-9000-1668", ViewType.view_type2))
			add(ContactData(R.drawable.profile7, "한진기", "010-4356-6575", ViewType.view_type2))
			add(ContactData(R.drawable.profile8, "설재희", "010-4777-2199", ViewType.view_type1))
			add(ContactData(R.drawable.profile9, "배주영", "010-6578-3547", ViewType.view_type2))
			add(ContactData(R.drawable.profile10, "정석환", "010-5678-4385", ViewType.view_type1))
		}
		contactAdapter.setData(list = data)
	}
}
package com.kotdev99.android.contactlist

class ContactList {
	fun addContact() {
		val contactList = ArrayList<ContactData>()

		contactList.add(ContactData(R.drawable.profile1, "김민준", "010-2512-9631", true))
		contactList.add(ContactData(R.drawable.profile2, "배해남", "010-4323-4134"))
		contactList.add(ContactData(R.drawable.profile3, "장준재", "010-5455-6453"))
		contactList.add(ContactData(R.drawable.profile4, "하인영", "010-6747-1116", true))
		contactList.add(ContactData(R.drawable.profile5, "김인철", "010-5788-3666"))
		contactList.add(ContactData(R.drawable.profile6, "유창하", "010-9000-1668", true))
		contactList.add(ContactData(R.drawable.profile7, "한진기", "010-4356-6575"))
		contactList.add(ContactData(R.drawable.profile8, "설재희", "010-4777-2199"))
		contactList.add(ContactData(R.drawable.profile9, "배주영", "010-6578-3547"))
		contactList.add(ContactData(R.drawable.profile10, "정석환", "010-5678-4385", true))
	}
}
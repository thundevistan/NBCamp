package com.example.contract.sampledata

import android.util.Log

object ContactManager {
	private val contactList = ArrayList<ContactItem>()

	fun addContact(contact: ContactItem) {
		contactList.add(contact)
	}

	fun getContact(): ArrayList<ContactItem> {
		return contactList
	}

	// Logcat 으로 연락처 확인
	fun printContact() {
		for ((index, contact) in contactList.withIndex()) {
			Log.d(
				"ContactInfo",
				"Index: $index, " +
						"profileImage: ${contact.profileImage}, " +
						"listName: ${contact.listName}, " +
						"groupName: ${contact.groupName}, " +
						"isFavorite: ${contact.isFavorite}, " +
						"phoneNumber: ${contact.phoneNumber} " +
						"email: ${contact.email}, " +
						"event: ${contact.event}"
			)
		}
	}
}
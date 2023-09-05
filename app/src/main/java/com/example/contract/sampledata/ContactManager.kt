package com.example.contract.sampledata

import android.util.Log
import com.example.contract.R

object ContactManager {
    private val contactList = ArrayList<ContactItem>()

    init {
        addContact(ContactItem(R.drawable.img_profile, "daeulzzang", "Home", true))
        addContact(ContactItem(R.drawable.img_profile, "daeulzzang", "Home", false))
        addContact(ContactItem(R.drawable.img_profile, "daeulzzang", "Home", false))
        addContact(ContactItem(R.drawable.img_profile, "daeulzzang", "Home", true))
        addContact(ContactItem(R.drawable.img_profile, "daeulzzang", "Home", true))
        addContact(ContactItem(R.drawable.img_profile, "daeulzzang", "Home", false))
        addContact(ContactItem(R.drawable.img_profile, "daeulzzang", "Home", false))
        addContact(ContactItem(R.drawable.img_profile, "daeulzzang", "Home", true))
        addContact(ContactItem(R.drawable.img_profile, "daeulzzang", "Home", false))
        addContact(ContactItem(R.drawable.img_profile, "daeulzzang", "Home", true))
    }

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
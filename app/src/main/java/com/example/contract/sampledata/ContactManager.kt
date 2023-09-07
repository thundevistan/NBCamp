package com.example.contract.sampledata

import android.util.Log
import com.example.contract.R

object ContactManager {
    private val contactList = ArrayList<ContactItem>()

    init {
        addContact(ContactItem(R.drawable.img_profile, "daeulzzang1", "Home", false, "010-1111-8598", "ball1241@naver.com", "10분후에 알림"))
        addContact(ContactItem(R.drawable.img_profile, "daeulzzang2", "Home", false, "010-2222-8598", "ball1241@naver.com", "10분후에 알림"))
        addContact(ContactItem(R.drawable.img_profile, "daeulzzang3", "Home", false, "010-3333-8598", "ball1241@naver.com", "10분후에 알림"))
        addContact(ContactItem(R.drawable.img_profile, "daeulzzang4", "Home", false, "010-4444-8598", "ball1241@naver.com", "10분후에 알림"))
        addContact(ContactItem(R.drawable.img_profile, "daeulzzang5", "Home", false, "010-5555-8598", "ball1241@naver.com", "10분후에 알림"))
        addContact(ContactItem(R.drawable.img_profile, "daeulzzang6", "Home", false, "010-6666-8598", "ball1241@naver.com", "10분후에 알림"))
        addContact(ContactItem(R.drawable.img_profile, "daeulzzang7", "Home", false, "010-7777-8598", "ball1241@naver.com", "10분후에 알림"))
        addContact(ContactItem(R.drawable.img_profile, "daeulzzang8", "Home", false, "010-8888-8598", "ball1241@naver.com", "10분후에 알림"))
        addContact(ContactItem(R.drawable.img_profile, "daeulzzang9", "Home", false, "010-9999-8598", "ball1241@naver.com", "10분후에 알림"))
        addContact(ContactItem(R.drawable.img_profile, "daeulzzang10", "Home", false, "010-0000-8598", "ball1241@naver.com", "10분후에 알림"))
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
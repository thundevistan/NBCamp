package com.example.contract.sampledata

import android.net.Uri
import android.util.Log

object ContactManager {
    private val contactList = ArrayList<ContactItem>()
    private val drawableResName = "img_profile" // Drawable 리소스 이름 (파일명과 동일)
    private val pakageName = "com.example.contract"
    private val uri = Uri.parse("android.resource://$pakageName/drawable/$drawableResName")

    init {
        addContact(
            ContactItem(
                uri,
                "daeulzzang1",
                "Home",
                false,
                "010-1111-8598",
                "ball1241@naver.com",
                "10분후에 알림"
            )
        )
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
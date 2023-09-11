package com.example.contract.sampledata

import android.net.Uri

object ContactManager {
    private val contactList = ArrayList<ContactItem>()
    private var index = 0
    private const val drawableResName = "img_profile" // Drawable 리소스 이름 (파일명과 동일)
    private const val pakageName = "com.example.contract"
    private val uri = Uri.parse("android.resource://$pakageName/drawable/$drawableResName")

    init {
        addContact(
            ContactItem(
                index++,
                -1,
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
}
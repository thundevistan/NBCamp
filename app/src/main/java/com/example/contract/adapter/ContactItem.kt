package com.example.contract.adapter

data class ContactItem(
    val profileImage: Int,
    val listName: String,
    val groupName: String,
    var isFavorite: Boolean,
    val phoneNumber: Int = 0,
    val email: String = "",
    val event: String = "",
) {
    companion object {
        const val VIEW_TYPE_LEFT = 0
        const val VIEW_TYPE_RIGHT = 1
    }
}

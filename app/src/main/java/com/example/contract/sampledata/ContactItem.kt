package com.example.contract.sampledata

data class ContactItem(
	val profileImage: Int,
	val listName: String,
	val groupName: String? = "HOME",
	var isFavorite: Boolean,
	val phoneNumber: String? = null,
	val email: String = "",
	val event: String? = null
) {
	companion object {
		const val VIEW_TYPE_LEFT = 0
		const val VIEW_TYPE_RIGHT = 1
	}
}

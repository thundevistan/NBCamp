package com.example.contract.sampledata

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize


@Parcelize
data class ContactItem(
	val profileImage: Int,
	val listName: String,
	val groupName: String? = "HOME",
	var isFavorite: Boolean,
	val phoneNumber: String? = "",
	val email: String = "",
	val event: String? = ""
): Parcelable {
	companion object {
		const val VIEW_TYPE_LEFT = 0
		const val VIEW_TYPE_RIGHT = 1
		const val VIEW_TYPE_GRID = 2
	}
}

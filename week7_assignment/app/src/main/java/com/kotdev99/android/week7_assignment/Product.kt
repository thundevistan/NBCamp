package com.kotdev99.android.week7_assignment

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import java.text.DecimalFormat

@Parcelize
data class Product(
	val no: Int,
	val thumb: Int,
	val name: String,
	val description: String,
	val seller: String,
	val price: Int,
	val addr: String,
	var fav: Int,
	val chat: Int,
	var isFav: Boolean = false
) : Parcelable {
	// Dec 처리
	fun dec(): String {
		val dec = DecimalFormat("#,###")

		return "${dec.format(this.price)}원"
	}
}
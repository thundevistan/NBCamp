package com.kotdev99.android.week7_assignment

import java.text.DecimalFormat

data class Product(
	val no: Int,
	val thumb: Int,
	val name: String,
	val description: String,
	val seller: String,
	val price: Int,
	val addr: String,
	val fav: Int,
	val chat: Int
) {
	fun dec(): String {
		val dec = DecimalFormat("#,###")

		return "${dec.format(this.price)}원"
	}
}
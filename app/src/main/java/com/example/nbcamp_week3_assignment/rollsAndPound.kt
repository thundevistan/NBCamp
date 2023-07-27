package com.example.nbcamp_week3_assignment

class rollsAndPound: Menu() {
	override fun add(orderList: MutableSet<String>) {
		var num = 1
		for (menu in rollsAndPoundArray) {
			println("[${num}] ${menu[0]} | ${menu[1]}원")
			num ++
		}

		var choice = readln()!!.toInt()
		orderList.add(rollsAndPoundArray[choice - 1][0].toString())
	}
}
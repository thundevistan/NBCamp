package com.example.nbcamp_week3_assignment

class Snacks() : Menu() {
    override fun add(orderList: MutableSet<String>) {
        println("[ Snacks MENU ]")
        println("1. Snacks 1 | 간단한 설명")
        println("2. Snacks 2 | 간단한 설명")
        println("3. Snacks 3 | 간단한 설명")
        println("4. Snacks 4 | 간단한 설명")

        var choice = readln()!!.toInt()
        orderList.add(snacksArray[choice-1][0].toString())
    }
}

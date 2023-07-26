package com.example.nbcamp_week3_assignment

class Bread() : Menu() {
    override fun add(orderList: MutableSet<String>) {
        println("[ Bread MENU ]")
        println("1. 소보루빵 | 간단한 설명")
        println("2. 단팥빵 | 간단한 설명")
        println("3. 마늘빵 | 간단한 설명")
        println("4. 맘모스빵 | 간단한 설명")

        var choice = readln()!!.toInt()
        orderList.add(breadArray[choice - 1][0].toString())

    }
}
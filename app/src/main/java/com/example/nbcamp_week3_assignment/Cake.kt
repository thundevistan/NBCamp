package com.example.nbcamp_week3_assignment

class Cake() : Menu() {
    override fun add(orderList: MutableSet<String>) {
        println("[ Cake MENU ]")
        println("1. Cake 1 | 간단한 설명")
        println("2. Cake 2 | 간단한 설명")
        println("3. Cake 3 | 간단한 설명")
        println("4. Cake 4 | 간단한 설명")

        var choice = readln()!!.toInt()
        orderList.add(cakeArray[choice - 1][0].toString())
    }
}
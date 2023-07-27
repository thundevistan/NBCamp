package com.example.nbcamp_week3_assignment

class Snacks() : Menu() {
    override fun add(orderList: MutableSet<String>) {
        var num = 1
        for (menu in snacksArray) {
            println("[${num}] ${menu[0]} | ${menu[1]}원")
            num ++
        }

        var choice = readln()!!.toInt()
        orderList.add(snacksArray[choice - 1][0].toString())
    }
}

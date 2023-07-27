package com.example.nbcamp_week3_assignment

class Bread() : Menu() {
    override fun add(orderList: MutableSet<String>) {
        var num = 1
        for (menu in breadArray) {
            println("[${num}] ${menu[0]} | ${menu[1]}원")
            num ++
        }

        var choice = readln()!!.toInt()
        orderList.add(breadArray[choice - 1][0].toString())

    }
}
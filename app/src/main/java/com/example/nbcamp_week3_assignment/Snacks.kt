package com.example.nbcamp_week3_assignment

class Snacks() : Menu() {
    override fun add(orderList: MutableList<String>) {
        var num = 1
        for (menu in snacksArray) {
            println("[${num}] ${menu[1].padEnd(6)} ₩ | ${menu[0]}원")
            num++
        }

        var choice = readln()!!.toInt()
        orderList.add(snacksArray[choice - 1][0].toString())
    }
}
//

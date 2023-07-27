package com.example.nbcamp_week3_assignment

class Cake() : Menu() {
    override fun add(orderList: MutableSet<String>) {
        var num = 1
        for (menu in cakeArray) {
            println("[${num}] ${menu[1].padEnd(6)} ₩ | ${menu[0]}원")
            num++
        }

        var choice = readln()!!.toInt()
        orderList.add(cakeArray[choice - 1][0].toString())
    }
}
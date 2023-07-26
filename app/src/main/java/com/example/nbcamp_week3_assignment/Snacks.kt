package com.example.nbcamp_week3_assignment

class Snacks : Menu() {
    override fun add(orderList: MutableSet<String>) {
        println("[ Snacks MENU ]")
        println("1. 대전부르스약과 | 간단한 설명")
        println("2. 쑥떡앙빵 | 간단한 설명")
        println("3. 파래미니전병 | 간단한 설명")
        println("4. 땅콩미니전병 | 간단한 설명")
        println("5. 대전부르스만주 1호 | 간단한 설명")

        var choice = readLine()?.toIntOrNull()

        when (choice) {
            1 -> orderList.add("대전부르스약과 | 1200")
            2 -> orderList.add("쑥떡앙빵 | 1800")
            3 -> orderList.add("파래미니전병 | 3500")
            4 -> orderList.add("땅콩미니전병 | 1000")
            5 -> orderList.add("대전부르스만주 1호 | 17000")
            else -> println("잘못된 선택입니다.")
        }
    }
}
package com.example.nbcamp_week3_assignment

class Cake : Menu() {
    override fun add(orderList: MutableSet<String>) {
        println("[ Cake MENU ]")
        println("1. 축복 4호 | 간단한 설명")
        println("2. 키리쉬모짜르트 | 간단한 설명")
        println("3. 티라미수 1호 | 간단한 설명")
        println("4. 딸기요거트 | 간단한 설명")
        println("5. 클래식치즈케이크 | 간단한 설명")

        var choice = readLine()?.toIntOrNull()

        when (choice) {
            1 -> orderList.add("축복 4호 | 41000")
            2 -> orderList.add("키리쉬모짜르트 | 35000")
            3 -> orderList.add("티라미수 1호 | 30000")
            4 -> orderList.add("딸기요거트 | 30000")
            5 -> orderList.add("클래식치즈케이크 | 30000")
            else -> println("잘못된 선택입니다.")
        }
    }
}

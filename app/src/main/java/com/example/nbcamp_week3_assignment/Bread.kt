package com.example.nbcamp_week3_assignment
class Bread : Menu() {
    override fun add(orderList: MutableSet<String>) {
        println("[ Bread MENU ]")
        println("1. 튀소패밀리 | 간단한 설명")
        println("2. 파이만주세트 | 간단한 설명")
        println("3. 오키도키슈 | 간단한 설명")
        println("4. 오키도키슈세트 | 간단한 설명")
        println("5. 김과장세트 | 간단한 설명")

        var choice = readLine()?.toIntOrNull()

        when (choice) {
            1 -> orderList.add("튀소패밀리 | 10800")
            2 -> orderList.add("파이만주세트 | 9600")
            3 -> orderList.add("오키도키슈 | 3500")
            4 -> orderList.add("오키도키슈세트 | 14000")
            5 -> orderList.add("김과장세트 | 40000")
            else -> println("잘못된 선택입니다.")
        }
    }
}
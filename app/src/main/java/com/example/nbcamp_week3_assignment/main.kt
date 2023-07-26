package com.example.nbcamp_week3_assignment

import java.text.DecimalFormat

fun main() {
    val orderList: MutableSet<String> = mutableSetOf()
    val bread = Bread()
    val snacks = Snacks()
    val cake = Cake()
    val order = Order() // Order 클래스 인스턴스 생성
    var currentBalance = 50000 // 임의로 5만원으로 설정
    var n3: Int = 0
    println("성심당에 오신것을 환영합니다.")
    Datetime().inspection() // 은행결제시간과 현재 시간을 받아오는 코드입니다 나중에 옮겨서 사용하시면 됩니다:)

    while (true) {
        while (true) {
            println("주문하시겠습니까? [yes : 1] [no : 2] ")
            val n = readLine()?.toIntOrNull()

            if (n == 1) {
                println("아래 메뉴판을 보시고 메뉴를 골라 입력해주세요.\n")
                println("[ 성심당 MENU ]")
                println("1. Bread | 간단한 설명")
                println("2. Snacks | 간단한 설명")
                println("3. Cake | 간단한 설명")
                println("4. Roll & Pound | 간단한 설명")
                break
            } else if (n == 2) {
                println("키오스크를 종료합니다.")
                return
            } else {
                println("1 또는 2의 숫자만 입력할 수 있습니다")
            }
        }

        val n2 = readLine()?.toIntOrNull()
        when (n2) {
            1 -> bread.add(orderList)
            2 -> snacks.add(orderList)
            3 -> cake.add(orderList)
            4 -> println("롤 & 파운드")
            else -> println("예외처리")
        }
        println("장바구니에 추가되었습니다!")

        while (true) {
            println("[더 주문하기 : 1] [결제 : 2]")
            val n3 = readLine()?.toIntOrNull()

            when (n3) {
                1 -> break
                2 -> {
                    order.displayOrder(orderList, currentBalance) // 주문 내역과 결제 기능 호출
                    break
                }
                else -> println("1 또는 2의 숫자만 입력할 수 있습니다")
            }
        }
        if (n3 == 2) {
            break
        }
    }

    // println(orderList) //*출력대신 Order class 호출 예정* -> 호출 시 orderList를 함께 넘겨주기
}

package com.example.nbcamp_week3_assignment

import Datetime
import kotlin.concurrent.thread
import kotlin.system.exitProcess


fun main() {
    val orderList: MutableList<String> = mutableListOf()
    val bread = Bread()
    val snacks = Snacks()
    val cake = Cake()
    val rollsAndPound = RollsAndPound()
    var menu = Menu()
    var n3: Int = 0
    var order = Order()
    var account = Balance().rand(20, 30)
    println("성심당에 오신것을 환영합니다.")
    println("현재 잔액은 ${account}원 입니다.")

    Datetime().inspection() //은행결제시간과 현재 시간을 받아오는 코드입니다 나중에 옮겨서 사용하시면 됩니다:)

    while (true) {
        try {
            println("주문하시겠습니까? [yes : 1] [no : 2] ")
            var n = readln()!!.toInt()
            when (n) {
                1 -> {
                    break
                }

                2 -> {
                    println("키오스크를 종료합니다.")
                    exitProcess(0)
                }

                else -> {
                    println("1 또는 2의 숫자만 입력할 수 있습니다")
                    continue
                }
            }
        } catch (e: java.lang.NumberFormatException) {
            println("잘못 입력하셨습니다. 숫자를 입력해 주세요")
        }
    }

    while (true) {
        while (true) {
            try {
                println("아래 메뉴판을 보시고 메뉴를 골라 입력해주세요.\n")
                println("[ 성심당 MENU ]")
                println("1. Bread")
                println("2. Snacks")
                println("3. Cake")
                println("4. Roll & Pound")

                var n2 = readln()!!.toInt()
                when (n2) {
                    1 -> {
                        bread.add(orderList)
                        break
                    }
                    2 -> {
                        snacks.add(orderList)
                        break
                    }
                    3 -> {
                        cake.add(orderList)
                        break
                    }
                    4 -> {
                        rollsAndPound.add(orderList)
                        break
                    }
                    else -> println("해당 번호의 품목이 존재하지 않습니다. 다시 입력해 주세요.")
                }
            } catch (e: java.lang.NumberFormatException) {
                println("잘못 입력하셨습니다. 숫자를 입력해 주세요")
            }
        }
        println("장바구니에 추가되었습니다!")
        while (true) {
            try {
                println("[더 주문하기 : 1] [결제 : 2]")
                n3 = readln()!!.toInt()
                when (n3) {
                    1 -> {
                        break
                        continue
                    }

                    2 -> {
                        order.displayOrder(orderList, account) // 주문 내역과 결제 기능 호출
                        break
                    }

                    else -> {
                        println("1 또는 2의 숫자만 입력할 수 있습니다")
                        continue
                    }
                }
            } catch (e: java.lang.NumberFormatException) {
                println("잘못 입력하셨습니다. 숫자를 입력해 주세요")
            }

        }
        when (n3) {
            2 -> break
        }
    }
}
//
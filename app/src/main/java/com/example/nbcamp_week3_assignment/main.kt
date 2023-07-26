package com.example.nbcamp_week3_assignment

import kotlin.concurrent.thread

fun main() {
    val orderList: MutableSet<String> = mutableSetOf()
    val bread = Bread()
    val snacks = Snacks()
    val cake = Cake()
    var menu = Menu()

    var n3: Int = 0
    var account = Balance().rand(20,30)

    println("성심당에 오신것을 환영합니다.")
    println("현재 잔액은 ${account}만원 입니다.")

    Datetime().inspection() //은행결제시간과 현재 시간을 받아오는 코드입니다 나중에 옮겨서 사용하시면 됩니다:)

    while (true)
    {
        while (true) {
            println("주문하시겠습니까? [yes : 1] [no : 2] ")
            var n = readln()!!.toInt()
            when (n) {
                1 -> {
                    println("아래 메뉴판을 보시고 메뉴를 골라 입력해주세요.\n")
                    println("[ 성심당 MENU ]")
                    println("1. Bread | 간단한 설명")
                    println("2. Snacks | 간단한 설명")
                    println("3. Cake | 간단한 설명")
                    break
                }
                2 -> {
                    println("키오스크를 종료합니다.")
                    return
                }
                else -> println("범위 밖의 값을 입력했습니다. 다시 입력해주세요")
            }
        }
        var n2 = readln()!!.toInt()
        var choice : String
        when(n2)
        {
            1 -> bread.add(orderList)
            2 -> snacks.add(orderList)
            3 -> cake.add(orderList)
            else -> println("예외처리")
        }
        println("장바구니에 추가되었습니다!")
        println("[더 주문하기 : 1] [결제 : 2]")
        var n3 = readln()!!.toInt()
        when(n3)
        {
            1 -> continue
            2 -> break
            else -> println("예외처리")
        }
    }
    thread(start = true) {
        WaitCount().waitCount()
    }

    println(orderList) //*출력대신 Order class 호출 예정* -> 호출 시 orderList를 함께 넘겨주기



}








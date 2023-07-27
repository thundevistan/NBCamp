package com.example.nbcamp_week3_assignment

import java.text.DecimalFormat

open class Menu {
    val dec = DecimalFormat("#,###")

    val breadArray = arrayOf(
        arrayOf("튀소패밀리", dec.format(10800)),
        arrayOf("파이만주세트", dec.format(9600)),
        arrayOf("오키도키슈", dec.format(3500)),
        arrayOf("오키도키슈세트", dec.format(14000)),
        arrayOf("김과장세트", dec.format(40000))
    )

    val snacksArray = arrayOf(
        arrayOf("대전부르스약과", dec.format(1200)),
        arrayOf("쑥떡앙빵", dec.format(1800)),
        arrayOf("파래미니전병", dec.format(3500)),
        arrayOf("땅콩미니전병", dec.format(1000)),
        arrayOf("대전부르스만주 1호", dec.format(17000))
    )

    val cakeArray = arrayOf(
        arrayOf("축복 4호", dec.format(41000)),
        arrayOf("키리쉬모짜르트", dec.format(35000)),
        arrayOf("티라미수 1호", dec.format(30000)),
        arrayOf("딸기요거트", dec.format(30000)),
        arrayOf("클래식치즈케이크", dec.format(30000))
    )

    val rollsAndPoundArray = arrayOf(
        arrayOf("순수마들렌 1호", dec.format(5000)),
        arrayOf("제로쿠키세트", dec.format(25000)),
        arrayOf("대흥동 종소리", dec.format(30000)),
        arrayOf("성심갸또컬렉션 2호", dec.format(39000)),
        arrayOf("마들파운드 2호", dec.format(30000))
    )

    open fun add(orderList: MutableList<String>) {
        // 주문
    }
}
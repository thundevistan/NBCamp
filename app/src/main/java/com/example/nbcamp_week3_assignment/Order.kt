package com.example.nbcamp_week3_assignment
import java.text.DecimalFormat

class Order : Menu() {
    fun showOrderDetails(orderList: MutableSet<String>, currentBalance: Int) {
        println("[ ORDER MENU ]")
        println("5. Order      | 장바구니를 확인 후 주문합니다.")
        println("5 <-")

        // 주문 목록이 비어있을 경우 메시지 출력 후 함수 종료
        if (orderList.isEmpty()) {
            println("\n주문 목록이 비어있습니다. 메뉴판에서 메뉴를 선택해주세요.")
            return
        }

        println("\n아래와 같이 주문 하시겠습니까?\n")
        println("[ Orders ]")
        var totalAmount = 0.0

        for (item in orderList) {
            // 쉼표를 제거하여 숫자만으로 이루어진 문자열로 변환
            val price = item.substringAfterLast("|").replace(",", "").trim()
            val menuInfo = getMenuInfo(item)

            if (menuInfo != null && menuInfo.size == 2) {
                println("${menuInfo[0]} | ${dec.format(price.toDouble())}")
                totalAmount += price.toDouble()
            } else {
                println("주문 목록에 존재하지 않는 메뉴가 있습니다: $item")
            }
        }

        println("\n[ Total ]")
        println("W ${dec.format(totalAmount)}")

        println("\n1. 주문 ")
        var n = readLine()?.toIntOrNull() ?: 0

        when (n) {
            1 -> {
                if (totalAmount <= currentBalance) {
                    println("주문이 완료되었습니다. 총 ${dec.format(totalAmount)}W 결제되었습니다.")
                    orderList.clear()
                } else {
                    println("현재 잔액은 ${dec.format(currentBalance)}W 으로 ${dec.format(totalAmount - currentBalance)}W이 부족해서 주문할 수 없습니다.")
                }
            }
        }
    }

    private fun getMenuInfo(item: String): Array<String>? {
        val menuArrays = arrayOf(breadArray, snacksArray, cakeArray, rollsAndPoundArray)

        for (menuArray in menuArrays) {
            for (menu in menuArray) {
                if (item.contains(menu[0])) {
                    return menu
                }
            }
        }

        return null
    }
}

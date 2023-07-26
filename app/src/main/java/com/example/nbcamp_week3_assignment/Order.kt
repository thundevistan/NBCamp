package com.example.nbcamp_week3_assignment
import Datetime
import java.text.DecimalFormat

// "Order" 클래스는 "Menu" 클래스를 상속
class Order : Menu() {
    // 주문 내역을 출력하고 주문 처리를 수행
    fun displayOrder(orderList: MutableSet<String>, currentBalance: Int) {
//        println("[ ORDER MENU ]")
//        println("5. Order      | 장바구니를 확인 후 주문합니다.")
//        지우기

        // 주문 목록이 비어있을 경우 메시지 출력 후 함수 종료
        if (orderList.isEmpty()) {
            println("\n주문 목록이 비어있습니다. 메뉴판에서 메뉴를 선택해주세요.")
            return
        }

        // 주문 내역을 출력
        println("\n아래와 같이 주문 하시겠습니까?\n")
        println("[ Orders ]")
        var totalCount = 0.0 // 초기값으로 0.0을 설정

        for (item in orderList) {
            val menuList = getMenuList(item)
            // 주문 항목이 메뉴 정보를 포함하고 있는지를 확인하기 위해 사용
            if (menuList != null && menuList.size == 2) {
                println("${menuList[0]} | ${dec.format(menuList[1].toDouble())}")
                //  해당 메뉴의 가격 정보를 totalCount 변수에 더해주기
                totalCount += menuList[1].toDouble()
            } else {
                println("주문 목록에 존재하지 않는 메뉴가 있습니다: $item")
            }
        }

        println("\n[ Total ]")
        println("W ${dec.format(totalCount)}")

        println("\n결제하시겠습니까? [yes : 1] [no : 2] ")
        var n = readLine()?.toIntOrNull() ?: 0

        when (n) {
            1 -> {
                if (totalCount <= currentBalance) {
                    Datetime().inspection() // 현재 시각을 받아옴
                    //println("현재 시각은 $currentTime 입니다.") // 현재 시각 출력
                    println("결제가 완료되었습니다. 총 ${dec.format(totalCount)}W 결제되었습니다.")
                    orderList.clear()
                } else {
                    // 결제 금액이 현재 잔고보다 큰 경우 주문을 처리X
                    println("현재 잔액은 ${dec.format(currentBalance)}W 으로 ${dec.format(totalCount - currentBalance)}W이 부족해서 주문할 수 없습니다.")
                }
            }
            2 -> {
                println("키오스크를 종료합니다.")
            }
            else -> {
                println("1 또는 2의 숫자만 입력할 수 있습니다")
            }
        }
    }
    private fun getMenuList(item: String): Array<String>? {
        // 주문 항목 문자열을 받아와서 해당 메뉴에 대한 정보를 가져오기
        // menuArrays는 breadArray, snacksArray, cakeArray, rollsAndPoundArray 배열들을 포함
        val menuArrays = arrayOf(breadArray, snacksArray, cakeArray, rollsAndPoundArray)

        // menuArrays의 각 배열들을 순회하기
        // 첫 번째 루프에서 menuArray는 breadArray를, 두 번째 루프에서는 snacksArray를 이런 식으로
        for (menuArray in menuArrays) {
            for (menu in menuArray) {
                // 주문 항목 문자열에 해당 메뉴가 있는지를 검사
                if (item.contains(menu[0])) {
                    // 쉼표를 제거하여 가격을 숫자 형태로 변환
                    val price = menu[1].replace(",", "").toDouble()
                    // 메뉴 이름과 가격 정보를 문자열 배열로 묶어 반환
                    return arrayOf(menu[0], price.toString())
                }
            }
        }

        return null
    }
}

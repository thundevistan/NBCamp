package com.example.nbcamp_week3_assignment

class RollsAndPound : Menu() {
    override fun add(orderList: MutableList<String>) {
        var num = 1
        for (menu in rollsAndPoundArray) {
            println("[${num}] ${menu[1].padEnd(6)} ₩ | ${menu[0]}")
            num++
        }
        while(true){
            val length = rollsAndPoundArray.size
            try{
                var choice = readln()!!.toInt()
                if(0 < choice && choice <= length) {
                    orderList.add(rollsAndPoundArray[choice - 1][0].toString())
                    break;
                } else {
                    println("1부터 $length 까지 입력 가능합니다.")
                    continue;
                }
            } catch (e:java.lang.NumberFormatException) {
                println("숫자만 입력 가능합니다.")
            }
        }

// var choice = readln()!!.toInt()
// orderList.add(rollsAndPoundArray[choice - 1][0].toString())
    }
}
//

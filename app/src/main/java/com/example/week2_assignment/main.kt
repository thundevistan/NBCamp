package com.example.week2_assignment

fun main() {
    println("첫 번째 숫자를 입력하세요")
    val num1: Int = readLine()!!.toInt()

    println("연산자를 선택해 주세요\n [1]: +  [2]: -  [3]: *  [4]: /")
    val oper = readLine()!!.toInt()

    println("두 번째 숫자를 입력하세요")
    val num2: Int = readLine()!!.toInt()

    when(oper) {
        1 -> {
            val calc = Calculator(AddOperation()).operate(num1, num2)
            println("${num1} 더하기 ${num2} 결과는 : ${calc} 입니다")
        }

        2-> {
            val calc = Calculator(SubtractOperation()).operate(num1, num2)
            println("${num1} 빼기 ${num2} 결과는 : ${calc} 입니다")
        }

        3 -> {
            val calc = Calculator(MultiplyOperation()).operate(num1, num2)
            println("${num1} 곱하기 ${num2} 결과는 : ${calc} 입니다")
        }

        4 -> {
            val calc = Calculator(DivideOperation()).operate(num1, num2)
            println("${num1} 나누기 ${num2} 결과는 : ${calc} 입니다")
        }

        else -> {
            println("잘못된 연산자 입니다!")
        }
    }
}
package com.example.week2_assignment

class DivideOperation : AbstractOperation() {
    override fun operate(num1: Int, num2: Int): Double = (num1 / num2).toDouble()
}
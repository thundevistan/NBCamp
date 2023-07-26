package com.example.nbcamp_week3_assignment

import java.util.Random


class Balance {
    val random = Random()
    fun rand(from: Int, to: Int): Int {
        return random.nextInt(to - from) + from
    }

}
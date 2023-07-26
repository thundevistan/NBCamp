package com.example.nbcamp_week3_assignment

import java.util.Timer
import java.util.TimerTask
import kotlin.random.Random

class WaitCount {
	fun waitCount() {
		// 1부터 20까지 무작위 대기자
		var waitCount = Random.nextInt(1, 21)

		// 타이머 객체 선언
		val timer = Timer()

		// 타이머 동작 시간 지정 및 작업 내용 지정
		timer.schedule(object : TimerTask() {
			override fun run() {
				// 최초 대기자 수 출력
				println("이와 같이 주문 하시겠습니까? (현재 주문 대기수: ${waitCount})")

				// 1부터 3까지 무작위 픽업
				val pickupCount = Random.nextInt(1, 4)

				// 대기자 수 감소
				if (waitCount > pickupCount) {
					waitCount -= pickupCount
				} else {
					waitCount--
				}

				// 대기자가 0명이 되면 타이머 종료
				if (waitCount <= 0) {
					println("[현재 주문 대기자가 없습니다!]")
					timer.cancel()
				}
			}
		}, 0, 5000)   // 즉시 실행, 5초 마다 반복
	}
}
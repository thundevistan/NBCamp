import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class Datetime {
    val currentTime = LocalDateTime.now() //현재시간 받아오기
    val formatter = DateTimeFormatter.ofPattern("현재 시각은 HH시:mm분 입니다.") //출력형식 지정
    val formattedTime = currentTime.format(formatter) //현재시간 출력형식에 맞춰 저장

    val inspectionTime = inspectionFun(currentTime)// 점검시간 확인해주는 함수

    fun inspection() {
        if (inspectionTime) {
            println(formattedTime)
            println("은행 점검시간인 22시 00분 ~ 22시 30분이므로 결제가 불가능합니다.")
        } else {
            println(formattedTime)
            println("결제가 가능합니다!")
        }
    }
}

fun inspectionFun(currentTime: LocalDateTime): Boolean {
    //점검시간 구간 설정 22:00~22:30분!!
    val startTime = currentTime.withHour(22).withMinute(0).withSecond(0) // 22:00
    val EndTime = currentTime.withHour(22).withMinute(30).withSecond(0) // 22:30

    return currentTime.isAfter(startTime) && currentTime.isBefore(EndTime)// return값 설정
    //만약 새벽으로 시간이 넘어가서 EndTime이 StartTime보다 빠르게 되면 [&&] -> [||]로 수정하시면됩니당
}
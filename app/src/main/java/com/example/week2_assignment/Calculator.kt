package com.example.week2_assignment

class Calculator(val operation: AbstractOperation) {
    fun operate(num1: Int, num2: Int): Double {
        return operation.operate(num1, num2)
    }
}



/*
lass Calculator(operation: AddOperation) {
    fun operate(num1: Int, num2: Int): Double {
        return operate()
    }
}

! return 코드 작성에 이슈 발생 !
[AddOperation 클래스의 operate 메소드에 접근이 불가능]

return operation.operate() 입력 안됨;;
Calculator의 파라미터에 AddOperation를 받아옴
그런데 operation.operation() 접근 불가능
본인 함수 operate()만 뜸


! 해결 !
lass Calculator(val operation: AddOperation) {
    fun operate(num1: Int, num2: Int): Double {
        return operation.operate(num1, num2)
    }
}

매개변수를 val/var로 선언해주니 해결됐다...? (why??)

----------------------------------------------------
[팀 PR 리뷰]

주찬영님 리뷰
매개변수는 단순히 해당 값을 '받겠다' 라는 의미이기 때문에,
해당 매개변수의 초기화를 val/var로 보장함 (... 것이라고 생각함)
 */
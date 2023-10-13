package bootcamp.sparta.disneym

import android.app.Application

/*
* 작성자: 서정한
* ViewModel에서 context를 사용하기위한 Application
* */
class DisneyMinusApp : Application(){
    companion object {
        // 메인 메모리에만 저장되며, 멀티 쓰레드 환경에서 메인 메모리의 값을 참조하므로 변수 값 불일치 문제를 해결함
        @Volatile
        private lateinit var app: DisneyMinusApp

        @JvmStatic
        fun getApp() : DisneyMinusApp = app
    }

    override fun onCreate() {
        app = this
        super.onCreate()
    }
}
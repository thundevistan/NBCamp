package bootcamp.sparta.disneym.viewmodel.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import java.lang.IllegalArgumentException
/*
 * Copyright 2023 추민수, Inc.
 * ViewModel을 생성하는데 도움을 주는 클래스
 * ViewModel 인스턴스를 생성할 때 특정한 설정 및 초기화 수행
 * 의존성 주입을 가능하게 함수점을 갖는다.
 */
class DetailViewModelFactory : ViewModelProvider.Factory{

    override fun <T: ViewModel> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(DetailViewModel::class.java)){ // 요청된 모델 클래스 ViewModel 클래스와 호환 되는지 확인
            return DetailViewModel() as T // 호환될 경우 생성된 ViewModel T타입 으로 형변환 하여 반환
        }
        throw IllegalArgumentException("Not Found ViewModel class.") // 호환 되지 않는 경우 알림
    }

}
package bootcamp.sparta.disneym.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import bootcamp.sparta.disneym.model.DetailModel
import bootcamp.sparta.disneym.model.TestModel
import bootcamp.sparta.disneym.model.toDetailModel

/*
 * Copyright 2023 추민수, Inc.
 * Fragment간 공유되는 데이터를 관리
 * 사용할 Fragment에서 by ActivityViewModels() 를 통해 선언
 * 필요한 LiveData 추가 예정
 * Model을 업데이트 해줄 때 매핑을 통해 그에 맞는 값으로 변환 후 바인딩 해주는 코드 미리 추가해놓은 상태
 * 추후 각 Fragment에 맞는 Model들이 생성되면 추가적인 코드 작성 예정
 */
class MainSharedViewModel : ViewModel() {

    private val _detailEvent: MutableLiveData<MainSharedEventForTest> = MutableLiveData()
    val detailEvent: LiveData<MainSharedEventForTest> get() = _detailEvent //읽기 전용

    /*
     * 추민수
     * ex) testModel의 형식을 갖고있는 Fragement에서 updateDetailItem()을 호출
     * testMode -> DetailModel로 매핑 후 데이터에 바인딩함
     */
    fun updateDetailItem(item: TestModel) {
        _detailEvent.value = MainSharedEventForTest.UpdateTodoItem(item.toDetailModel())
    }
}

sealed interface MainSharedEventForTest {
    data class UpdateTodoItem(
        val item: DetailModel
    ) : MainSharedEventForTest
}

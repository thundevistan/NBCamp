package bootcamp.sparta.disneym.ui.viewmodel.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import bootcamp.sparta.disneym.model.DetailModel
/*
 * 추민수
 * DetialPage 데이터 관리를 위한 ViewModel
 * 비즈니스 로직 캡슐화
 * UI에 사용될 상태를 처리, UI Layer에 상채를 알림
 * 주요 이점은 상태를 저장하여 구성이 변경되어도 이를 유지하는 것 ( 화면 회전 시에도 UI가 데이터를 다시 가져오지 않아도 됨 )
 */
class DetailViewModel : ViewModel(){
    private val _detailItem : MutableLiveData<DetailModel> = MutableLiveData()
    val detailItem : LiveData<DetailModel> get() = _detailItem // 읽기 전용

    fun addDetailItem(item: DetailModel) {
        _detailItem.value = item
    }

    fun getItemBookmarked() : Boolean {
        return if(detailItem.value == null) {
            false
        } else {
            detailItem.value!!.isBookmarked
        }
    }
    fun updateDetailItem(item : DetailModel){
        _detailItem.value = item
    }

    fun isBookmarkedItem(item: DetailModel){
        _detailItem.value = item
    }

}
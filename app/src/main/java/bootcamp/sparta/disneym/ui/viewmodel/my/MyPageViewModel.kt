package bootcamp.sparta.disneym.ui.viewmodel.my

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import bootcamp.sparta.disneym.R
/*
 * 추민수
 * MyPage 데이터 관리를 위한 ViewModel
 * 비즈니스 로직 캡슐화
 * UI에 사용될 상태를 처리, UI Layer에 상채를 알림
 * 주요 이점은 상태를 저장하여 구성이 변경되어도 이를 유지하는 것 ( 화면 회전 시에도 UI가 데이터를 다시 가져오지 않아도 됨 )
 */
class MyPageViewModel : ViewModel() {

    private val _userInfo : MutableLiveData<EventForUserInfo>  = MutableLiveData()
    val userInfo : LiveData<EventForUserInfo> get() = _userInfo

    init {
        _userInfo.value = EventForUserInfo.UpdateUserProfile(R.drawable.profile2)
        _userInfo.value = EventForUserInfo.UpdateUserId("disneym@gmail.com")
        _userInfo.value = EventForUserInfo.UpdateUserPw("123456789")
    }

    fun updateUserId(id : String){
        _userInfo.value = EventForUserInfo.UpdateUserId(id)
    }

    fun updateUserPw(pw : String){
        _userInfo.value = EventForUserInfo.UpdateUserPw(pw)
    }

    fun updateUserProfile(profile: Int) {
        _userInfo.value = EventForUserInfo.UpdateUserProfile(profile)
    }
}

sealed interface EventForUserInfo {
    data class UpdateUserProfile(
        val image : Int
    ) : EventForUserInfo
    data class UpdateUserId(
        val text: String
    ) : EventForUserInfo

    data class UpdateUserPw(
        val text: String
    ) : EventForUserInfo
}
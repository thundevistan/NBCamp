package bootcamp.sparta.disneym.ui.mypage

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import bootcamp.sparta.disneym.R

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
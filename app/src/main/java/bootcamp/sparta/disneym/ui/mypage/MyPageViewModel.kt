package bootcamp.sparta.disneym.ui.mypage

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import bootcamp.sparta.disneym.model.BookmarkModel
import org.w3c.dom.Text

class MyPageViewModel : ViewModel() {

    private val _userInfo : MutableLiveData<EventForUserInfo>  = MutableLiveData()
    val userInfo : LiveData<EventForUserInfo> get() = _userInfo


    fun updateUserId(id : String){
        _userInfo.value = EventForUserInfo.UpdateUserId(id)
    }

    fun updateUserPw(pw : String){
        _userInfo.value = EventForUserInfo.UpdateUserPw(pw)
    }
}

sealed interface EventForUserInfo {
    data class UpdateUserId(
        val text: String
    ) : EventForUserInfo

    data class UpdateUserPw(
        val text: String
    ) : EventForUserInfo
}
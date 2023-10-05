package bootcamp.sparta.disneym.ui.viewmodel.my

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import bootcamp.sparta.disneym.domain.usecase.mypage.LoadUserDataUseCase
import bootcamp.sparta.disneym.domain.usecase.mypage.SaveUserDataUseCase
import bootcamp.sparta.disneym.domain.usecase.mypage.UpdateUserIdUseCase
import bootcamp.sparta.disneym.domain.usecase.mypage.UpdateUserProfileUseCase
import bootcamp.sparta.disneym.domain.usecase.mypage.UpdateUserPwUseCase
import bootcamp.sparta.disneym.ui.mypage.UserModel

/*
 * 추민수
 * MyPage 데이터 관리를 위한 ViewModel
 * 비즈니스 로직 캡슐화
 * UI에 사용될 상태를 처리, UI Layer에 상채를 알림
 * 주요 이점은 상태를 저장하여 구성이 변경되어도 이를 유지하는 것 ( 화면 회전 시에도 UI가 데이터를 다시 가져오지 않아도 됨 )
 */
class MyPageViewModel(
    private val loadUser : LoadUserDataUseCase,
    private val saveUser : SaveUserDataUseCase,
    private val updateId : UpdateUserIdUseCase,
    private val updatePw : UpdateUserPwUseCase,
    private val updateProfile : UpdateUserProfileUseCase

) : ViewModel() {

    private val _userInfo: MutableLiveData<UserModel> = MutableLiveData()
    val userInfo: LiveData<UserModel> get() = _userInfo

    fun updateUserId(id: String) {
        _userInfo.value = updateId(id)
    }

    fun updateUserPw(pw: String) {
        _userInfo.value = updatePw(pw)
    }

    fun updateUserProfile(profile: Int) {
        _userInfo.value = updateProfile(profile)
    }

    fun loadUserData(context: Context) {
        _userInfo.value = loadUser(context)
    }

    fun saveUserData(context: Context, userModel: UserModel) {
       saveUser(context,userModel)
    }
}
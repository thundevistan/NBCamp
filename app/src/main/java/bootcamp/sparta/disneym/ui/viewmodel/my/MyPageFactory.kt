package bootcamp.sparta.disneym.ui.viewmodel.my

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import bootcamp.sparta.disneym.domain.repository.MyPageRepositoryImpl
import bootcamp.sparta.disneym.domain.usecase.mypage.LoadUserDataUseCase
import bootcamp.sparta.disneym.domain.usecase.mypage.SaveUserDataUseCase
import bootcamp.sparta.disneym.domain.usecase.mypage.UpdateUserIdUseCase
import bootcamp.sparta.disneym.domain.usecase.mypage.UpdateUserProfileUseCase
import bootcamp.sparta.disneym.domain.usecase.mypage.UpdateUserPwUseCase
import bootcamp.sparta.disneym.ui.mypage.UserModel

class MyPageFactory : ViewModelProvider.Factory {

    private val repository = MyPageRepositoryImpl()

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MyPageViewModel::class.java)) {
            return MyPageViewModel(
                LoadUserDataUseCase(repository),
                SaveUserDataUseCase(repository),
                UpdateUserIdUseCase(repository),
                UpdateUserPwUseCase(repository),
                UpdateUserProfileUseCase(repository)
            ) as T
        } else {
            throw IllegalArgumentException("Not found ViewModel class.")
        }
    }
}
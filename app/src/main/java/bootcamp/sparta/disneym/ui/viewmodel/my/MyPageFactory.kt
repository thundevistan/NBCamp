package bootcamp.sparta.disneym.ui.viewmodel.my

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import bootcamp.sparta.disneym.ui.mypage.UserModel

class MyPageFactory : ViewModelProvider.Factory {

    private val repository = MyPageRepositoryImpl()

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(UserModel::class.java)) {
            return MyPageViewModel(repository) as T
        } else {
            throw IllegalArgumentException("Not found ViewModel class.")
        }
    }
}
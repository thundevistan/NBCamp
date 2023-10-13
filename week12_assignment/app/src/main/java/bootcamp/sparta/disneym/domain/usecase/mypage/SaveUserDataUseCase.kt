package bootcamp.sparta.disneym.domain.usecase.mypage

import android.content.Context
import bootcamp.sparta.disneym.data.repository.MyPageRepositoryImpl
import bootcamp.sparta.disneym.ui.mypage.UserModel

class SaveUserDataUseCase(private val repository: MyPageRepositoryImpl) {

    operator fun invoke(context: Context, userModel: UserModel) = repository.saveUserData(context,userModel)

}
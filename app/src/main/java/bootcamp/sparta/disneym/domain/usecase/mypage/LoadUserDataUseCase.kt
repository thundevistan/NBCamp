package bootcamp.sparta.disneym.domain.usecase.mypage

import android.content.Context
import bootcamp.sparta.disneym.domain.repository.MyPageRepository

class LoadUserDataUseCase (private val repository: MyPageRepository){

    operator fun invoke(context: Context) = repository.loadUserData(context)

}
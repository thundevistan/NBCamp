package bootcamp.sparta.disneym.domain.usecase.mypage

import android.content.Context
import bootcamp.sparta.disneym.data.repository.MyPageRepositoryImpl
import bootcamp.sparta.disneym.domain.repository.HomeRepository

class LoadUserDataUseCase(private val repository: MyPageRepositoryImpl){

    operator fun invoke(context: Context) = repository.loadUserData(context)

}
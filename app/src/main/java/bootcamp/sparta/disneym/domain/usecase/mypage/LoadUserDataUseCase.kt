package bootcamp.sparta.disneym.domain.usecase.mypage

import android.content.Context
import bootcamp.sparta.disneym.domain.repository.HomeRepository

class LoadUserDataUseCase (private val repository: HomeRepository){

    operator fun invoke(context: Context) = repository.loadUserData(context)

}
package bootcamp.sparta.disneym.domain.usecase.mypage

import bootcamp.sparta.disneym.data.repository.MyPageRepositoryImpl
import bootcamp.sparta.disneym.domain.repository.HomeRepository

class UpdateUserPwUseCase(private val repository: MyPageRepositoryImpl) {

    operator fun invoke(pw: String) = repository.updateUserPw(pw)

}
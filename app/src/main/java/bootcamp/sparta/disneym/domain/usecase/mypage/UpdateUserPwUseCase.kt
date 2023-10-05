package bootcamp.sparta.disneym.domain.usecase.mypage

import bootcamp.sparta.disneym.domain.repository.MyPageRepository

class UpdateUserPwUseCase(private val repository: MyPageRepository) {

    operator fun invoke(pw: String) = repository.updateUserPw(pw)

}
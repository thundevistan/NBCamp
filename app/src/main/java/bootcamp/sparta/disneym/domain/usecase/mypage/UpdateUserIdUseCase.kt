package bootcamp.sparta.disneym.domain.usecase.mypage

import bootcamp.sparta.disneym.domain.repository.MyPageRepository

class UpdateUserIdUseCase(private val repository: MyPageRepository) {

    operator fun invoke(id : String) = repository.updateUserId(id)

}
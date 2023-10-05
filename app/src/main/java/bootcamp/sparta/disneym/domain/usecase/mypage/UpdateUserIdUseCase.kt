package bootcamp.sparta.disneym.domain.usecase.mypage

import bootcamp.sparta.disneym.domain.repository.HomeRepository

class UpdateUserIdUseCase(private val repository: HomeRepository) {

    operator fun invoke(id : String) = repository.updateUserId(id)

}
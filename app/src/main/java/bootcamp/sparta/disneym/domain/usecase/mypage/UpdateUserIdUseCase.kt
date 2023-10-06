package bootcamp.sparta.disneym.domain.usecase.mypage

import bootcamp.sparta.disneym.data.repository.MyPageRepositoryImpl

class UpdateUserIdUseCase(private val repository: MyPageRepositoryImpl) {

    operator fun invoke(id : String) = repository.updateUserId(id)

}
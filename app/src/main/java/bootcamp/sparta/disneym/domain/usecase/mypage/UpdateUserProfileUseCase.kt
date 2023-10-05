package bootcamp.sparta.disneym.domain.usecase.mypage

import bootcamp.sparta.disneym.domain.repository.MyPageRepository

class UpdateUserProfileUseCase(private val repository: MyPageRepository) {

    operator fun invoke(profile: Int) = repository.updateUserProfile(profile)

}
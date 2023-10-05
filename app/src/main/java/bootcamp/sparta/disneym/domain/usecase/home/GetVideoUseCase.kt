package bootcamp.sparta.disneym.domain.usecase.home

import bootcamp.sparta.disneym.BuildConfig
import bootcamp.sparta.disneym.domain.repository.MainRepository
import bootcamp.sparta.disneym.domain.repository.SearchRepositoryImpl

class GetVideoUseCase(private val repository: MainRepository) {

	suspend operator fun invoke(
		part: String = "snippet",
		chart: String = "mostPopular",
		key: String = BuildConfig.YOUTUBE_API_KEY,
		maxResults: Int = 50,
		videoCategoryId: Int,
		regionCode: String = "KR"
	) =
		repository.getVideos(
			part,
			chart,
			key,
			maxResults,
			videoCategoryId,
			regionCode
		)
}
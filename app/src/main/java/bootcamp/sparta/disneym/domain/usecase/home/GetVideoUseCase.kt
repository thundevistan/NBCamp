package bootcamp.sparta.disneym.domain.usecase.home

import bootcamp.sparta.disneym.data.datasource.remote.MainRepository

class GetVideoUseCase(private val repository: MainRepository) {

	suspend operator fun invoke(
		part: String,
		chart: String,
		key: String,
		maxResults: Int,
		videoCategoryId: Int,
		regionCode: String
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
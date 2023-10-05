package bootcamp.sparta.disneym.ui.search

import bootcamp.sparta.disneym.BuildConfig
import bootcamp.sparta.disneym.data.datasource.remote.Videos
import retrofit2.Response

class VideoUseCase(private val repository: SearchRepository) {

    suspend operator fun invoke(
        part: String = "snippet",
        chart: String = "mostPopular",
        key: String = BuildConfig.YOUTUBE_API_KEY,
        maxResults: Int = 50,
        videoCategoryId: Int,
        regionCode: String = "KR"
    ) : Response<Videos> = repository.getVideos(
        part,
        chart,
        key,
        maxResults,
        videoCategoryId,
        regionCode
    )


}
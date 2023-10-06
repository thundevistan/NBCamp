package bootcamp.sparta.disneym.data.repository

import bootcamp.sparta.disneym.data.model.Channels
import bootcamp.sparta.disneym.data.model.VideoCategories
import bootcamp.sparta.disneym.data.model.Videos
import bootcamp.sparta.disneym.data.datasource.remote.YoutubeRetrofit
import bootcamp.sparta.disneym.domain.repository.MainRepository
import retrofit2.Response

class HomeRepositoryImpl: MainRepository {

	override suspend fun getVideos(part: String, chart: String, key: String, maxResults: Int, videoCategoryId: Int, regionCode: String): Response<Videos> {
		return YoutubeRetrofit.youtubeApi.getPopularVideos(part = part, chart = chart, key = key, maxResults = maxResults, videoCategoryId= videoCategoryId, regionCode = regionCode)
	}
	override suspend fun getCategories(part: String, regionCode: String, key: String): Response<VideoCategories> {
return YoutubeRetrofit.youtubeApi.getVideoCategory(part = part, regionCode = regionCode, key = key)
	}
	override suspend fun getChannels(part: String, id: String, key: String): Response<Channels> {
		return YoutubeRetrofit.youtubeApi.getYoutubeChanel(part = part, id = id, key = key)
	}

}
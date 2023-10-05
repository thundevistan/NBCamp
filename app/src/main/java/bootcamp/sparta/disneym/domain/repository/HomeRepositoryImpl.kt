package bootcamp.sparta.disneym.domain.repository

import bootcamp.sparta.disneym.data.datasource.remote.Channels
import bootcamp.sparta.disneym.data.datasource.remote.MainRepository
import bootcamp.sparta.disneym.data.datasource.remote.Search
import bootcamp.sparta.disneym.data.datasource.remote.VideoCategories
import bootcamp.sparta.disneym.data.datasource.remote.Videos
import bootcamp.sparta.disneym.data.datasource.remote.YoutubeRetrofit
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
	override suspend fun getSearch(part: String, q: String, maxResults: Int, key: String): Response<Search> {
		return YoutubeRetrofit.youtubeApi.getSearch(part = part, q = q, maxResults = maxResults, key = key)
	}
}
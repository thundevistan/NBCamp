package bootcamp.sparta.disneym.data.datasource.remote

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query


/*
* Copyright 2023 김현준, Inc.
*
* getPopularVideos : 인기 있는 동영상을 가져오는 메서드
* getVideoCategory : 카테고리 목록을 가져오는 메서드
* getYoutubeChannel : 채널의 정보를 가져오는 메서드
* getSearch : 검색 메서드
*
* */
interface YoutubeApi {

	@GET("youtube/v3/videos")
	suspend fun getPopularVideos(
		@Query("key") key: String = API_KEY.AUTH_KEY,
		@Query("part") part: String,
		@Query("chart") chart: String,
		@Query("videoCategoryId") categoryId: Int? = null
	): Response<VideoModel>

	@GET("youtube/v3/videoCategories")
	suspend fun getVideoCategory(
		@Query("key") key: String = API_KEY.AUTH_KEY,
		@Query("part") part: String,
		@Query("regionCode") regionCode: String
	): Response<CategoryModel>

	@GET("youtube/v3/channels")
	suspend fun getYoutubeChanel(
		@Query("key") key: String = API_KEY.AUTH_KEY,
		@Query("part") part: String,
		@Query("id") id: String
	): Response<ChannelModel>

	@GET("youtube/v3/search")
	suspend fun getSearch(
		@Query("key") key: String = API_KEY.AUTH_KEY,
		@Query("q") q: String,
		@Query("part") part: String,
		@Query("maxResults") maxResults: Int
	): Response<SearchModel>
}
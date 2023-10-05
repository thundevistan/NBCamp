package bootcamp.sparta.disneym.data.datasource.remote

import retrofit2.Response

/*
* Copyright 2023 김현준, Inc.
* 각 메서드는 인기 동영상, 카테고리 목록, 채널 정보를 가져오고
* YoutubeRetrofit 객체로 초기화한 API에서 각각의 목록과 정보를 요청하고 반환한다 */

interface MainRepository {
    suspend fun getVideos(part: String, chart: String, key: String, maxResults: Int, videoCategoryId: Int, regionCode: String) : Response<Videos>

    suspend fun getCategories(part: String, regionCode: String, key: String) : Response<VideoCategories>

    suspend fun getChannels(part: String, id: String, key: String) : Response<Channels>

    suspend fun getSearch(part: String, q: String, maxResults: Int, key: String) : Response<Search>
}
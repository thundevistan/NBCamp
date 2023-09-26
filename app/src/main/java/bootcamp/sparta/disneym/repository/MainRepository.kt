package bootcamp.sparta.disneym.repository

import bootcamp.sparta.disneym.data.datasource.remote.CategoryModel
import bootcamp.sparta.disneym.data.datasource.remote.ChannelModel
import bootcamp.sparta.disneym.data.datasource.remote.SearchModel
import bootcamp.sparta.disneym.data.datasource.remote.VideoModel
import bootcamp.sparta.disneym.data.datasource.remote.YoutubeRetrofit
import retrofit2.Response

/*
* Copyright 2023 김현준, Inc.
* 각 메서드는 인기 동영상, 카테고리 목록, 채널 정보를 가져오고
* YoutubeRetrofit 객체로 초기화한 API에서 각각의 목록과 정보를 요청하고 반환한다 */

class MainRepository {

    suspend fun getVideos(part: String, chart: String, key: String) : Response<VideoModel> {
        return YoutubeRetrofit.youtubeApi.getPopularVideos(part = part, chart = chart, key = key)
    }

    suspend fun getCategories(part: String, regionCode: String, key: String) : Response<CategoryModel> {
        return YoutubeRetrofit.youtubeApi.getVideoCategory(part = part, regionCode = regionCode, key = key)
    }

    suspend fun getChannels(part: String, id: String, key: String) : Response<ChannelModel> {
        return YoutubeRetrofit.youtubeApi.getYoutubeChanel(part = part, id = id, key = key)
    }

    suspend fun getSearch(part: String, q: String, maxResults: Int, key: String) : Response<SearchModel>{
        return YoutubeRetrofit.youtubeApi.getSearch(part = part, q = q, maxResults = 50, key = key)
    }
}
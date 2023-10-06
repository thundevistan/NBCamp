package bootcamp.sparta.disneym.data.repository

import bootcamp.sparta.disneym.data.model.Search
import bootcamp.sparta.disneym.data.datasource.remote.YoutubeRetrofit
import bootcamp.sparta.disneym.domain.repository.SearchRepository
import retrofit2.Response

/*
* Copyright 2023 김현준, Inc.
* 각 메서드는 인기 동영상, 카테고리 목록, 채널 정보를 가져오고
* YoutubeRetrofit 객체로 초기화한 API에서 각각의 목록과 정보를 요청하고 반환한다 */

class SearchRepositoryImpl: SearchRepository {
    override suspend fun getSearch(part: String, q: String, maxResults: Int, key: String) : Response<Search>{
        return YoutubeRetrofit.youtubeApi.getSearch(part = part, q = q, maxResults = maxResults, key = key)
    }
}


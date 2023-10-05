package bootcamp.sparta.disneym.domain.repository

import bootcamp.sparta.disneym.data.datasource.remote.Search
import bootcamp.sparta.disneym.data.datasource.remote.Videos
import retrofit2.Response

interface SearchRepository {

    suspend fun getSearch(part: String, q: String, maxResults: Int, key: String) : Response<Search>

}
package bootcamp.sparta.disneym.ui.search

import bootcamp.sparta.disneym.data.datasource.remote.Search
import bootcamp.sparta.disneym.data.datasource.remote.Videos
import retrofit2.Response

interface SearchRepository {

    suspend fun getVideos(part: String, chart: String, key: String, maxResults: Int, videoCategoryId: Int, regionCode: String) : Response<Videos>

    suspend fun getSearch(part: String, q: String, maxResults: Int, key: String) : Response<Search>

}
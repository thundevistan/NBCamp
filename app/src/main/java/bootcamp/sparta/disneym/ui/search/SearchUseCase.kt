package bootcamp.sparta.disneym.ui.search

import bootcamp.sparta.disneym.BuildConfig
import bootcamp.sparta.disneym.data.datasource.remote.Search
import retrofit2.Response

class SearchUseCase (private val repository: SearchRepository)

 {
    suspend operator fun invoke (
        part : String = "snippet",
        q : String,
        maxResults: Int = 50,
        key: String = BuildConfig.YOUTUBE_API_KEY
    ) : Response<Search> = repository.getSearch(
        part,
        q,
        maxResults,
        key
    )
}
package bootcamp.sparta.disneym.ui.viewmodel.search

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import bootcamp.sparta.disneym.model.SearchModel
import bootcamp.sparta.disneym.ui.search.SearchRepository
import bootcamp.sparta.disneym.ui.search.SearchUseCase
import bootcamp.sparta.disneym.ui.search.VideoUseCase
import kotlinx.coroutines.launch

class SearchViewModel(private val searchUseCase: SearchUseCase, private val videoUseCase: VideoUseCase): ViewModel() {

    private val _searchItem: MutableLiveData<List<SearchModel>> = MutableLiveData()
    val searchItem: LiveData<List<SearchModel>> = _searchItem

    private val _getVideo: MutableLiveData<List<SearchModel>> = MutableLiveData()
    val getVideo : LiveData<List<SearchModel>> = _getVideo

    fun getSearchItems(q: String) {
        viewModelScope.launch {
            try {
                val response = searchUseCase(q= q)
                if(response.isSuccessful) {
                    val searchResults = response.body()
                    Log.d("Search", "response : ${response.body()}")
                    if(searchResults != null) {
                        val searchItems: List<SearchModel> = searchResults.items.map { item ->
                            SearchModel(
                                id = item.id.videoId,
                                title = item.snippet.title,
                                imgUrl = item.snippet.thumbnails.high.url,
                                description = item.snippet.description,
                                datetime = item.snippet.publishedAt,
                                channelTitle = item.snippet.channelTitle,
                                isBookmarked = false
                            )
                        }
                        _searchItem.value = searchItems
                    } else {
                        Log.d("Search", "검색 결과 null")
                    }
                } else {
                    Log.d("Search", "요청 실패 : ${response.code()} - ${response.message()}")
                }
            } catch (e: Exception) {
                Log.d("Search", "호출 실패 : ${e.message}")
            }

        }
    }

    fun getVideoItems(videoCategoryId: Int) {
        viewModelScope.launch {
            try {
                val response = videoUseCase(videoCategoryId = videoCategoryId)
                if (response.isSuccessful) {
                    val videoResult = response.body()
                    if (videoResult != null) {
                        var videoItems: List<SearchModel> = videoResult.items.map { item ->
                            SearchModel(
                                id = item.id,
                                title = item.snippet.title,
                                imgUrl = item.snippet.thumbnails.high.url,
                                description = item.snippet.description,
                                datetime = item.snippet.publishedAt,
                                channelTitle = item.snippet.channelTitle,
                                isBookmarked = false
                            )
                        }
                        _getVideo.value = videoItems
                    } else {
                        Log.d("SearchVideo", "동영상 null")
                    }
                } else {
                    Log.d("SearchVideo", "요청 실패 : ${response.code()} - ${response.message()}")
                }
            } catch (e:Exception) {
                Log.d("SearchVideo", "호출 실패 : ${e.message}")

            }
        }
    }
}




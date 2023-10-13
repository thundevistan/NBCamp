package bootcamp.sparta.disneym.ui.viewmodel.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import bootcamp.sparta.disneym.data.repository.HomeRepositoryImpl
import bootcamp.sparta.disneym.data.repository.SearchRepositoryImpl
import bootcamp.sparta.disneym.domain.usecase.home.GetVideoUseCase
import bootcamp.sparta.disneym.domain.usecase.search.SearchUseCase

class SearchViewModelFactory : ViewModelProvider.Factory {

    private val searchRepository = SearchRepositoryImpl()
    private val videoRepository = HomeRepositoryImpl()
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(SearchViewModel::class.java)) {
            return SearchViewModel(searchUseCase = SearchUseCase(searchRepository),
                videoUseCase = GetVideoUseCase(videoRepository)
            ) as T
        }
        throw IllegalArgumentException("ViewModel을 찾을 수 없습니다.")
    }
}

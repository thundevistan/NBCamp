package bootcamp.sparta.disneym.ui.viewmodel.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import bootcamp.sparta.disneym.repository.SearchRepositoryImpl
import bootcamp.sparta.disneym.ui.search.SearchRepository
import bootcamp.sparta.disneym.ui.search.SearchUseCase
import bootcamp.sparta.disneym.ui.search.VideoUseCase

class SearchViewModelFactory : ViewModelProvider.Factory {

    private val repository = SearchRepositoryImpl()
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(SearchViewModel::class.java)) {
            return SearchViewModel(searchUseCase = SearchUseCase(repository),
                videoUseCase = VideoUseCase(repository)) as T
        }
        throw IllegalArgumentException("ViewModel을 찾을 수 없습니다.")
    }
}

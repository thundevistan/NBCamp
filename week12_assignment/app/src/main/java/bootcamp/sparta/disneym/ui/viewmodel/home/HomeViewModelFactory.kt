package bootcamp.sparta.disneym.ui.viewmodel.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import bootcamp.sparta.disneym.data.repository.HomeRepositoryImpl
import bootcamp.sparta.disneym.domain.usecase.home.GetVideoUseCase

class HomeViewModelFactory(): ViewModelProvider.Factory {
    private val repository = HomeRepositoryImpl()

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(HomeViewModel::class.java)){
            return HomeViewModel(GetVideoUseCase(repository)) as T
        }
        throw IllegalArgumentException("is Not Valid ViewModel class")
    }
}
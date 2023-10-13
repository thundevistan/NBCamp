package bootcamp.sparta.disneym.ui.viewmodel.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import bootcamp.sparta.disneym.BuildConfig
import bootcamp.sparta.disneym.domain.usecase.home.GetVideoUseCase
import bootcamp.sparta.disneym.domain.model.HomeModel
import bootcamp.sparta.disneym.ui.home.Category
import kotlinx.coroutines.launch

const val PART = "snippet"
const val CHART = "mostPopular"
const val MAXRESULT = 60
const val REGIONCODE = "KR"

fun getIntForChannelCategory(category: Category): Int {
	return when (category) {
		Category.POPULAR -> 0
		Category.FILM -> 1
		Category.PETS -> 15
		Category.MUSIC -> 10
		Category.PEOPLE -> 22
		Category.GAMING -> 20
		Category.ENTERTAINMENT -> 24
	}
}

class HomeViewModel(private val getVideo: GetVideoUseCase) : ViewModel() {

	private var _list: MutableLiveData<List<HomeModel>> = MutableLiveData()
	val list get() = _list

	private val _popular: MutableLiveData<List<HomeModel>> = MutableLiveData()
	val popular get() = _popular

	fun getVideoForCategory(category: Category) {

		viewModelScope.launch {
			val response = getVideo(
				PART,
				CHART,
				BuildConfig.YOUTUBE_API_KEY,
				MAXRESULT,
				getIntForChannelCategory(category),
				REGIONCODE
			).body()

			val videoList: List<HomeModel> = response?.items?.map {
				HomeModel(
					it.id,
					it.snippet.title,
					it.snippet.description,
					it.snippet.thumbnails.high.url,
					it.snippet.publishedAt
				)
			}.orEmpty()

			if (category == Category.POPULAR) {
				_popular.value = videoList
			} else {
				_list.value = videoList
			}
		}
	}
}
package bootcamp.sparta.disneym.ui.viewmodel.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import bootcamp.sparta.disneym.data.datasource.remote.VideoModel
import bootcamp.sparta.disneym.model.HomeModel
import bootcamp.sparta.disneym.repository.MainRepository
import kotlinx.coroutines.launch

const val PART = "snippet"
const val CHART = "mostPopular""
const val FILM = 1
const val PETS = 15
const val MUSIC = 10
const val MOVIES = 30
const val GAMING = 20
const val EDUCATION = 27

class HomeViewModel : ViewModel() {
	private val repository: MainRepository

	private val _films = MutableLiveData<List<HomeModel>>()
	val films: LiveData<List<HomeModel>>
		get() = _films

	private val _pets = MutableLiveData<List<HomeModel>>()
	val pets: LiveData<List<HomeModel>>
		get() = _pets

	private val _music = MutableLiveData<List<HomeModel>>()
	val music: LiveData<List<HomeModel>>
		get() = _music

	private val _movies = MutableLiveData<List<HomeModel>>()
	val movies: LiveData<List<HomeModel>>
		get() = _movies

	private val _gaming = MutableLiveData<List<HomeModel>>()
	val gaming: LiveData<List<HomeModel>>
		get() = _gaming

	private val _education = MutableLiveData<List<HomeModel>>()
	val education: LiveData<List<HomeModel>>
		get() = _education

	init {
		repository = MainRepository()
		getFilm()
	}

	private fun getFilm() {

		viewModelScope.launch {
			val response: VideoModel =
				repository.getVideos(PART, CHART, FILM, "youtube_api_key").body()!!

			val filmList: List<HomeModel> = response.videos.items.map {
				HomeModel(
					it.id,
					it.snippet.title,
					it.snippet.description,
					it.snippet.thumbnails.high.url,
					it.snippet.publishedAt
				)
			}

			_films.value = filmList
		}
	}

	private fun getPets() {}

	private fun getMusic() {}

	private fun getMovies() {}

	private fun getGaming() {}

	private fun getEducation() {}
}
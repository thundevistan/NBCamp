package bootcamp.sparta.disneym.ui.viewmodel.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import bootcamp.sparta.disneym.BuildConfig
import bootcamp.sparta.disneym.data.datasource.remote.Videos
import bootcamp.sparta.disneym.model.HomeModel
import bootcamp.sparta.disneym.repository.MainRepository
import bootcamp.sparta.disneym.ui.home.Category
import kotlinx.coroutines.launch

const val PART = "snippet"
const val CHART = "mostPopular"
const val MAXRESULT = 51
const val REGIONCODE = "KR"
//const val POPULAR = 0
//const val FILM = 1
//const val PETS = 15
//const val MUSIC = 10
//const val PEOPLE = 22
//const val GAMING = 20
//const val ENTERTAINMENT = 24

fun getIntForChannelCategory(category : Category): Int {
    return when(category) {
        Category.POPULAR -> 0
        Category.FILM -> 1
        Category.PETS -> 15
        Category.MUSIC -> 10
        Category.PEOPLE -> 22
        Category.GAMING -> 20
        Category.ENTERTAINMENT -> 24
    }
}

class HomeViewModel(private val repository: MainRepository) : ViewModel() {

//    private val _popular = MutableLiveData<List<HomeModel>>()
//    val popular: LiveData<List<HomeModel>>
//        get() = _popular
//
//    private val _films = MutableLiveData<List<HomeModel>>()
//    val films: LiveData<List<HomeModel>>
//        get() = _films
//
//    private val _pets = MutableLiveData<List<HomeModel>>()
//    val pets: LiveData<List<HomeModel>>
//        get() = _pets
//
//    private val _music = MutableLiveData<List<HomeModel>>()
//    val music: LiveData<List<HomeModel>>
//        get() = _music
//
//    private val _people = MutableLiveData<List<HomeModel>>()
//    val people: LiveData<List<HomeModel>>
//        get() = _people
//
//    private val _gaming = MutableLiveData<List<HomeModel>>()
//    val gaming: LiveData<List<HomeModel>>
//        get() = _gaming
//
//    private val _entertainment = MutableLiveData<List<HomeModel>>()
//    val entertainment: LiveData<List<HomeModel>>
//        get() = _entertainment
    private var _list : MutableLiveData<List<HomeModel>> = MutableLiveData()
    val list get() = _list

    private val _popular : MutableLiveData<List<HomeModel>> = MutableLiveData()
    val popular get() = _popular

    fun getVideoForCategory(category: Category) {
        viewModelScope.launch {
            val response: Videos? =
                repository.getVideos(
                    PART,
                    CHART,
                    BuildConfig.YOUTUBE_API_KEY,
                    10,
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

            if(category == Category.POPULAR) {
                _popular.value = videoList
            } else {
                _list.value = videoList
            }
        }
    }

//
//    fun getPopular() {
//
//        viewModelScope.launch {
//            val response: Videos? =
//                repository.getVideos(
//                    PART,
//                    CHART,
//                    BuildConfig.YOUTUBE_API_KEY,
//                    10,
//                    getIntForChannelCategory(YTChannelCategory.POPULAR),
//                    REGIONCODE
//                ).body()
//
//            val popularList: List<HomeModel> = response?.items?.map {
//                HomeModel(
//                    it.id,
//                    it.snippet.title,
//                    it.snippet.description,
//                    it.snippet.thumbnails.high.url,
//                    it.snippet.publishedAt
//                )
//            }.orEmpty()
//
//            _popular.value = popularList
//        }
//    }
//
//    fun getFilm() {
//
//        viewModelScope.launch {
//            val response: Videos? =
//                repository.getVideos(
//                    PART,
//                    CHART,
//                    BuildConfig.YOUTUBE_API_KEY,
//                    MAXRESULT,
//                    FILM,
//                    REGIONCODE
//                ).body()!!
//
//            val filmList: List<HomeModel> = response?.items?.map {
//                HomeModel(
//                    it.id,
//                    it.snippet.title,
//                    it.snippet.description,
//                    it.snippet.thumbnails.high.url,
//                    it.snippet.publishedAt
//                )
//            }.orEmpty()
//
//            _films.value = filmList
//        }
//    }
//
//    fun getPets() {
//
//        viewModelScope.launch {
//            val response: Videos? =
//                repository.getVideos(
//                    PART,
//                    CHART,
//                    BuildConfig.YOUTUBE_API_KEY,
//                    MAXRESULT,
//                    PETS,
//                    REGIONCODE
//                ).body()
//
//            val petList: List<HomeModel> = response?.items?.map {
//                HomeModel(
//                    it.id,
//                    it.snippet.title,
//                    it.snippet.description,
//                    it.snippet.thumbnails.high.url,
//                    it.snippet.publishedAt
//                )
//            }.orEmpty()
//
//            _pets.value = petList
//        }
//    }
//
//    fun getMusic() {
//
//        viewModelScope.launch {
//            val response: Videos? =
//                repository.getVideos(
//                    PART,
//                    CHART,
//                    BuildConfig.YOUTUBE_API_KEY,
//                    MAXRESULT,
//                    MUSIC,
//                    REGIONCODE
//                ).body()
//
//            val musicList: List<HomeModel> = response?.items?.map {
//                HomeModel(
//                    it.id,
//                    it.snippet.title,
//                    it.snippet.description,
//                    it.snippet.thumbnails.high.url,
//                    it.snippet.publishedAt
//                )
//            }.orEmpty()
//
//            _music.value = musicList
//        }
//    }
//
//    fun getPeople() {
//        viewModelScope.launch {
//            val response: Videos? =
//                repository.getVideos(
//                    PART,
//                    CHART,
//                    BuildConfig.YOUTUBE_API_KEY,
//                    MAXRESULT,
//                    PEOPLE,
//                    REGIONCODE
//                ).body()!!
//
//            val peopleList: List<HomeModel> = response.items.map {
//                HomeModel(
//                    it.id,
//                    it.snippet.title,
//                    it.snippet.description,
//                    it.snippet.thumbnails.high.url,
//                    it.snippet.publishedAt
//                )
//            }
//
//            _people.value = peopleList
//        }
//    }
//
//    fun getGaming() {
//        viewModelScope.launch {
//            val response: Videos =
//                repository.getVideos(
//                    PART,
//                    CHART,
//                    BuildConfig.YOUTUBE_API_KEY,
//                    MAXRESULT,
//                    GAMING,
//                    REGIONCODE
//                ).body()!!
//
//            val gamingList: List<HomeModel> = response.items.map {
//                HomeModel(
//                    it.id,
//                    it.snippet.title,
//                    it.snippet.description,
//                    it.snippet.thumbnails.high.url,
//                    it.snippet.publishedAt
//                )
//            }
//
//            _gaming.value = gamingList
//        }
//    }
//
//    fun getEntertainment() {
//        viewModelScope.launch {
//            val response: Videos =
//                repository.getVideos(
//                    PART,
//                    CHART,
//                    BuildConfig.YOUTUBE_API_KEY,
//                    MAXRESULT,
//                    ENTERTAINMENT,
//                    REGIONCODE
//                ).body()!!
//
//            val entertainmentList: List<HomeModel> = response.items.map {
//                HomeModel(
//                    it.id,
//                    it.snippet.title,
//                    it.snippet.description,
//                    it.snippet.thumbnails.high.url,
//                    it.snippet.publishedAt
//                )
//            }
//
//            _entertainment.value = entertainmentList
//        }
//    }
}
package bootcamp.sparta.disneym.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import bootcamp.sparta.disneym.domain.model.BookmarkModel
import bootcamp.sparta.disneym.domain.model.DetailModel
import bootcamp.sparta.disneym.domain.model.HomeModel
import bootcamp.sparta.disneym.domain.model.SearchModel
import bootcamp.sparta.disneym.domain.model.TestModel1
import bootcamp.sparta.disneym.domain.model.toBookmarkModel
import bootcamp.sparta.disneym.domain.model.toDetailModel
import bootcamp.sparta.disneym.domain.model.toHomeModel

/*
 * 작성자 : 추민수
 * Fragment간 공유되는 데이터를 관리
 * 사용할 Fragment에서 by ActivityViewModels() 를 통해 선언
 */
class MainSharedViewModel : ViewModel() {

    private val _detailEvent: MutableLiveData<MainSharedEventForDetail> = MutableLiveData()
    val detailEvent: LiveData<MainSharedEventForDetail> get() = _detailEvent // 읽기 전용

    private val _bookmarkEvent: MutableLiveData<MainSharedEventForBookmark> = MutableLiveData()
    val bookmarkEvent: LiveData<MainSharedEventForBookmark> get() = _bookmarkEvent // 읽기 전용

    private val _homeEvent: MutableLiveData<MainSharedEventForHome> = MutableLiveData()
    val homeEvent: LiveData<MainSharedEventForHome> get() = _homeEvent // 읽기 전용

    /*
     * 추민수
     * 어떤 Model이 들어와도 DetailModel로 변환해주는 함수
     * 이해를 돕기위해 testModel을 만들었습니다 :)
     * testModel에 들어가서 보면 toDetailModel() 이라는 DetailModel로 매핑해주는 확장함수가 있습니다 :)
     */
    fun <T> updateDetailItem(updateModel: T) {
        val detailModel = when (updateModel) {
            is TestModel1 -> updateModel.toDetailModel()
            is SearchModel -> updateModel.toDetailModel()
            else -> return // 다른 값이 들어왔을 경우
        }
        _detailEvent.value = MainSharedEventForDetail.UpdateDetailItem(detailModel)
    }

    /*
     * 추민수
     * DetailModel -> BookmarkModel 매핑 하여 전달 하는 함수
     * bookmark 여부에 따라서 _bookmarkEvent 에서 추가 or 삭제
     */
    fun updateBookmarkItems(detailModel: DetailModel) {
        if (detailModel.isBookmarked) {
            _bookmarkEvent.value =
                MainSharedEventForBookmark.BookmarkItemForAdd(detailModel.toBookmarkModel())
        } else {
            _bookmarkEvent.value =
                MainSharedEventForBookmark.BookmarkItemForRemove(detailModel.toBookmarkModel())
        }
    }

    /*
     * 추민수
     * Detail, Bookmark에서 변경이 일어났을 때 Home에 있는 List로 데이터를 전달하는 함수
     */
    fun <T> updateHomeItems(updateModel: T?) {
        val homeModel = when (updateModel) {
            is DetailModel -> updateModel.toHomeModel()
            is BookmarkModel -> updateModel.toHomeModel()
            else -> return
        }
        _homeEvent.value = MainSharedEventForHome.UpdateHomeItem(homeModel)
    }
}


sealed interface MainSharedEventForDetail {
    data class UpdateDetailItem(
        val item: DetailModel
    ) : MainSharedEventForDetail
}

sealed interface MainSharedEventForBookmark {
    data class BookmarkItemForAdd(
        val item: BookmarkModel
    ) : MainSharedEventForBookmark

    data class BookmarkItemForRemove(
        val item: BookmarkModel
    ) : MainSharedEventForBookmark
}

sealed interface MainSharedEventForHome {
    data class UpdateHomeItem(
        val item: HomeModel
    ) : MainSharedEventForHome

}

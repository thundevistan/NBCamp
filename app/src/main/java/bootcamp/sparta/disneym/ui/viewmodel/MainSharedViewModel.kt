package bootcamp.sparta.disneym.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import bootcamp.sparta.disneym.model.BookmarkModel
import bootcamp.sparta.disneym.model.DetailModel
import bootcamp.sparta.disneym.model.TestModel1
import bootcamp.sparta.disneym.model.TestModel2
import bootcamp.sparta.disneym.model.toBookmarkModel
import bootcamp.sparta.disneym.model.toDetailModel

/*
 * 작성자 : 추민수
 * Fragment간 공유되는 데이터를 관리
 * 사용할 Fragment에서 by ActivityViewModels() 를 통해 선언
 */
class MainSharedViewModel : ViewModel() {

    private val _detailEvent: MutableLiveData<MainSharedEventForDetail> = MutableLiveData()
    val detailEvent: LiveData<MainSharedEventForDetail> get() = _detailEvent //읽기 전용

    private val _bookmarkEvent: MutableLiveData<List<MainSharedEventForBookmark>> = MutableLiveData()
    val bookmarkEvent: LiveData<List<MainSharedEventForBookmark>> get() = _bookmarkEvent //읽기 전용

    /*
     * 추민수
     * 어떤 Model이 들어와도 DetailModel로 변환해주는 코드
     * 이해를 돕기위해 testModel을 만들었습니다 :)
     * testModel에 들어가서 보면 toDetailModel() 이라는 DetailModel로 매핑해주는 확장함수가 있습니다 :)
     */
    fun updateDetailItem(testModel1: TestModel1?, testModel2: TestModel2?) {
        val detailModel = when {
            testModel1 != null -> testModel1.toDetailModel()
            testModel2 != null -> testModel2.toDetailModel()
            else -> return // 모든 변수가 null인 경우
        }
        _detailEvent.value = MainSharedEventForDetail.UpdateDetailItem(detailModel)
    }

    /*
     * 추민수
     * DetailModel -> BookmarkModel 매핑 하여 전달 하는 코드
     * bookmark 여부에 따라서 _bookmarkEvent 에서 추가 or 삭제
     * list의 형태로 구현 했기 때문에 바로 가져다 쓰시면 될 것 같습니다 :)
     */
    fun updateBookmarkItems(detailModel: DetailModel) {
        val currentList = bookmarkEvent.value.orEmpty().toMutableList()
        if (detailModel.isBookmarked) {
            currentList.add(MainSharedEventForBookmark.UpdateBookmarkItem(detailModel.toBookmarkModel()))
        }
        else{
            val findPosition = findIndex(currentList,detailModel.toBookmarkModel())
            currentList.removeAt(findPosition)
        }
        _bookmarkEvent.value = currentList
    }

    // index값을 찾아서 return
    private fun findIndex(currentList: MutableList<MainSharedEventForBookmark>, toBookmarkModel: BookmarkModel): Int {
        return currentList.indexOfFirst { item ->
            item is MainSharedEventForBookmark.UpdateBookmarkItem && item.item == toBookmarkModel
        }
    }
}


sealed interface MainSharedEventForDetail {
    data class UpdateDetailItem(
        val item: DetailModel
    ) : MainSharedEventForDetail
}

sealed interface MainSharedEventForBookmark {
    data class UpdateBookmarkItem(
        val item: BookmarkModel
    ) : MainSharedEventForBookmark
}

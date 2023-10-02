package bootcamp.sparta.disneym.ui.viewmodel.bookmark

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import bootcamp.sparta.disneym.DisneyMinusApp
import bootcamp.sparta.disneym.model.BookmarkModel
import bootcamp.sparta.disneym.ui.bookmark.BookmarkViewType
import bootcamp.sparta.disneym.ui.viewmodel.detail.DetailViewModel
import bootcamp.sparta.disneym.util.Util
import java.util.concurrent.atomic.AtomicLong

/*
* 작성자: 서정한
* 내용: Bookmark 데이터와 View의 업데이트를 위한 ViewModel
* */
class BookmarkViewModel : ViewModel() {
    private var _list: MutableLiveData<List<BookmarkModel>> = MutableLiveData()
    val list get() = _list

    init {
        _list.value = initData()
    }

    // 더미데이터 init
    private fun initData(): List<BookmarkModel> {
        val list: MutableList<BookmarkModel> = mutableListOf()
        val genId = AtomicLong(1L)
        for (i in 0 until 10) {
            list.add(
                BookmarkModel(
                    id = genId.getAndIncrement().toString(),
                    title = "영상제목${i}",
                    imgUrl = "https://picsum.photos/150/100",
                    description = "영상설명영상설명영상설명영상설명${i}",
                    datetime = "2323년 9월 2${i}일",
                    isBookmarked = true,
                )
            )
        }
        return list.toList()
    }

    // 북마크 타입 변경
    fun updateBookmarkListType(type: BookmarkViewType) {
        val current = list.value.orEmpty().toMutableList()
        for (i in current.indices) {
            current[i] = current[i].copy(viewType = type)
        }
        _list.value = current
    }

    // 북마크 아이템 추가
    fun addBookmarkItem(item: BookmarkModel) {
        val current = list.value.orEmpty().toMutableList()
        if(current.contains(item)) {
            return
        }
        current.add(item)
        _list.value = current
        Util.saveBookmarkItemForSharedPrefs(
            DisneyMinusApp.getApp().applicationContext,
            item
        )
    }

    // item remove
    fun removeSelectedBookmarkItem(item: BookmarkModel) {
        fun findIndex(item: BookmarkModel): Int? {
            val current = list.value.orEmpty().toMutableList()
            if (current.isEmpty()) {
                return null
            }

            val res = current.indexOf(item)
            if (res == -1) {
                return null
            }

            return res
        }

        val list = list.value.orEmpty().toMutableList()
        findIndex(item)?.let {
            list.removeAt(it)
        }
        _list.value = list
    }

    // 체크박스 업데이트
    fun updateItemChecked(position: Int, item: BookmarkModel) {
        val current = list.value.orEmpty().toMutableList()
        current[position] = item
        _list.value = current
    }
}

class BookmarkViewModelFactory : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(DetailViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return BookmarkViewModel() as T
        }
        throw IllegalArgumentException("Not Found ViewModel class.")
    }
}
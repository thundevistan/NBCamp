package bootcamp.sparta.disneym.domain.model

import android.os.Parcelable
import bootcamp.sparta.disneym.ui.bookmark.BookmarkViewType

/*
* 작성자: 서정한
* 내용: Bookmark에서 사용하는 Model
* */
@kotlinx.parcelize.Parcelize
data class BookmarkModel(
    val id: String?,
    val title: String,
    val imgUrl: String,
    val description: String,
    val datetime: String,
    val isBookmarked: Boolean,
    val viewType: Int = BookmarkViewType.Normal.INT, // RecyclerView Adapter의 ViewType 관리용
    val isChecked: Boolean = false, // RecyclerView Adapter의 체크박스 관리용
) : Parcelable
// 민수 : 모델 매핑 확장함수 Bookmark -> Detail
fun BookmarkModel.toDetailModel() = DetailModel(
    id = id,
    title = title,
    imgUrl = imgUrl,
    description = description,
    datetime = datetime,
    isBookmarked = isBookmarked,
)
// 민수 : 모델 매핑 확장함수 Bookmark -> Home
fun BookmarkModel.toHomeModel() = HomeModel(
    id = id,
    title = title,
    imgUrl = imgUrl,
    description = description,
    datetime = datetime,
    isBookmarked = isBookmarked,
)
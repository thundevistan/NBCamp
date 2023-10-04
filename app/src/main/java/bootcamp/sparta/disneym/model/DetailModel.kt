package bootcamp.sparta.disneym.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class DetailModel(
    val id: String? = null,
    val title: String,
    val imgUrl: String,
    val description: String,
    val datetime: String,
    val isBookmarked: Boolean,
) : Parcelable

// 민수 : 모델 매핑 확장함수 Detail -> Bookmark
fun DetailModel.toBookmarkModel() = BookmarkModel(
    id = id ?: "",
    title = title,
    imgUrl = imgUrl,
    description = description,
    datetime = datetime,
    isBookmarked = isBookmarked,
)
// 민수 : 모델 매핑 확장함수 Detail -> Home
fun DetailModel.toHomeModel() = HomeModel(
    id = id,
    title = title,
    imgUrl = imgUrl,
    description = description,
    datetime = datetime,
    isBookmarked = isBookmarked,
)
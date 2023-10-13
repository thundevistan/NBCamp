package bootcamp.sparta.disneym.domain.model

import android.os.Parcelable
import bootcamp.sparta.disneym.ui.bookmark.BookmarkViewType
import kotlinx.android.parcel.Parcelize

@Parcelize
data class HomeModel(
    val id: String? = null,
    val title: String,
    val description: String,
    val imgUrl: String,
    val datetime: String,
    val isBookmarked: Boolean = false,
) : Parcelable

fun HomeModel.toBookmarkModel(): BookmarkModel =
    BookmarkModel(
        id = id,
        title = title,
        imgUrl = imgUrl,
        description = description,
        datetime = datetime,
        isBookmarked = isBookmarked,
        viewType = BookmarkViewType.Normal.INT,
        isChecked = false
    )

fun HomeModel.toDetailModel(): DetailModel =
    DetailModel(
        id = id,
        title = title,
        imgUrl = imgUrl,
        description = description,
        datetime = datetime,
        isBookmarked = isBookmarked,
    )
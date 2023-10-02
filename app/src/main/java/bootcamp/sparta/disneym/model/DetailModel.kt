package bootcamp.sparta.disneym.model

import android.os.Parcelable
import bootcamp.sparta.disneym.ui.bookmark.BookmarkViewType
import kotlinx.android.parcel.Parcelize

@Parcelize
data class DetailModel(
    val title: String,
    val imgUrl : String,
    val description : String,
    val datetime : String,
    val isBookmarked : Boolean
) : Parcelable

fun DetailModel.toBookmarkModel() : BookmarkModel{
    return BookmarkModel(
        title = title,
        imgUrl = imgUrl,
        description = description,
        datetime = datetime,
        isBookmarked = isBookmarked,
        viewType = BookmarkViewType.Normal,
        isChecked = false
    )
}

fun DetailModel.toHomeModel() : HomeModel = HomeModel(
)
package bootcamp.sparta.disneym.ui.search

import android.os.Parcelable
import bootcamp.sparta.disneym.data.datasource.remote.SearchModel
import kotlinx.parcelize.Parcelize

@Parcelize
data class SearchTestModel(
    val thumbnails: Int

) : Parcelable {
}
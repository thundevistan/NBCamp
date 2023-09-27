package bootcamp.sparta.disneym.ui.search

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class SearchViewTest(
    val thumbnail: Int,
    val title: String,
    val channelName: String,
    val dataTime: String
) : Parcelable {
}
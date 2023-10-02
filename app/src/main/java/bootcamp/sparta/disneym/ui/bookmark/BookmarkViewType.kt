package bootcamp.sparta.disneym.ui.bookmark

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

/*
* 작성자: 서정한
* 내용: RecyclerView Adapter에서 ViewType 분기처리를 위한 sealed Class
* */
@Parcelize
sealed class BookmarkViewType : Parcelable {
    data object Normal : BookmarkViewType() {
        // adapter의 onCreateViewHolder에서 viewType 분기처리시 사용
        const val INT = 0
    }
    data object Edit: BookmarkViewType() {
        // adapter의 onCreateViewHolder에서 viewType 분기처리시 사용
        const val INT= 1
    }
}

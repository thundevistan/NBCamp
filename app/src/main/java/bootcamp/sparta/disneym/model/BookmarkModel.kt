package bootcamp.sparta.disneym.model

import bootcamp.sparta.disneym.ui.bookmark.BookmarkViewType

/*
* 작성자: 서정한
* 내용: Bookmark에서 사용하는 Model
* */
data class BookmarkModel(
    val title: String,
    val imgUrl : String,
    val description : String,
    val datetime : String,
    val viewType: BookmarkViewType = BookmarkViewType.Normal, // RecyclerView Adapter의 ViewType 관리용
    val isChecked: Boolean = false, // RecyclerView Adapter의 체크박스 관리용
)
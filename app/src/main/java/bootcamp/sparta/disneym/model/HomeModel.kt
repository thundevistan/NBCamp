package bootcamp.sparta.disneym.model

import bootcamp.sparta.disneym.ui.bookmark.BookmarkViewType

data class HomeModel(
	val id: String,
	val title: String,
	val description: String,
	val imgUrl: String,
	val datetime: String
	val viewType: BookmarkViewType = BookmarkViewType.Normal, // RecyclerView Adapter의 ViewType 관리용
	val isChecked: Boolean = false, // RecyclerView Adapter의 체크박스 관리용
)
package bootcamp.sparta.disneym.ui.home

import androidx.recyclerview.widget.DiffUtil
import bootcamp.sparta.disneym.model.HomeModel

class DiffUtil(
	private val oldList: List<HomeModel>,
	private val newList: List<HomeModel>
) : DiffUtil.Callback() {
	override fun getOldListSize(): Int {
		return oldList.size
	}

	override fun getNewListSize(): Int {
		return newList.size
	}

	override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
		return oldList[oldItemPosition] == newList[newItemPosition]
	}

	override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
		val oldVideo = oldList[oldItemPosition]
		val newVideo = newList[newItemPosition]

		return oldVideo.title == newVideo.title
	}
}
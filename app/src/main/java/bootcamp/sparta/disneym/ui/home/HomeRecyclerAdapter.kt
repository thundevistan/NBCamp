package bootcamp.sparta.disneym.ui.home

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Recycler
import bootcamp.sparta.disneym.databinding.HomeRecyclerItemBinding
import bootcamp.sparta.disneym.model.HomeModel
import com.bumptech.glide.Glide

/**
 * Copyright 2023 김민준, Inc.
 *
 * HomeFragment 하단의 scrollView 안에 위치한 recyclerView의 어댑터
 */
//class HomeRecyclerAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
//
//	private var oldItems = emptyList<HomeModel>()
//
//	interface ItemClick {
//		fun onClick(view: View, position: Int)
//	}
//
//	var itemClick: ItemClick? = null
//
//	override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
//		val view = HomeRecyclerItemBinding.inflate(
//			LayoutInflater.from(parent.context),
//			parent,
//			false
//		)
//		return ViewHolder(view)
//	}
//
//	override fun getItemCount(): Int {
//		return oldItems.size
//	}
//
//	override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
//		(holder as ViewHolder).setData(oldItems[position])
//	}
//
//	inner class ViewHolder(itemView: HomeRecyclerItemBinding) :
//		RecyclerView.ViewHolder(itemView.root) {
//
//		private val binding = itemView
//
//		fun setData(data: HomeModel) {
//			Glide.with(binding.root)
//				.load(data.imgUrl)
//				.into(binding.homeThumbnailIv)
//			Log.d("imgUrl", data.imgUrl)
//		}
//	}
//
//	fun setData(newList: List<HomeModel>) {
//		val videoDiff = DiffUtil(oldItems, newList)
//		val diff = DiffUtil.calculateDiff(videoDiff)
//		oldItems = newList
//		diff.dispatchUpdatesTo(this)
//	}
//}

class HomeRecyclerAdapter(
	private val onItemClicked: (HomeModel) -> Unit,
) : ListAdapter<HomeModel, HomeRecyclerAdapter.Holder>(
	object: DiffUtil.ItemCallback<HomeModel>() {
		override fun areItemsTheSame(oldItem: HomeModel, newItem: HomeModel): Boolean {
			return oldItem.imgUrl == newItem.imgUrl
		}

		override fun areContentsTheSame(oldItem: HomeModel, newItem: HomeModel): Boolean {
			return oldItem == newItem
		}
	}
) {
	override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
		val view = HomeRecyclerItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
		return Holder(view, onItemClicked)
	}

	override fun onBindViewHolder(holder: Holder, position: Int) {
		holder.onBind(getItem(position))
	}

	class Holder(
		private val binding: HomeRecyclerItemBinding,
		private val onItemClicked: (HomeModel) -> Unit
	): RecyclerView.ViewHolder(binding.root) {
		fun onBind(item: HomeModel)=with(binding) {
			Glide.with(binding.root)
				.load(item.imgUrl)
				.into(binding.homeThumbnailIv)

			// 클릭이벤트
			itemView.setOnClickListener {
				onItemClicked(item)
			}
		}
	}

}
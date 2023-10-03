package bootcamp.sparta.disneym.ui.home

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import bootcamp.sparta.disneym.databinding.HomeViewpagerItemBinding
import bootcamp.sparta.disneym.model.HomeModel
import com.bumptech.glide.Glide

/**
 * Copyright 2023 김민준, Inc.
 *
 * HomeFragment 상단에 위치한 viewpager의 어댑터
 */
class HomeViewPagerAdapter :
	RecyclerView.Adapter<HomeViewPagerAdapter.ViewHolder>() {

	private var oldItems = emptyList<HomeModel>()

	interface ItemClick {
		fun onClick(view: View, position: Int)
	}

	var itemClick: ItemClick? = null

	override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
		val view = HomeViewpagerItemBinding.inflate(
			LayoutInflater.from(parent.context),
			parent,
			false
		)
		return ViewHolder(view)
	}

	override fun onBindViewHolder(holder: ViewHolder, position: Int) {
		holder.setData(oldItems[position])
	}

	override fun getItemCount(): Int {
		return oldItems.size
	}

	inner class ViewHolder(itemVIew: HomeViewpagerItemBinding) :
		RecyclerView.ViewHolder(itemVIew.root) {

		private val binding = itemVIew

		fun setData(data: HomeModel) {
			Glide.with(binding.root)
				.load(data.imgUrl)
				.into(binding.homeTitleIv)
			Log.d("viewpager", data.imgUrl)
		}
	}

	fun setData(newList: List<HomeModel>) {
		val videoDiff = DiffUtil(oldItems, newList)
		val diff = androidx.recyclerview.widget.DiffUtil.calculateDiff(videoDiff)
		oldItems = newList
		diff.dispatchUpdatesTo(this)
	}
}
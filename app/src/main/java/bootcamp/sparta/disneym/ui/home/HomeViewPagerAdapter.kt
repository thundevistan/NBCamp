package bootcamp.sparta.disneym.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import bootcamp.sparta.disneym.databinding.HomeViewpagerItemBinding
import bootcamp.sparta.disneym.model.HomeModel
import com.bumptech.glide.Glide

/**
 * Copyright 2023 김민준, Inc.
 *
 * HomeFragment 상단에 위치한 viewpager의 어댑터
 */
class HomeViewPagerAdapter(
	private val onItemClicked: (HomeModel) -> Unit
) : ListAdapter<HomeModel, HomeViewPagerAdapter.Holder>(
	object : DiffUtil.ItemCallback<HomeModel>() {
		override fun areItemsTheSame(oldItem: HomeModel, newItem: HomeModel): Boolean {
			return oldItem.imgUrl == newItem.imgUrl
		}

		override fun areContentsTheSame(oldItem: HomeModel, newItem: HomeModel): Boolean {
			return oldItem == newItem
		}

	}
) {
	override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
		val view =
			HomeViewpagerItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
		return Holder(view, onItemClicked)
	}

	override fun onBindViewHolder(holder: Holder, position: Int) {
		holder.onBind(getItem(position))
	}

	class Holder(
		private val binding: HomeViewpagerItemBinding,
		private val onItemClicked: (HomeModel) -> Unit
	) : RecyclerView.ViewHolder(binding.root) {

		fun onBind(item: HomeModel) = with(binding) {
			Glide.with(binding.homeTitleIv)
				.load(item.imgUrl)
				.into(binding.homeTitleIv)

			itemView.setOnClickListener {
				onItemClicked(item)
			}
		}
	}
}
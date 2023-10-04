package bootcamp.sparta.disneym.ui.search

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import bootcamp.sparta.disneym.databinding.SearchRecyclerViewItemBinding
import bootcamp.sparta.disneym.model.SearchModel
import bootcamp.sparta.disneym.ui.viewmodel.MainSharedViewModel
import com.bumptech.glide.Glide

/*
* Copyright 2023 김현준, Inc.
*
* search_recycler_view_item xml파일로 search main 나타내기 위한 어댑터.
* 추가 예정
*
* */

class SearchViewAdapter(
    private val onItemClick : (SearchModel) -> Unit,
) : ListAdapter<SearchModel, SearchViewAdapter.ViewHolder>(
    object : DiffUtil.ItemCallback<SearchModel>() {
        override fun areItemsTheSame(oldItem: SearchModel, newItem: SearchModel): Boolean {
            return oldItem.imgUrl == newItem.imgUrl
        }

        override fun areContentsTheSame(oldItem: SearchModel, newItem: SearchModel): Boolean {
            return oldItem == newItem
        }

    }
) {
    class ViewHolder(
        private val binding: SearchRecyclerViewItemBinding,
        private val onItemClick: (SearchModel) -> Unit,
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind (item: SearchModel)= with(binding) {
            Glide.with(itemView)
                .load(item.imgUrl)
                .into(searchViewThumbnail)

            searchViewTitle.text = item.title
            searchViewChannelname.text = item.channelTitle
            searchViewViews.text = item.datetime

            itemView.setOnClickListener {
                onItemClick(item)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = SearchRecyclerViewItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(
            binding,
            onItemClick
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)
    }
}


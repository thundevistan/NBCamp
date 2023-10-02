package bootcamp.sparta.disneym.ui.search

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import bootcamp.sparta.disneym.databinding.SearchRecyclerViewItemBinding
import bootcamp.sparta.disneym.model.SearchModel
import com.bumptech.glide.Glide

/*
* Copyright 2023 김현준, Inc.
*
* search_recycler_view_item xml파일로 search main 나타내기 위한 어댑터.
* 추가 예정
*
* */

class SearchViewAdapter: ListAdapter<SearchModel, SearchViewAdapter.ViewHolder>(
    object : DiffUtil.ItemCallback<SearchModel>() {
        override fun areItemsTheSame(oldItem: SearchModel, newItem: SearchModel): Boolean {
            return oldItem.imgUrl == newItem.imgUrl
        }

        override fun areContentsTheSame(oldItem: SearchModel, newItem: SearchModel): Boolean {
            return oldItem == newItem
        }

    }
) {

    class ViewHolder(private val binding: SearchRecyclerViewItemBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind (item: SearchModel)= with(binding) {
            Glide.with(itemView)
                .load(item.imgUrl)
                .into(searchViewThumbnail)

            searchViewTitle.text = item.title
            searchViewChannelname.text = item.channelTitle
            searchViewViews.text = item.datetime
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = SearchRecyclerViewItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}


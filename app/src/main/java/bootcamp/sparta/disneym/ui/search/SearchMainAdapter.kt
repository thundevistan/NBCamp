package bootcamp.sparta.disneym.ui.search

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import bootcamp.sparta.disneym.databinding.SearchRecyclerMainItemBinding

/*
* Copyright 2023 김현준, Inc.
*
* search_recycler_main_item xml파일로 search main 나타내기 위한 어댑터.
* 추가 예정
*
* */

class SearchMainAdapter(private val items: MutableList<SearchTestModel>) : RecyclerView.Adapter<SearchMainAdapter.ViewHolder>() {


    fun set(newItems: List<SearchTestModel>) {
        items.clear()
        items.addAll(newItems)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = SearchRecyclerMainItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = items[position]
        holder.bind(item)

    }

    inner class ViewHolder(private val binding: SearchRecyclerMainItemBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(item: SearchTestModel) {
            binding.searchMainThumbnail.setImageResource(item.thumbnails)
        }
    }
}
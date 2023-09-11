package com.kotdev99.android.week4_mini_assignment

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.kotdev99.android.week4_mini_assignment.databinding.ItemRecyclerBinding
import com.kotdev99.android.week4_mini_assignment.sampledata.NewsItem

class RecyclerAdapter(private val datas: ArrayList<NewsItem>) :
	RecyclerView.Adapter<RecyclerAdapter.ViewHolder>() {

	interface ItemClick {
		fun onClick(view: View, position: Int)
	}

	var itemClick: ItemClick? = null

	override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerAdapter.ViewHolder {
		Log.d("RecyclerAdapter", "onCreateViewHolder")
		return ViewHolder(
			ItemRecyclerBinding.inflate(
				LayoutInflater.from(parent.context),
				parent,
				false
			)
		)
	}

	override fun onBindViewHolder(holder: RecyclerAdapter.ViewHolder, position: Int) {
		holder.bind(datas[position])
		Log.d("RecyclerAdapter", "onBindViewHolder")

		holder.itemView.setOnClickListener {
			itemClick?.onClick(it, position)
			Log.d("RecyclerAdapter", "onClick")
		}
	}

	override fun getItemId(position: Int): Long {
		return position.toLong()
	}

	override fun getItemCount(): Int {
		return datas.size
	}

	inner class ViewHolder(private val binding: ItemRecyclerBinding) :
		RecyclerView.ViewHolder(binding.root) {

		private val title = binding.tvTitle
		private val article = binding.tvArticle

		fun bind(item: NewsItem) {

			title.text = item.title
			article.text = item.article
			Log.d("RecyclerAdapter", "bind")
		}
	}
}
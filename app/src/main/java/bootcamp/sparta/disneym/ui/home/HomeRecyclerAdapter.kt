package bootcamp.sparta.disneym.ui.home

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import bootcamp.sparta.disneym.databinding.HomeRecyclerItemBinding
import bootcamp.sparta.disneym.model.HomeModel
import bootcamp.sparta.disneym.viewmodel.Home.HomeViewModel
import com.bumptech.glide.Glide

/**
 * Copyright 2023 김민준, Inc.
 *
 * HomeFragment 하단의 scrollView 안에 위치한 recyclerView의 어댑터
 */
class HomeRecyclerAdapter(val context: Context) :
    RecyclerView.Adapter<HomeRecyclerAdapter.ViewHolder>() {

    private val dataList = HomeViewModel().films

    interface ItemClick {
        fun onClick(view: View, position: Int)
    }

    var itemClick: ItemClick? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            HomeRecyclerItemBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        if (dataList.value == null) {
            return
        }
        val itemList = dataList.value
        val item = itemList?.get(position)
        item?.let { holder.bind(it) }
    }

    override fun getItemCount(): Int {
        return dataList.value.size
    }

    inner class ViewHolder(binding: HomeRecyclerItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        private val thumbnail = binding.homeThumbnailIv

        fun bind(item: HomeModel) {

            Glide.with(context).load(dataList).into(thumbnail)
        }
    }
}
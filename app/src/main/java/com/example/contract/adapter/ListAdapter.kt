package com.example.contract.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.contract.R
import com.example.contract.databinding.FragmentContractBinding
import com.example.contract.databinding.ListViewLeftBinding

class ListAdapter(private val dataList: MutableList<ContactItem>) : RecyclerView.Adapter<ListAdapter.ViewHolder>() {

    inner class ViewHolder(private val binding: ListViewLeftBinding) : RecyclerView.ViewHolder(binding.root) {
        val profileImage = binding.profileImage
        val listName = binding.listName
        val groupName = binding.groupName
        val favoritButton = binding.favoritButton
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ListViewLeftBinding.inflate(inflater, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = dataList[position]
        holder.profileImage.setImageResource(item.profileImage)
        holder.listName.text = item.listName
        holder.groupName.text = item.groupName
        if (item.isFavorite) {
            holder.favoritButton.setImageResource(R.drawable.ic_love_filled)
        } else {
            holder.favoritButton.setImageResource(R.drawable.ic_love_empty)
        }
    }

    override fun getItemCount(): Int {
        return dataList.size
    }
}
package com.kotdev99.android.week7_assignment

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.kotdev99.android.week7_assignment.databinding.ItemMainBinding


class ProductAdapter(private val productList: MutableList<Product>) :
	RecyclerView.Adapter<ProductAdapter.ViewHolder>() {

	interface ItemClick {
		fun onClick(view: View, position: Int)
	}

	var itemClick: ItemClick? = null

	override fun getItemCount(): Int {
		return productList.size
	}

	override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductAdapter.ViewHolder {
		return ViewHolder(
			ItemMainBinding.inflate(
				LayoutInflater.from(parent.context), parent, false
			)
		)
	}

	override fun onBindViewHolder(holder: ProductAdapter.ViewHolder, position: Int) {
		holder.itemView.setOnClickListener {
			itemClick?.onClick(it, position)
		}
		holder.thumb.setImageResource(productList[position].thumb)
		holder.thumb.clipToOutline = true
		holder.name.text = productList[position].name
		holder.addr.text = productList[position].addr
		holder.price.text = productList[position].price.toString()
		holder.chatCnt.text = productList[position].chat.toString()
		holder.favCnt.text = productList[position].fav.toString()
	}

	inner class ViewHolder(binding: ItemMainBinding) : RecyclerView.ViewHolder(binding.root) {
		val thumb = binding.ivThumb
		val name = binding.tvName
		val addr = binding.tvAddr
		val price = binding.tvPrice
		val chat = binding.ivChat
		val chatCnt = binding.tvChatCnt
		val fav = binding.ivFav
		val favCnt = binding.tvFavCnt
	}
}
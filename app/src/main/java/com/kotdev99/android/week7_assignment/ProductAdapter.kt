package com.kotdev99.android.week7_assignment

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.kotdev99.android.week7_assignment.databinding.ItemMainBinding


class ProductAdapter(private val productList: ArrayList<Product>) :
	RecyclerView.Adapter<ProductAdapter.ViewHolder>() {

	interface ItemClick {
		fun onClick(view: View, position: Int)
	}

	var itemClick: ItemClick? = null

	interface ItemLongClick {
		fun onLongClick(view: View, position: Int)
	}

	var itemLongClick: ItemLongClick? = null

	override fun getItemCount(): Int {
		return productList.size
	}

	override fun getItemId(position: Int): Long {
		return position.toLong()
	}

	override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductAdapter.ViewHolder {
		return ViewHolder(
			ItemMainBinding.inflate(
				LayoutInflater.from(parent.context), parent, false
			)
		)
	}

	@SuppressLint("NotifyDataSetChanged")
	override fun onBindViewHolder(holder: ProductAdapter.ViewHolder, position: Int) {
		holder.itemView.setOnClickListener {
			itemClick?.onClick(it, position)
		}
		holder.itemView.setOnLongClickListener(View.OnLongClickListener {
			itemLongClick?.onLongClick(it, position)
			return@OnLongClickListener true
		})

		holder.thumb.setImageResource(productList[position].thumb)
		holder.thumb.clipToOutline = true
		holder.name.text = productList[position].name
		holder.addr.text = productList[position].addr
		holder.price.text = productList[position].dec()
		holder.chatCnt.text = productList[position].chat.toString()
		holder.fav.setImageResource(if (productList[position].isFav) {R.drawable.ic_isfav} else {R.drawable.ic_fav})
		holder.favCnt.text = productList[position].fav.toString()
	}

	inner class ViewHolder(binding: ItemMainBinding) : RecyclerView.ViewHolder(binding.root) {
		val thumb = binding.ivThumb
		val name = binding.tvName
		val addr = binding.tvAddr
		val price = binding.tvPrice
		val chatCnt = binding.tvChatCnt
		val fav = binding.ivFav
		val favCnt = binding.tvFavCnt
	}
}
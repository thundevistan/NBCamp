package com.kotdev99.android.contactlist

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.kotdev99.android.contactlist.databinding.ActivityItemBinding
import com.kotdev99.android.contactlist.databinding.ActivityItemFavBinding

class RecyclerAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
	private var items = ArrayList<ContactData>()

	override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
		if (viewType == ViewType.view_Type1) {
			if (viewType == ViewType.view_Type1) {
				return ViewHolder(
					ActivityItemBinding.inflate(
						LayoutInflater.from(parent.context),
						parent,
						false
					)
				)
			}
		}
		return ViewHolderFav(
			ActivityItemFavBinding.inflate(
				LayoutInflater.from(parent.context),
				parent,
				false
			)
		)
	}

	override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
		when (items[position].viewType) {
			ViewType.view_Type1 -> {
				(holder as ViewHolder).bind(items[position])
			}

			ViewType.view_Type2 -> {
				(holder as ViewHolderFav).bind(items[position])
			}
		}
	}

	override fun getItemCount(): Int {
		return items.size
	}

	override fun getItemViewType(position: Int): Int {
		return items[position].viewType
	}

	class ViewHolder(binding: ActivityItemBinding) : RecyclerView.ViewHolder(binding.root) {
		private val profile = binding.ivProfile
		private val name = binding.tvName
		private val tel = binding.tvTel

		fun bind(item: ContactData) {
			profile.setImageResource(item.profile)
			name.text = item.name
			tel.text = item.tel
		}
	}

	class ViewHolderFav(binding: ActivityItemFavBinding) : RecyclerView.ViewHolder(binding.root) {
		private val profile = binding.ivProfileFav
		private val name = binding.tvNameFav
		private val tel = binding.tvTelFav

		fun bind(item: ContactData) {
			profile.setImageResource(item.profile)
			name.text = item.name
			tel.text = item.tel
		}
	}

	@SuppressLint("NotifyDataSetChanged")
	fun setData(list: ArrayList<ContactData>) {
		items = list
		notifyDataSetChanged()
	}
}

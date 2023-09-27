package bootcamp.sparta.disneym.ui.home

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import bootcamp.sparta.disneym.databinding.HomeViewpagerItemBinding
import bootcamp.sparta.disneym.model.Get
import bootcamp.sparta.disneym.model.HomeModel

class HomeRecyclerAdapter(val context: Context) :
	RecyclerView.Adapter<HomeRecyclerAdapter.ViewHolder>() {

	private val get = Get()
	private val dataList = get.getter()

	interface ItemClick {
		fun onClick(view: View, position: Int)
	}

	var itemClick: ItemClick? = null

	override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
		return ViewHolder(
			HomeViewpagerItemBinding.inflate(
				LayoutInflater.from(parent.context),
				parent,
				false
			)
		)
	}

	override fun onBindViewHolder(holder: ViewHolder, position: Int) {
		holder.bind(dataList[position])
	}

	override fun getItemCount(): Int {
		return dataList.size
	}

	inner class ViewHolder(binding: HomeViewpagerItemBinding) :
		RecyclerView.ViewHolder(binding.root) {

		private val thumbnail = binding.homeTestImageView

		fun bind(item: HomeModel) {
			thumbnail.setImageResource(item.thumbnail)
		}
	}
}
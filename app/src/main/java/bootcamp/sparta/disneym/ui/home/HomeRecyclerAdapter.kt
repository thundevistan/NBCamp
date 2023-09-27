package bootcamp.sparta.disneym.ui.home

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import bootcamp.sparta.disneym.databinding.HomeRecyclerItemBinding
import bootcamp.sparta.disneym.model.Get
import bootcamp.sparta.disneym.model.HomeModel
import com.bumptech.glide.Glide

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
			HomeRecyclerItemBinding.inflate(
				LayoutInflater.from(parent.context), parent, false
			)
		)
	}

	override fun onBindViewHolder(holder: ViewHolder, position: Int) {
		holder.bind(dataList[position])
	}

	override fun getItemCount(): Int {
		return dataList.size
	}

	inner class ViewHolder(binding: HomeRecyclerItemBinding) :
		RecyclerView.ViewHolder(binding.root) {

		private val homeLinearLayout = binding.homeLowerLinear
		private val imageView = ImageView(context)

		fun bind(item: HomeModel) {

			Glide.with(context).load(item.thumbnail).override(300, 300).into(imageView)

			imageView.adjustViewBounds = true
			homeLinearLayout.addView(imageView)
		}
	}
}
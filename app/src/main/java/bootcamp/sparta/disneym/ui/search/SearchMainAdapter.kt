package bootcamp.sparta.disneym.ui.search

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import bootcamp.sparta.disneym.databinding.SearchRecyclerMainItemBinding

class SearchMainAdapter(private val items: List<SearchTestModel>) : RecyclerView.Adapter<SearchMainAdapter.ViewHolder>() {


    fun set() {

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

//        init {
//            val layoutParams = binding.searchMainThumbnail.layoutParams
//            layoutParams.width = 175
//            layoutParams.height = 175
//            binding.searchMainThumbnail.layoutParams = layoutParams
//        }

    }
}
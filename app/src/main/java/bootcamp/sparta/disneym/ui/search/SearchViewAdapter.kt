package bootcamp.sparta.disneym.ui.search

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import bootcamp.sparta.disneym.databinding.SearchRecyclerViewItemBinding

class SearchViewAdapter(private val items: List<SearchViewTest>): RecyclerView.Adapter<SearchViewAdapter.ViewHolder>() {



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = SearchRecyclerViewItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = items[position]
        holder.bind(item)
    }

    inner class ViewHolder(private val binding: SearchRecyclerViewItemBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind (item: SearchViewTest ) {
            binding.searchViewThumbnail.setImageResource(item.thumbnail)
            binding.searchViewTitle.text = item.title
            binding.searchViewChannelname.text = item.channelName
            binding.searchViewViews.text = item.dataTime
        }
    }
}
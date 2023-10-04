package bootcamp.sparta.disneym.ui.bookmark

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import bootcamp.sparta.disneym.databinding.BookmarkRecyclerItemBinding
import bootcamp.sparta.disneym.databinding.BookmarkRecyclerItemCheckboxBinding
import bootcamp.sparta.disneym.model.BookmarkModel
import com.bumptech.glide.Glide

/*
* 작성자: 서정한
* 내용: RecyclerView Adapter.
* viewType에 따라 Holder or EditHolder를 띄워줌.
* */
class BookmarkListAdapter(private val onItemChecked: (Int, BookmarkModel) -> Unit) :
    ListAdapter<BookmarkModel, RecyclerView.ViewHolder>(
        object : DiffUtil.ItemCallback<BookmarkModel>() {
            override fun areItemsTheSame(oldItem: BookmarkModel, newItem: BookmarkModel): Boolean {
                return oldItem.imgUrl == newItem.imgUrl
            }

            override fun areContentsTheSame(
                oldItem: BookmarkModel,
                newItem: BookmarkModel
            ): Boolean {
                return oldItem == newItem
            }
        }
    ) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            BookmarkViewType.Normal.INT -> {
                val view = BookmarkRecyclerItemBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
                Holder(view)
            }

            BookmarkViewType.Edit.INT -> {
                val view = BookmarkRecyclerItemCheckboxBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
                EditHolder(view, onItemChecked)
            }

            else -> {
                val view = BookmarkRecyclerItemBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
                Holder(view)
            }
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is Holder -> {
                holder.bind(getItem(position))
            }

            is EditHolder -> {
                holder.bind(getItem(position))
            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        return getItem(position).viewType
    }

    class Holder(private val binding: BookmarkRecyclerItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: BookmarkModel) = with(binding) {
            Glide.with(itemView.context)
                .load(item.imgUrl)
                .into(bookmarkItemImage)
            bookmarkItemTitle.text = item.title
        }
    }

    class EditHolder(
        private val binding: BookmarkRecyclerItemCheckboxBinding,
        private val onItemChecked: (Int, BookmarkModel) -> Unit
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: BookmarkModel) = with(binding) {
            Glide.with(itemView.context)
                .load(item.imgUrl)
                .into(bookmarkItemImage)
            bookmarkItemTitle.text = item.title
            bookmarkItemCheckbox.isChecked = item.isChecked

            // 체크박스 체크되어있을때만 ClickEvent 동작
            bookmarkItemCheckbox.setOnClickListener {
                onItemChecked(adapterPosition, item.copy(
                    isChecked = bookmarkItemCheckbox.isChecked
                ))
            }
        }
    }
}
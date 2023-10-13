package bootcamp.sparta.disneym.ui.mypage.dialog

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import bootcamp.sparta.disneym.databinding.MypageRecyclerItemBinding
/*
* 추민수
* List Adapter(RecyclerView Adapter)
* 클릭 이벤트를 넘겨줌
* */
class ProfileListAdapter(
    private val onClickItem: (Int,ProfileDialogModel) -> Unit
) : ListAdapter<ProfileDialogModel, ProfileListAdapter.ViewHolder>(

    object : DiffUtil.ItemCallback<ProfileDialogModel>() {
        override fun areItemsTheSame(
            oldItem: ProfileDialogModel,
            newItem: ProfileDialogModel
        ): Boolean {
            return oldItem.profileImage == newItem.profileImage
        }

        override fun areContentsTheSame(
            oldItem: ProfileDialogModel,
            newItem: ProfileDialogModel
        ): Boolean {
            return oldItem == newItem
        }
    }
) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            MypageRecyclerItemBinding.inflate(LayoutInflater.from(parent.context), parent, false),
            onClickItem
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)
    }

    class ViewHolder(
        private val binding: MypageRecyclerItemBinding,
        private val onClickItem: (Int,ProfileDialogModel) -> Unit,
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(item: ProfileDialogModel) = with(binding) {
            recyclerImage.setImageResource(item.profileImage)
            // 아이템 클릭
            container.setOnClickListener {
                onClickItem(
                    adapterPosition,
                    item
                )
            }
        }
    }
}
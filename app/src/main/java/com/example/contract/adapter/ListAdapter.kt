package com.example.contract.adapter

import android.content.Context
import android.content.Intent
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Rect
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.contract.DetailActivity
import com.example.contract.NotifyChanged
import com.example.contract.R
import com.example.contract.databinding.ListViewGridBinding
import com.example.contract.databinding.ListViewLeftBinding
import com.example.contract.databinding.ListViewRightBinding
import com.example.contract.sampledata.ContactItem
import com.example.contract.sampledata.ContactItem.Companion.VIEW_TYPE_GRID
import com.example.contract.sampledata.ContactItem.Companion.VIEW_TYPE_LEFT
import com.example.contract.sampledata.ContactItem.Companion.VIEW_TYPE_RIGHT
import com.example.contract.sampledata.ContactManager

class ListAdapter(private val items: MutableList<ContactItem>, private val context: Context) : RecyclerView.Adapter<RecyclerView.ViewHolder>(), NotifyChanged {
    interface ItemClick {
        fun onClick(view: View, position: Int)
    }
    var itemClick: ItemClick? = null

    inner class LeftViewHolder(private val binding: ListViewLeftBinding) : RecyclerView.ViewHolder(binding.root) {
        private val profileImage = binding.profileImage
        private val listName = binding.listName
        private val groupName = binding.groupName
        private val favoritButton = binding.favoritButton

        fun bindLeft(item: ContactItem) {
            itemView.setOnClickListener {
                val intent = Intent(context, DetailActivity::class.java)
                val contactList = ContactManager.getContact()
                val contact = contactList[adapterPosition]
                intent.putExtra("profileImage", contact.profileImage)
                intent.putExtra("listName", contact.listName)
                intent.putExtra("phoneNumber", contact.phoneNumber)
                intent.putExtra("email", contact.email)
                intent.putExtra("event", contact.event)

                context.startActivity(intent)
            }
            profileImage.setImageResource(item.profileImage)
            listName.text = item.listName
            groupName.text = item.groupName
            if (item.isFavorite) {
                favoritButton.setImageResource(R.drawable.ic_love_filled)
            } else {
                favoritButton.setImageResource(R.drawable.ic_love_empty)
            }
        }
    }

    inner class RightViewHolder(private val binding: ListViewRightBinding) : RecyclerView.ViewHolder(binding.root) {
        private val profileImage = binding.profileImage
        private val listName = binding.listName
        private val groupName = binding.groupName
        private val favoritButton = binding.favoritButton
        fun bindRight(item: ContactItem) {
            itemView.setOnClickListener {
                val intent = Intent(context, DetailActivity::class.java)
                val contactList = ContactManager.getContact()
                val contact = contactList[adapterPosition]
                intent.putExtra("profileImage", contact.profileImage)
                intent.putExtra("listName", contact.listName)
                intent.putExtra("phoneNumber", contact.phoneNumber)
                intent.putExtra("email", contact.email)
                intent.putExtra("event", contact.event)

                context.startActivity(intent)
            }
            profileImage.setImageResource(item.profileImage)
            listName.text = item.listName
            groupName.text = item.groupName
            if (item.isFavorite) {
                favoritButton.setImageResource(R.drawable.ic_love_filled)
            } else {
                favoritButton.setImageResource(R.drawable.ic_love_empty)
            }
        }
    }

    inner class GridViewHolder(private val binding: ListViewGridBinding) : RecyclerView.ViewHolder(binding.root) {
        private val profileImage = binding.profileImage
        private val listName = binding.listName
        private val groupName = binding.groupName
        private val favoritButton = binding.favoritButton
        fun bindGrid(item: ContactItem) {
            itemView.setOnClickListener {
                val intent = Intent(context, DetailActivity::class.java)
                val contactList = ContactManager.getContact()
                val contact = contactList[adapterPosition]
                intent.putExtra("profileImage", contact.profileImage)
                intent.putExtra("listName", contact.listName)
                intent.putExtra("phoneNumber", contact.phoneNumber)
                intent.putExtra("email", contact.email)
                intent.putExtra("event", contact.event)

                context.startActivity(intent)
            }
            profileImage.setImageResource(item.profileImage)
            listName.text = item.listName
            groupName.text = item.groupName
            if (item.isFavorite) {
                favoritButton.setImageResource(R.drawable.ic_love_filled)
            } else {
                favoritButton.setImageResource(R.drawable.ic_love_empty)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return when (viewType) {
            VIEW_TYPE_LEFT -> {
                val binding = ListViewLeftBinding.inflate(inflater, parent, false)
                LeftViewHolder(binding)
            }
            VIEW_TYPE_RIGHT -> {
                val binding = ListViewRightBinding.inflate(inflater, parent, false)
                RightViewHolder(binding)
            }
            VIEW_TYPE_GRID -> {
                val binding = ListViewGridBinding.inflate(inflater, parent, false)
                GridViewHolder(binding)
            }
            else -> throw IllegalArgumentException("Invalid view type")
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val item = items[position]

        when (holder) {
            is LeftViewHolder -> holder.bindLeft(item)
            is RightViewHolder -> holder.bindRight(item)
            is GridViewHolder -> holder.bindGrid(item)
        }
    }

    private var isGridLayout: Boolean = false
    fun setGridLayout(isGridLayout: Boolean) {
        this.isGridLayout = isGridLayout
        notifyDataSetChanged()
    }
    override fun getItemViewType(position: Int): Int {
        return if (isGridLayout) {
            VIEW_TYPE_GRID
        } else {
            if (position % 2 == 0) {
                VIEW_TYPE_LEFT
            } else {
                VIEW_TYPE_RIGHT
            }
        }
    }

    override fun getItemCount(): Int {
        return items.size
    }
    class AddressAdapterDecoration : RecyclerView.ItemDecoration() {
        private val paint = Paint()
        init {
            paint.color = Color.GRAY
        }
        override fun onDraw(c: Canvas, parent: RecyclerView, state: RecyclerView.State) {
            super.onDraw(c, parent, state)

            for (i in 0 until parent.childCount) {
                val child = parent.getChildAt(i)
                val layoutParams = child.layoutParams as RecyclerView.LayoutParams
                val top = (child.bottom + layoutParams.bottomMargin + 80).toFloat()
                val bottom = top + 1f

                val left = parent.paddingStart.toFloat()
                val right = (parent.width - parent.paddingEnd).toFloat()

                c.drawRect(left, top, right, bottom, paint)
            }
        }
        override fun getItemOffsets(
            outRect: Rect,
            view: View,
            parent: RecyclerView,
            state: RecyclerView.State
        ) {
            super.getItemOffsets(outRect, view, parent, state)
            val offset = 80
            outRect.top = offset
            outRect.bottom = offset
        }
    }

    override fun notifyChanged() {
        notifyDataSetChanged()
    }
}
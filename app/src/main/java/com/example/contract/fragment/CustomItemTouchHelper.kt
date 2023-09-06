package com.example.contract.fragment

import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.example.contract.adapter.ListAdapter
import com.example.contract.sampledata.ContactManager

class CustomItemTouchHelper(val context: Context, val adapter: ListAdapter) : ItemTouchHelper.SimpleCallback(
    0, // 드래그 플래그 (0으로 설정하여 드래그 비활성화)
    ItemTouchHelper.RIGHT // 오른쪽 스와이프만 허용
) {
    override fun onMove(
        recyclerView: RecyclerView,
        viewHolder: RecyclerView.ViewHolder,
        target: RecyclerView.ViewHolder
    ): Boolean {
        // 아이템을 드래그하여 재배치하는 로직을 여기에 구현
        return false
    }

    override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
        // 아이템을 스와이프할 때 호출되는 메서드
        if (direction == ItemTouchHelper.RIGHT) {
            // 오른쪽으로 스와이프한 경우 전화 걸기 동작을 수행
            val position = viewHolder.bindingAdapterPosition
            val contactItem = ContactManager.getContact()[position]
            val phoneNumber = contactItem.phoneNumber
            // 여기에서 전화 걸기 동작을 수행하도록 코드를 작성

            // 전화 걸기 Intent 생성
            val callIntent = Intent(Intent.ACTION_CALL)
            callIntent.data = Uri.parse("tel:$phoneNumber")
            context.startActivity(callIntent)
        }
        adapter.notifyDataSetChanged()
    }
}


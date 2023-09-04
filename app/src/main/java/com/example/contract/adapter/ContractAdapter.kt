package com.example.contract.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.contract.fragment.ContactFragment
import com.example.contract.fragment.MyPageFragment

class ContractAdapter(fragmentActivity: FragmentActivity) : FragmentStateAdapter(fragmentActivity) {
    override fun getItemCount(): Int {
        return 2 // 예: 2개의 페이지
    }

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> ContactFragment()
            1 -> MyPageFragment()
            else -> throw IllegalArgumentException("Invalid position")
        }
    }
}

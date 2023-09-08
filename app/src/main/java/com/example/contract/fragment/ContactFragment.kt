package com.example.contract.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.contract.R
import com.example.contract.adapter.ListAdapter
import com.example.contract.databinding.FragmentContractBinding
import com.example.contract.sampledata.ContactManager


class ContactFragment : Fragment() {

    private lateinit var binding: FragmentContractBinding

    // Fragment의 UI를 생성하고 반환합니다.
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Fragment의 레이아웃을 인플레이트하고 반환합니다.
        binding = FragmentContractBinding.inflate(inflater, container, false)
        val root = binding.root

        val contactRv = binding.root.findViewById<RecyclerView>(R.id.contactRv)
        val listAdapter = ListAdapter(ContactManager.getContact(), requireContext())
        contactRv.adapter = listAdapter
        contactRv.layoutManager = LinearLayoutManager(context)


        val decoration = ListAdapter.AddressAdapterDecoration()
        binding.contactRv.addItemDecoration(decoration)

        // 스와이프 기능...
        val itemTouchHelperCallback = CustomItemTouchHelper(requireContext(), listAdapter)
        val itemTouchHelper = ItemTouchHelper(itemTouchHelperCallback)
        itemTouchHelper.attachToRecyclerView(contactRv)

        return root
    }
}
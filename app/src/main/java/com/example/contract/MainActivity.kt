package com.example.contract

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.viewpager2.widget.ViewPager2
import com.example.contract.adapter.ContractAdapter
import com.example.contract.databinding.ActivityMainBinding
import com.google.android.material.tabs.TabLayoutMediator

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initView()
    }

    private fun initView() {
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val tabLayout = binding.tabLayout
        val viewPager2 = binding.viewPager

        // 어댑터 생성
        val adapter = ContractAdapter(this)

        // ViewPager2에 어댑터 설정
        viewPager2.adapter = adapter

        // TabLayoutMediator를 사용하여 ViewPager2와 TabLayout을 연결
        TabLayoutMediator(tabLayout, viewPager2) { tab, position ->
            when (position) {
                0 -> {
                    tab.text = "Contact"
                }

                1 -> {
                    tab.text = "My Page"
                }
            }
        }.attach()

        // ViewPager2의 페이지 변경 리스너 설정
        viewPager2.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                when (position) {
                    0 -> {
                        binding.fabButton.show()
                    }

                    1 -> {
                        binding.fabButton.hide()
                    }
                }
            }
        })
    }
}
package com.example.contract

import android.os.Bundle
import android.view.View
import android.widget.PopupMenu
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager2.widget.ViewPager2
import com.example.contract.adapter.ContractAdapter
import com.example.contract.databinding.ActivityMainBinding
import com.example.contract.fragment.DialogFragment
import com.example.contract.fragment.ExitDialogFragment
import com.google.android.material.tabs.TabLayoutMediator

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var viewPager2: ViewPager2
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initView()
        fabButton()
    }

    private fun initView() {
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.toolbar)
        supportActionBar?.title = null

        val tabLayout = binding.tabLayout
        viewPager2 = binding.viewPager

        // 어댑터 생성
        val adapter = ContractAdapter(this)

        // ViewPager2에 어댑터 설정
        viewPager2.adapter = adapter

        tabLayout.tabRippleColor = null

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
                        binding.menuButton.visibility = View.VISIBLE
                        binding.backButton.setOnClickListener {
                            onBackPressed()
                        }
                        binding.menuButton.setOnClickListener { view ->
                            showPopupMenu(view)
                        }
                    }

                    1 -> {
                        binding.fabButton.hide()
                        binding.menuButton.visibility = View.INVISIBLE
                    }
                }
            }
        })
    }

    private fun fabButton() {
        binding.fabButton.setOnClickListener {
            DialogFragment().show(
                supportFragmentManager, "DialogFrag"
            )
        }
    }

    override fun onBackPressed() {
        if (viewPager2.currentItem == 0) {
            val dialogFragment = ExitDialogFragment()
            dialogFragment.show(supportFragmentManager, "ExitDialogFragment")
        } else {
            viewPager2.currentItem = 0
        }
    }

    private fun showPopupMenu(view: View) {
        val popupMenu = PopupMenu(this, view)
        popupMenu.menuInflater.inflate(R.menu.menu_list_type, popupMenu.menu)

        popupMenu.setOnMenuItemClickListener { menuItem ->
            when (menuItem.itemId) {
                R.id.menu_grid -> {

                    true
                }

                R.id.menu_list -> {

                    true
                }

                else -> false
            }
        }
        popupMenu.show()
    }
}
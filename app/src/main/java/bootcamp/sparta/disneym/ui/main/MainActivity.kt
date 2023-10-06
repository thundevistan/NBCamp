package bootcamp.sparta.disneym.ui.main

import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import androidx.activity.viewModels

import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.res.ResourcesCompat
import bootcamp.sparta.disneym.R
import bootcamp.sparta.disneym.databinding.ActivityMainBinding
import bootcamp.sparta.disneym.domain.model.DetailModel
import bootcamp.sparta.disneym.ui.detail.DetailActivity
import bootcamp.sparta.disneym.ui.viewmodel.MainSharedViewModel
import com.google.android.material.tabs.TabLayoutMediator

class MainActivity : AppCompatActivity() {
    companion object {
        fun newIntentForSearch(
            context: Context,
            item: DetailModel
        ): Intent = Intent(context, MainActivity::class.java).apply {
            putExtra(DetailActivity.EXTRA_DETAIL, item)
        }
    }

    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }
    private val viewPagerAdapter by lazy { ViewPagerAdapter(this) }
    private val sharedViewModel: MainSharedViewModel by viewModels()

    private val detailItem by lazy {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            intent.getParcelableExtra(DetailActivity.EXTRA_DETAIL, DetailModel::class.java)
        } else {
            intent.getParcelableExtra(DetailActivity.EXTRA_DETAIL)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        initView()
    }

    private fun initView() = with(binding) {
        // 디자인 가이드라인에서는 사용자의 터치 스크롤로 인한 화면 변경을 금지하는 것을 추천하여 추가 했습니다
        viewpager.isUserInputEnabled = false
        viewpager.offscreenPageLimit = viewPagerAdapter.itemCount

        // viewPager <-> tabLayout
        binding.viewpager.adapter = viewPagerAdapter
        TabLayoutMediator(tabLayout, viewpager) { tab, position ->
            when (position) {
                0 -> tab.icon =
                    ResourcesCompat.getDrawable(resources, R.drawable.ic_home, theme)

                1 -> tab.icon =
                    ResourcesCompat.getDrawable(resources, R.drawable.ic_search, theme)

                2 -> tab.icon =
                    ResourcesCompat.getDrawable(resources, R.drawable.ic_download, theme)

                3 -> tab.icon =
                    ResourcesCompat.getDrawable(resources, R.drawable.ic_user, theme)
            }
        }.attach()

        // Detail Fragment의 Bookmark 클릭이벤트
        detailItem?.let {
            sharedViewModel.updateHomeItems(it)
        }
    }

    // 뒤로가기 막기
    override fun onBackPressed() {
//        super.onBackPressed()
    }
}

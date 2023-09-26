package bootcamp.sparta.disneym.ui

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import bootcamp.sparta.disneym.ui.bookmark.BookmarkFragment
import bootcamp.sparta.disneym.ui.detail.DetailFragment
import bootcamp.sparta.disneym.ui.home.HomeFragment
import bootcamp.sparta.disneym.ui.mypage.MyPageFragment
import bootcamp.sparta.disneym.ui.search.SearchFragment

/**
 * 4개의 주요 Fragment 를 연결하는 viewpager 어댑터
 */
class ViewPagerAdapter(activity: FragmentActivity) : FragmentStateAdapter(activity) {
	val fragments: List<Fragment>

	init {
		fragments = listOf(HomeFragment(), SearchFragment(), BookmarkFragment(), DetailFragment())
	}

	override fun getItemCount(): Int {
		return fragments.size
	}

	override fun createFragment(position: Int): Fragment {
		return fragments[position]
	}
}
package bootcamp.sparta.disneym.ui

import android.content.Context
import android.content.Intent
import android.os.Bundle

import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.res.ResourcesCompat
import androidx.viewpager2.widget.ViewPager2
import bootcamp.sparta.disneym.R
import bootcamp.sparta.disneym.databinding.ActivityMainBinding
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

class MainActivity : AppCompatActivity() {
	companion object {
		fun newIntent(context: Context): Intent = Intent(context, MainActivity::class.java)
	}

	private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView(binding.root)

		initView()

		// viewPager <-> tabLayout
		tabLayoutMediator(binding.tabLayout, binding.viewpager)
	}

	private fun initView() = with(binding) {

	}

	// viewPager <-> tabLayout 연결하는 함수
	private fun tabLayoutMediator(tabLayout: TabLayout, viewPager: ViewPager2) {
		binding.viewpager.adapter = ViewPagerAdapter(this)


		TabLayoutMediator(tabLayout, viewPager) { tab, position ->
			when (position) {
				0 -> tab.icon =
					ResourcesCompat.getDrawable(this.resources, R.drawable.ic_home, theme)


				1 -> tab.icon =
					ResourcesCompat.getDrawable(this.resources, R.drawable.ic_search, theme)


				2 -> tab.icon =
					ResourcesCompat.getDrawable(this.resources, R.drawable.ic_download, theme)


				3 -> tab.icon =
					ResourcesCompat.getDrawable(this.resources, R.drawable.ic_search, theme)
			}
		}.attach()
	}
}
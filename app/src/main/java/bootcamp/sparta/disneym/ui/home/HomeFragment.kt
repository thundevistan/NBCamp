package bootcamp.sparta.disneym.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.viewpager2.widget.CompositePageTransformer
import androidx.viewpager2.widget.ViewPager2
import bootcamp.sparta.disneym.databinding.FragmentHomeBinding
import kotlin.math.abs

class HomeFragment : Fragment() {

	private lateinit var binding: FragmentHomeBinding

	override fun onCreateView(
		inflater: LayoutInflater, container: ViewGroup?,
		savedInstanceState: Bundle?
	): View {
		binding = FragmentHomeBinding.inflate(inflater, container, false)
		return binding.root
	}

	override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
		super.onViewCreated(view, savedInstanceState)

		upperViewPager()
		lowerRecycler()
	}

	// 위쪽 viewpager 출력
	private fun upperViewPager() {
		val adapter = HomeViewPagerAdapter(requireActivity())
		val transform = CompositePageTransformer()

		binding.mainUpperViewPager.apply {
			this.adapter = adapter
			offscreenPageLimit = 3
			getChildAt(0).overScrollMode = View.OVER_SCROLL_NEVER
			setPageTransformer(transform)
		}

		transform.apply {
			addTransformer(ViewPager2.PageTransformer { view: View, fl: Float ->
				val v = 1 - abs(fl)
				view.scaleY = 0.8f + v * 0.2f
			})
		}
	}

	private fun lowerRecycler() {
		val adapter = HomeRecyclerAdapter(requireActivity())

		binding.mainLowerRecycler.apply {
			this.adapter = adapter
			layoutManager = LinearLayoutManager(requireActivity())
			setHasFixedSize(true)
		}
	}
}
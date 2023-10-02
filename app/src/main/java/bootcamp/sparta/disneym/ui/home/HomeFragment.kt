package bootcamp.sparta.disneym.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.viewpager2.widget.CompositePageTransformer
import androidx.viewpager2.widget.ViewPager2
import bootcamp.sparta.disneym.databinding.FragmentHomeBinding
import bootcamp.sparta.disneym.viewmodel.Home.HomeViewModel
import kotlin.math.abs

/**
 * Copyright 2023 김민준, Inc.
 *
 * 앱 실행 시 가장 먼저 보여지는 프래그먼트 입니다.
 * 최상단부터 viewpager -> 카테고리 버튼 -> scrollView 순서의 구조입니다.
 */
class HomeFragment : Fragment() {

	private var _binding: FragmentHomeBinding? = null
	private val binding get() = _binding!!
	private val viewModel: HomeViewModel by viewModels()

	override fun onCreateView(
		inflater: LayoutInflater, container: ViewGroup?,
		savedInstanceState: Bundle?
	): View {
		_binding = FragmentHomeBinding.inflate(inflater, container, false)
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
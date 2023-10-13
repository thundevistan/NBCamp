package bootcamp.sparta.disneym.ui.home

import android.app.Activity
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.CompositePageTransformer
import androidx.viewpager2.widget.MarginPageTransformer
import bootcamp.sparta.disneym.databinding.FragmentHomeBinding
import bootcamp.sparta.disneym.domain.model.DetailModel
import bootcamp.sparta.disneym.domain.model.toDetailModel
import bootcamp.sparta.disneym.ui.detail.DetailActivity
import bootcamp.sparta.disneym.ui.home.Category.ENTERTAINMENT
import bootcamp.sparta.disneym.ui.home.Category.FILM
import bootcamp.sparta.disneym.ui.home.Category.GAMING
import bootcamp.sparta.disneym.ui.home.Category.MUSIC
import bootcamp.sparta.disneym.ui.home.Category.PEOPLE
import bootcamp.sparta.disneym.ui.home.Category.PETS
import bootcamp.sparta.disneym.ui.home.Category.POPULAR
import bootcamp.sparta.disneym.ui.viewmodel.MainSharedViewModel
import bootcamp.sparta.disneym.ui.viewmodel.home.HomeViewModel
import bootcamp.sparta.disneym.ui.viewmodel.home.HomeViewModelFactory
import kotlin.math.abs

/**
 * Copyright 2023 김민준, Inc.
 *
 * 앱 실행 시 가장 먼저 보여지는 프래그먼트 입니다.
 * 최상단부터 viewpager -> 카테고리 버튼 -> scrollView 순서의 구조입니다.
 */

enum class Category {
	POPULAR,
	FILM,
	PETS,
	MUSIC,
	PEOPLE,
	GAMING,
	ENTERTAINMENT
}

class HomeFragment : Fragment() {

	private var _binding: FragmentHomeBinding? = null
	private val binding get() = _binding!!

	private val detailLauncher =
		registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
			if (result.resultCode == Activity.RESULT_OK) {
				val item = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
					result.data?.getParcelableExtra(
						DetailActivity.EXTRA_DETAIL,
						DetailModel::class.java
					)
				} else {
					result.data?.getParcelableExtra(DetailActivity.EXTRA_DETAIL)
				}

				sharedViewModel.updateHomeItems(item)
			}
		}

	private val rvAdapter by lazy {
		HomeRecyclerAdapter(
			onItemClicked = { item ->
				detailLauncher.launch(
					DetailActivity.newIntent(
						requireContext(),
						item.toDetailModel()
					)
				)
			}
		)
	}
	private val vpAdapter by lazy {
		HomeViewPagerAdapter(
			onItemClicked = { item ->
				detailLauncher.launch(
					DetailActivity.newIntent(
						requireContext(),
						item.toDetailModel()
					)
				)
			})
	}

	private val viewModel: HomeViewModel by viewModels {
		HomeViewModelFactory()
	}

	private val sharedViewModel: MainSharedViewModel by activityViewModels()

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
		initView()
		initViewModel()

		// 스크롤 Up/Down 판별
		binding.mainLowerRecycler.addOnScrollListener(object : RecyclerView.OnScrollListener() {
			private var isScrolledDown = false

			override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
				super.onScrollStateChanged(recyclerView, newState)

				if (!recyclerView.canScrollVertically(-1) && !isScrolledDown) {
					binding.apply {
						mainMiddleContainer.visibility = View.VISIBLE
						mainUpperViewPager.visibility = View.VISIBLE
					}
				}
				if (newState == RecyclerView.SCROLL_STATE_DRAGGING && isScrolledDown) {
					binding.apply {
						mainMiddleContainer.visibility = View.GONE
						mainUpperViewPager.visibility = View.GONE
					}
				}
			}

			override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
				super.onScrolled(recyclerView, dx, dy)

				isScrolledDown = dy > 0
				Log.d("isScrolledDown", dy.toString())
			}
		})
	}

	private fun initView() = with(binding) {
		viewModel.getVideoForCategory(FILM)

		mainFilmBtn.setOnClickListener {
			viewModel.getVideoForCategory(FILM)
		}

		mainPetsBtn.setOnClickListener {
			viewModel.getVideoForCategory(PETS)
		}

		mainMusicBtn.setOnClickListener {
			viewModel.getVideoForCategory(MUSIC)
		}

		mainPeopleBtn.setOnClickListener {
			viewModel.getVideoForCategory(PEOPLE)
		}

		mainGamingBtn.setOnClickListener {
			viewModel.getVideoForCategory(GAMING)
		}

		mainEntertainmentBtn.setOnClickListener {
			viewModel.getVideoForCategory(ENTERTAINMENT)
		}
	}

	private fun initViewModel() {
		binding.mainLowerRecycler.adapter = rvAdapter

		with(viewModel) {
			list.observe(viewLifecycleOwner) { list ->
				rvAdapter.submitList(list)
			}
			popular.observe(viewLifecycleOwner) { list ->
				vpAdapter.submitList(list)
			}
		}
	}

	// 최상단 viewpager 출력
	private fun upperViewPager() {
		val transform = CompositePageTransformer()

		binding.mainUpperViewPager.apply {
			this.adapter = vpAdapter
			offscreenPageLimit = 3
			getChildAt(0).overScrollMode = View.OVER_SCROLL_NEVER
			setPageTransformer(transform)
		}

		transform.apply {
			addTransformer(MarginPageTransformer(8))
			addTransformer { view: View, fl: Float ->
				val v = 1 - abs(fl)
				view.scaleY = 0.8f + v * 0.2f
			}
		}

		viewModel.getVideoForCategory(POPULAR)
	}
}
package bootcamp.sparta.disneym.ui.home

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.viewModelScope
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.CompositePageTransformer
import androidx.viewpager2.widget.MarginPageTransformer
import bootcamp.sparta.disneym.databinding.FragmentHomeBinding
import bootcamp.sparta.disneym.model.toDetailModel
import bootcamp.sparta.disneym.repository.MainRepository
import bootcamp.sparta.disneym.ui.detail.DetailActivity
import bootcamp.sparta.disneym.ui.detail.DetailFragment
import bootcamp.sparta.disneym.ui.home.Category.*
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
        HomeViewPagerAdapter()
    }

    private val repository by lazy {
        MainRepository()
    }
    private val viewModel: HomeViewModel by viewModels {
        HomeViewModelFactory(repository)
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

        binding.mainLowerRecycler.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                if (!recyclerView.canScrollVertically(-1)) {
                    binding.apply {
                        mainUpperViewPager.visibility = View.VISIBLE
                        mainMiddleContainer.visibility = View.VISIBLE
                    }
                }
//				else () {
//					binding.apply {
//						mainUpperViewPager.visibility = View.VISIBLE
//						mainMiddleContainer.visibility = View.VISIBLE
//					}
//				}
            }
        })
    }

    private fun initView() = with(binding) {
        mainFilmBtn.setOnClickListener {
            viewVisibility()
            viewModel.getVideoForCategory(FILM)
//                categoryClick(FILM)
        }

        mainPetsBtn.setOnClickListener {
            viewVisibility()
            viewModel.getVideoForCategory(PETS)
//                categoryClick(PETS)
        }

        mainMusicBtn.setOnClickListener {
            viewVisibility()
            viewModel.getVideoForCategory(MUSIC)
//                categoryClick(MUSIC)
        }

        mainPeopleBtn.setOnClickListener {
            viewVisibility()
            viewModel.getVideoForCategory(PEOPLE)
//                categoryClick(PEOPLE)
        }

        mainGamingBtn.setOnClickListener {
            viewVisibility()
            viewModel.getVideoForCategory(GAMING)
//                categoryClick(GAMING)
        }

        mainEntertainmentBtn.setOnClickListener {
            viewVisibility()
            viewModel.getVideoForCategory(ENTERTAINMENT)
//                categoryClick(ENTERTAINMENT)
        }
    }

    private fun initViewModel() {
        with(viewModel) {
            list.observe(viewLifecycleOwner) { list ->
                rvAdapter.submitList(list)
            }
            popular.observe(viewLifecycleOwner) {list ->
                vpAdapter.setData(list)
            }
        }

        with(sharedViewModel) {
            homeEvent.observe(viewLifecycleOwner, Observer {
                // sharedViewModel homeEvent(LiveData)의 값이 변했을 때 이벤트
                // 매핑을 통해 HomeModel의 형태를 가진 Item이 "it"에 들어옴
                // 기존의 Home에 있는 List와 비교해 동일한 아이템을 찾아 isbookmarked 값 변경

            })
        }

        // 카테고리 버튼
        binding.mainLowerRecycler.adapter = rvAdapter

        // 아이템 클릭
//        rvAdapter.itemClick = object : HomeRecyclerAdapter.ItemClick {
//            override fun onClick(view: View, position: Int) {
//                val intent = Intent(requireActivity(), DetailFragment::class.java)
//                startActivity(intent)
//            }
//
//        }

        vpAdapter.itemClick = object : HomeViewPagerAdapter.ItemClick {
            override fun onClick(view: View, position: Int) {
                val intent = Intent(requireActivity(), DetailFragment::class.java)
                startActivity(intent)
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
//        viewModel.getPopular()
//        viewModel.popular.observe(viewLifecycleOwner) {
//            if (!it.isNullOrEmpty()) {
//                vpAdapter.setData(it)
//            }
//        }
    }

    // 카테고리 버튼 이벤트 처리
//    private fun categoryClick(category: Category) = when (category) {
//        Category.FILM -> {
//            viewModel.getFilm()
//            viewModel.films.observe(viewLifecycleOwner) {
//                if (!it.isNullOrEmpty()) {
//                    rvAdapter.setData(it)
//                }
//            }
//        }
//
//        Category.PETS -> {
//            viewModel.getPets()
//            viewModel.pets.observe(viewLifecycleOwner) {
//                if (!it.isNullOrEmpty()) {
//                    rvAdapter.setData(it)
//                }
//            }
//        }
//
//        Category.MUSIC -> {
//            viewModel.getMusic()
//            viewModel.music.observe(viewLifecycleOwner) {
//                if (!it.isNullOrEmpty()) {
//                    rvAdapter.setData(it)
//                }
//            }
//        }
//
//        Category.PEOPLE -> {
//            viewModel.getPeople()
//            viewModel.people.observe(viewLifecycleOwner) {
//                if (!it.isNullOrEmpty()) {
//                    rvAdapter.setData(it)
//                }
//            }
//        }
//
//        Category.GAMING -> {
//            viewModel.getGaming()
//            viewModel.gaming.observe(viewLifecycleOwner) {
//                if (!it.isNullOrEmpty()) {
//                    rvAdapter.setData(it)
//                }
//            }
//        }
//
//        Category.ENTERTAINMENT -> {
//            viewModel.getEntertainment()
//            viewModel.entertainment.observe(viewLifecycleOwner) {
//                if (!it.isNullOrEmpty()) {
//                    rvAdapter.setData(it)
//                }
//            }
//        }
//    }

    private fun viewVisibility() {
        binding.apply {
            mainUpperViewPager.visibility = View.GONE
            mainMiddleContainer.visibility = View.GONE
        }
    }
}
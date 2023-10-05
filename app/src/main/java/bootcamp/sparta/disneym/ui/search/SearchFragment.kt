package bootcamp.sparta.disneym.ui.search

import android.app.Activity
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.SearchView
import androidx.activity.result.contract.ActivityResultContracts
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import bootcamp.sparta.disneym.R
import bootcamp.sparta.disneym.data.datasource.remote.API_KEY
import bootcamp.sparta.disneym.databinding.FragmentSearchBinding
import bootcamp.sparta.disneym.model.DetailModel
import bootcamp.sparta.disneym.repository.MainRepository
import bootcamp.sparta.disneym.repository.SearchRepositoryImpl
import bootcamp.sparta.disneym.ui.detail.DetailActivity
import bootcamp.sparta.disneym.ui.detail.DetailFragment
import bootcamp.sparta.disneym.ui.main.MainActivity
import bootcamp.sparta.disneym.ui.viewmodel.MainSharedEventForDetail
import bootcamp.sparta.disneym.ui.viewmodel.MainSharedViewModel
import bootcamp.sparta.disneym.ui.viewmodel.search.SearchViewModel
import bootcamp.sparta.disneym.ui.viewmodel.search.SearchViewModelFactory

/*
* Copyright 2023 김현준, Inc.
*
* 검색 프래그먼트(SearchView, MainView)
* 추가 예정
*
* */

class SearchFragment : Fragment() {
    companion object {
        const val BUNDLE_DETAIL = "bundle_detail"
    }

    private var _binding: FragmentSearchBinding? = null
    private val binding get() = _binding!!

    private val mainAdapter by lazy {
        SearchMainAdapter()
    }
    private val viewAdapter by lazy {
        SearchViewAdapter(
            onItemClick = { item ->
                sharedViewModel.updateDetailItem(item)
            }
        )
    }

    private val repository: SearchRepository by lazy {
        SearchRepositoryImpl()
    }

    private val viewModel: SearchViewModel by viewModels { SearchViewModelFactory(repository) }
    private val sharedViewModel: MainSharedViewModel by activityViewModels()

    private val detailLauncher =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == Activity.RESULT_OK) {
                val item = if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                    result.data?.getParcelableExtra(DetailActivity.EXTRA_DETAIL, DetailModel::class.java)
                } else {
                    result.data?.getParcelableExtra(DetailActivity.EXTRA_DETAIL)
                }

                sharedViewModel.updateHomeItems(item)
            }
        }

    private lateinit var gridManager: StaggeredGridLayoutManager
    private var searchViewVisible = false

    private val part = "snippet"
    private val key = API_KEY.AUTH_KEY
    private val maxResults = 50
    private val regionCode = "KR"
    private val chart = "mostPopular"


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSearchBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.searchViewRecycler.adapter = viewAdapter

        initView()
        initViewModel()
    }

    private fun initView() = with(binding) {
        searchViewRecycler.adapter = viewAdapter

        showMainView()
        clickBtn()

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener,
            androidx.appcompat.widget.SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                if (query != null && query.isNotEmpty()) {
                    performSearch(query)
                    val imm = context?.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
                    imm.hideSoftInputFromWindow(searchView.windowToken, 0)
                }

                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                if (newText != null) {
                    if (newText.isNotEmpty()) {
                        showViewView()

                    } else {
                        showMainView()
                    }
                }
                return true
            }
        })

        searchView.setOnCloseListener {
            if (searchViewVisible) {
                showMainView()
                searchViewVisible = false
            }
            false
        }
    }

    private fun showMainView() {
        binding.searchMainRecycler.visibility = View.VISIBLE
        binding.searchViewRecycler.visibility = View.INVISIBLE
        binding.test123.visibility = View.VISIBLE

        gridManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
        binding.searchMainRecycler.layoutManager = gridManager

        binding.searchMainRecycler.adapter = mainAdapter

        binding.searchMainRecycler.itemAnimator = null

        searchVideo(0)
    }

    private fun showViewView() {

        binding.searchMainRecycler.visibility = View.INVISIBLE
        binding.searchViewRecycler.visibility = View.VISIBLE
        binding.test123.visibility = View.INVISIBLE


        val linearLayout = LinearLayoutManager(requireContext())
        binding.searchViewRecycler.layoutManager = linearLayout

        binding.searchViewRecycler.itemAnimator = null
    }

    private fun initViewModel() = with(viewModel) {
//        sharedViewModel.updateDetailItem(updateModel = SearchModel(  ))

//        viewAdapter.setOnItemClickListener { searchModel ->
//            sharedViewModel.updateDetailItem(searchModel)
//            Log.d("SearchFragment", "updateDetailItem 호출 - searchModel: $searchModel")
//        }

        // detailEvent를 관찰하여 디테일 아이템이 업데이트될 때 화면을 열기
        searchItem.observe(viewLifecycleOwner) { searchItem ->
            viewAdapter.submitList(searchItem)
        }

        getVideo.observe(viewLifecycleOwner) { getVideoItem ->
            mainAdapter.submitList(getVideoItem)
        }

        sharedViewModel.detailEvent.observe(viewLifecycleOwner, Observer { event ->
            when (event) {
                is MainSharedEventForDetail.UpdateDetailItem -> {
                    detailLauncher.launch(
                        DetailActivity.newIntent(
                            requireContext(),
                            event.item
                        )
                    )
                }

                else -> Unit
            }
        })
    }

    private fun performSearch(query: String) {
        val q = query
        Log.d("SearchFragment", "performSearch() 호출 - 검색어: $q")

        viewModel.getSearchItems(part, q, maxResults, key)

        viewModel.searchItem.observe(viewLifecycleOwner, Observer {
            viewAdapter.submitList(it)
            Log.d("Search", "$it")
        })
    }

    private fun searchVideo(videoCategoryId: Int) {

        viewModel.getVideoItems(part, chart, key, maxResults, videoCategoryId, regionCode)

        viewModel.getVideo.observe(viewLifecycleOwner, Observer {
            mainAdapter.submitList(it)
        })
    }

    private fun clickBtn() = with(binding) {
        searchPopularBtn.setOnClickListener { searchVideo(0) }
        searchSportsBtn.setOnClickListener { searchVideo(17) }
        searchShortsBtn.setOnClickListener { searchVideo(1) }
        searchNewsBtn.setOnClickListener { searchVideo(25) }
        searchComedyBtn.setOnClickListener { searchVideo(23) }
        searchPetsBtn.setOnClickListener { searchVideo(15) }
    }

    override fun onDestroy() {
        _binding = null
        super.onDestroy()
    }
}
package bootcamp.sparta.disneym.ui.search

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import bootcamp.sparta.disneym.R
import bootcamp.sparta.disneym.data.datasource.remote.API_KEY
import bootcamp.sparta.disneym.databinding.FragmentSearchBinding
import bootcamp.sparta.disneym.model.DetailModel
import bootcamp.sparta.disneym.model.SearchModel
import bootcamp.sparta.disneym.model.toDetailModel
import bootcamp.sparta.disneym.repository.MainRepository
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

    private lateinit var binding: FragmentSearchBinding
    private lateinit var mainAdapter: SearchMainAdapter
    private lateinit var viewAdapter: SearchViewAdapter
    private lateinit var gridManager: StaggeredGridLayoutManager
    private var searchViewVisible = false

    private lateinit var viewModel: SearchViewModel
    private val sharedViewModel: MainSharedViewModel by activityViewModels()

    private val part = "snippet"
    private val key = API_KEY.AUTH_KEY
    private val maxResults = 50
    private val regionCode = "KR"
    private val chart = "mostPopular"


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSearchBinding.inflate(layoutInflater)

        viewModel = ViewModelProvider(
            this,
            SearchViewModelFactory(MainRepository())
        ).get(SearchViewModel::class.java)

//        sharedViewModel.updateDetailItem(updateModel = SearchModel(  ))
        viewAdapter = SearchViewAdapter(sharedViewModel)

        viewAdapter.setOnItemClickListener { searchModel ->
            sharedViewModel.updateDetailItem(searchModel)
            Log.d("SearchFragment", "updateDetailItem 호출 - searchModel: $searchModel")
        }
        binding.searchViewRecycler.adapter = viewAdapter

        // detailEvent를 관찰하여 디테일 아이템이 업데이트될 때 화면을 열기
        sharedViewModel.detailEvent.observe(viewLifecycleOwner, Observer { event ->
            when (event) {
                is MainSharedEventForDetail.UpdateDetailItem -> {
                    // 디테일 페이지 열기
                    showDetailFragment(event.item)
                    Log.d("SearchFragment", "showDetailFragment 호출 - item: ${event.item}")
                }
                else -> Unit
            }
        })


        showMainView()
        clickBtn()

        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener,
            androidx.appcompat.widget.SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                if (query != null && query.isNotEmpty()) {
                    performSearch(query)
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

        binding.searchView.setOnCloseListener {
            if (searchViewVisible) {
                showMainView()
                searchViewVisible = false
            }
            false
        }

        return binding.root
    }

    private fun showMainView() {
        binding.searchMainRecycler.visibility = View.VISIBLE
        binding.searchViewRecycler.visibility = View.INVISIBLE
        binding.test123.visibility = View.VISIBLE

        gridManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
        binding.searchMainRecycler.layoutManager = gridManager

        mainAdapter = SearchMainAdapter()
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

    private fun clickBtn()= with(binding) {
        searchPopularBtn.setOnClickListener { searchVideo(0) }
        searchSportsBtn.setOnClickListener { searchVideo(17) }
        searchShortsBtn.setOnClickListener { searchVideo(1) }
        searchNewsBtn.setOnClickListener { searchVideo(25) }
        searchComedyBtn.setOnClickListener { searchVideo(23) }
        searchPetsBtn.setOnClickListener { searchVideo(15) }
    }

    private fun showDetailFragment(detailModel: DetailModel) {
        Log.d("SearchFragment", "showDetailFragment 호출 - detailModel: $detailModel")
        val bundle = Bundle()
        bundle.putParcelable(BUNDLE_DETAIL, detailModel)

        val detailFragment = (requireActivity() as MainActivity).viewPagerAdapter.getDeatilFragment()
        detailFragment.arguments = bundle

        // Fragment 전환
        val transaction = requireActivity().supportFragmentManager.beginTransaction()
        transaction.replace(R.id.detail_layout, detailFragment)
        transaction.addToBackStack(null)
        transaction.commit()

        Log.d("SearchFragment", "디테일 프래그먼트로 전환 완료")
    }
}
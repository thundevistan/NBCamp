package bootcamp.sparta.disneym.ui.search

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import bootcamp.sparta.disneym.data.datasource.remote.API_KEY
import bootcamp.sparta.disneym.databinding.FragmentSearchBinding
import bootcamp.sparta.disneym.repository.MainRepository
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

    private lateinit var binding: FragmentSearchBinding
    private lateinit var mainAdapter: SearchMainAdapter
    private lateinit var viewAdapter: SearchViewAdapter
    private lateinit var gridManager: StaggeredGridLayoutManager
    private var searchViewVisible = false

    private lateinit var viewModel: SearchViewModel

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

        viewAdapter = SearchViewAdapter()
        binding.searchViewRecycler.adapter = viewAdapter

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
}
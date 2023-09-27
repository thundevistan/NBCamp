package bootcamp.sparta.disneym.ui.search

import android.content.res.Resources
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.HorizontalScrollView
import android.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import bootcamp.sparta.disneym.R
import bootcamp.sparta.disneym.databinding.FragmentSearchBinding
import com.bumptech.glide.load.engine.Resource

/*
* Copyright 2023 김현준, Inc.
*
* 검색 프래그먼트(SearchView, MainView)
* 추가 예정
*
* */

class SearchFragment : Fragment() {

	private lateinit var binding: FragmentSearchBinding
	private lateinit var mainAdapter : SearchMainAdapter
	private lateinit var viewAdapter: SearchViewAdapter
	private lateinit var gridManager: StaggeredGridLayoutManager
	private var searchViewVisible = false


	override fun onCreateView(
		inflater: LayoutInflater, container: ViewGroup?,
		savedInstanceState: Bundle?
	): View? {
		binding = FragmentSearchBinding.inflate(layoutInflater)

		showMainView()

//		binding.searchView.setOnClickListener {
//			if (!searchViewVisible) {
//				showViewView()
//				searchViewVisible = true
//			}
//		}
//


		binding.searchView.setOnQueryTextListener(object: SearchView.OnQueryTextListener,
			androidx.appcompat.widget.SearchView.OnQueryTextListener {
			override fun onQueryTextSubmit(query: String?): Boolean {


				return false
			}

			override fun onQueryTextChange(newText: String?): Boolean {
				if (newText != null) {
					if (newText.isNotEmpty()) {
						showViewView()
					} else {
						showMainView()
					}
				}
					//					if (searchViewVisible) {
			//						showMainView()
			//					} else {
			//						showViewView()
			//					}
			//					searchViewVisible = !searchViewVisible
			//				}
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

//		val gridItemDecoration = GridItemDecoration(spanCount = 2, spacing = 16f.fromDptoPx())
//		binding.searchMainRecycler.addItemDecoration(gridItemDecoration)

		val item = listOf(
			SearchTestModel(R.drawable.detail_test),
			SearchTestModel(R.drawable.detail_test),
			SearchTestModel(R.drawable.detail_test),
			SearchTestModel(R.drawable.detail_test)
		)

		mainAdapter = SearchMainAdapter(item.toMutableList())
		binding.searchMainRecycler.adapter = mainAdapter

		val newItems = listOf(
			SearchTestModel(R.drawable.detail_test),
			SearchTestModel(R.drawable.detail_test),
			SearchTestModel(R.drawable.detail_test),
			SearchTestModel(R.drawable.detail_test),
			SearchTestModel(R.drawable.detail_test),
			SearchTestModel(R.drawable.detail_test),
			SearchTestModel(R.drawable.detail_test),
			SearchTestModel(R.drawable.detail_test)
		)

		mainAdapter.set(newItems)

		binding.searchMainRecycler.itemAnimator = null
	}

	private fun showViewView() {

		binding.searchMainRecycler.visibility = View.INVISIBLE
		binding.searchViewRecycler.visibility = View.VISIBLE
		binding.test123.visibility = View.INVISIBLE

		val item = listOf(
			SearchViewTest(R.drawable.detail_test,"디지니 마이너스", "졸리조", "2023.09.27"),
			SearchViewTest(R.drawable.detail_test,"디지니 마이너스마", "졸리조", "2023.09.27"),
			SearchViewTest(R.drawable.detail_test,"디지니 마이너스이", "졸리조", "2023.09.27"),
			SearchViewTest(R.drawable.detail_test,"디지니 마이너스너", "졸리조", "2023.09.27"),
			SearchViewTest(R.drawable.detail_test,"디지니 마이너스스", "졸리조", "2023.09.27")

			)

		viewAdapter = SearchViewAdapter(item)
		binding.searchViewRecycler.adapter = viewAdapter

		val linearLayout = LinearLayoutManager(requireContext())
		binding.searchViewRecycler.layoutManager = linearLayout

		binding.searchViewRecycler.itemAnimator = null
	}

	fun Float.fromDptoPx() : Int =
		(this * Resources.getSystem().displayMetrics.density).toInt()
}
package com.kotdev99.android.week4_mini_assignment

import android.content.res.Configuration
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.kotdev99.android.week4_mini_assignment.sampledata.Data
import com.kotdev99.android.week4_mini_assignment.sampledata.NewsItem

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [TitleFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class TitleFragment : Fragment() {
	// TODO: Rename and change types of parameters
	private var param1: String? = null
	private var param2: String? = null

	private lateinit var recyclerView: RecyclerView
	private lateinit var adapter: RecyclerAdapter
	private lateinit var data: ArrayList<NewsItem>

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		arguments?.let {
			param1 = it.getString(ARG_PARAM1)
			param2 = it.getString(ARG_PARAM2)
		}
	}

	override fun onCreateView(
		inflater: LayoutInflater, container: ViewGroup?,
		savedInstanceState: Bundle?
	): View? {
		// Inflate the layout for this fragment
		return inflater.inflate(R.layout.fragment_title, container, false)
	}

	override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
		super.onViewCreated(view, savedInstanceState)
		data = Data().dataInitialize()

		val layoutManager = LinearLayoutManager(context)
		recyclerView = view.findViewById(R.id.recycler_content)
		recyclerView.layoutManager = layoutManager
		recyclerView.setHasFixedSize(true)
		recyclerView.addItemDecoration(DividerItemDecoration(context, LinearLayoutManager.VERTICAL))
		adapter = RecyclerAdapter(data)
		recyclerView.adapter = adapter
		Log.d("TitleFragment", "onViewCreated()")

		adapter.itemClick = object : RecyclerAdapter.ItemClick {
			override fun onClick(view: View, position: Int) {
				val title = data[position].title
				val article = data[position].article

				val transaction = activity?.supportFragmentManager?.beginTransaction()
				val detailFragment = DetailFragment()
				val bundle = Bundle()
				bundle.putString("title", title)
				bundle.putString("article", article)
				detailFragment.arguments = bundle

				transaction?.apply {
					replace(R.id.fragment_content, detailFragment)
					setReorderingAllowed(true)
					addToBackStack("")
					commit()
				}
			}
		}
	}

	companion object {
		/**
		 * Use this factory method to create a new instance of
		 * this fragment using the provided parameters.
		 *
		 * @param param1 Parameter 1.
		 * @param param2 Parameter 2.
		 * @return A new instance of fragment TitleFragment.
		 */
		// TODO: Rename and change types and number of parameters
		@JvmStatic
		fun newInstance(param1: String, param2: String) =
			TitleFragment().apply {
				arguments = Bundle().apply {
					putString(ARG_PARAM1, param1)
					putString(ARG_PARAM2, param2)
				}
			}
	}
}
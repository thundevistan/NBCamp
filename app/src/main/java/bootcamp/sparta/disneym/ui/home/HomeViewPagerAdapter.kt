package bootcamp.sparta.disneym.ui.home

/**
 * Copyright 2023 김민준, Inc.
 *
 * HomeFragment 상단에 위치한 viewpager의 어댑터
 */
//class HomeViewPagerAdapter(val context: Context) :
//	RecyclerView.Adapter<HomeViewPagerAdapter.ViewHolder>() {
//
//	private val get = Get()
//	private val dataList = get.getter()
//
//	interface ItemClick {
//		fun onClick(view: View, position: Int)
//	}
//
//	var itemClick: ItemClick? = null
//
//	override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
//		return ViewHolder(
//			HomeViewpagerItemBinding.inflate(
//				LayoutInflater.from(parent.context),
//				parent,
//				false
//			)
//		)
//	}
//
//	override fun onBindViewHolder(holder: ViewHolder, position: Int) {
//		holder.bind(dataList[position])
//	}
//
//	override fun getItemCount(): Int {
//		return dataList.size
//	}
//
//	inner class ViewHolder(binding: HomeViewpagerItemBinding) :
//		RecyclerView.ViewHolder(binding.root) {
//
//		private val thumbnail = binding.homeTestImageView
//
//		fun bind(item: HomeModel) {
//			Glide.with(context).load(item.thumbnail).into(thumbnail)
//		}
//	}
//}
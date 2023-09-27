package bootcamp.sparta.disneym.model

import bootcamp.sparta.disneym.R

data class HomeModel(
	val thumbnail: Int
)

class Get {
	fun getter(): MutableList<HomeModel> {
		val data = mutableListOf<HomeModel>()

		for (i in 0..9) {
			data.add(HomeModel(R.drawable.ic_home_sample))
		}
		return data
	}
}


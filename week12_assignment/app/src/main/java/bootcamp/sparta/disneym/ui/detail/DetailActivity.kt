package bootcamp.sparta.disneym.ui.detail

import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import bootcamp.sparta.disneym.R
import bootcamp.sparta.disneym.databinding.ActivityDetailBinding
import bootcamp.sparta.disneym.domain.model.DetailModel
import bootcamp.sparta.disneym.ui.search.SearchFragment

class DetailActivity : AppCompatActivity() {
    companion object {
        const val EXTRA_DETAIL = "extra_detail"
        fun newIntent(
            context: Context,
            item: DetailModel,
            ) : Intent = Intent(context, DetailActivity::class.java).apply {
            putExtra(EXTRA_DETAIL, item)
        }
    }

    private val detailBundle by lazy {
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            intent.getParcelableExtra(EXTRA_DETAIL, DetailModel::class.java)
        }else {
            intent.getParcelableExtra(EXTRA_DETAIL)
        }
    }

    private val binding by lazy {
        ActivityDetailBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        initBundle()
    }

    private fun initBundle() {
        detailBundle?.let {
            supportFragmentManager.beginTransaction().replace(
                R.id.detail_container,
                DetailFragment.newInstance(it),
            ).commit()
        }
    }

    // 뒤로가기 막기
    override fun onBackPressed() {
//        super.onBackPressed()
    }
}
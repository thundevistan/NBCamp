package bootcamp.sparta.disneym.ui.detail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import bootcamp.sparta.disneym.R
import bootcamp.sparta.disneym.databinding.FragmentDetailBinding

class DetailFragment : Fragment() {
    private var _binding : FragmentDetailBinding? = null
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initView()
    }

    private fun initView()=with(binding) {

        detailPlayBtn.setOnClickListener {
            Toast.makeText(context,"* 하단 스크롤 예정 *",Toast.LENGTH_SHORT).show()
        }
        detailBookmarkBtn.setOnClickListener{
            Toast.makeText(context,"* 북마크 추가 구현 예정 *",Toast.LENGTH_SHORT).show()
        }
        detailShareBtn.setOnClickListener {
            Toast.makeText(context,"* 공유 기능 구현 예정 *",Toast.LENGTH_SHORT).show()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}
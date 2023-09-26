package bootcamp.sparta.disneym.ui.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import bootcamp.sparta.disneym.databinding.FragmentDetailBinding
import bootcamp.sparta.disneym.viewmodel.MainSharedViewModel
import bootcamp.sparta.disneym.viewmodel.detail.DetailViewModel
import bootcamp.sparta.disneym.viewmodel.detail.DetailViewModelFactory
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener


class DetailFragment : Fragment() {
    private var _binding: FragmentDetailBinding? = null
    private val binding get() = _binding!!
    private val viewModel: DetailViewModel by viewModels { DetailViewModelFactory() }
    private val sharedViewModel: MainSharedViewModel by activityViewModels()

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
        initViewModel()
        initPlayer()
    }

    private fun initPlayer() = with(binding) {
        detailPlayer.addYouTubePlayerListener(object :
            AbstractYouTubePlayerListener() {
            override fun onReady(youTubePlayer: YouTubePlayer) {
                super.onReady(youTubePlayer)
                // https://www.youtube.com/watch?v=KhEAe2_T-4c&t=4652s 링크 -> ID값 [KhEAe2_T-4c]
                val videoId = "KhEAe2_T-4c"
                youTubePlayer.loadVideo(videoId, 0f)
            }
        })
    }

    private fun initViewModel() {
        /*
        * 추민수
        * viewModel 상의 LiveData의 변경을 관찰해 값이 변하는 경우 Event 처리
        */
        with(viewModel) {
            list.observe(viewLifecycleOwner, Observer {
                // list 변경 감지 시 처리 추가 예정
            })
        }
        with(sharedViewModel) {
            detailEvent.observe(viewLifecycleOwner, Observer {
                // sharedViewModel 상의 데이터 변경 시 화면에 값을 뿌려줄 예정
            })
        }
    }

    private fun initView() = with(binding) {

        detailPlayBtn.setOnClickListener {
            detailScrollview.smoothScrollTo(0,detailScrollview.bottom)// 하단 스크롤
        }
        detailBookmarkBtn.setOnClickListener {
            Toast.makeText(context, "* 북마크 추가 구현 예정 *", Toast.LENGTH_SHORT).show()
        }
        detailShareBtn.setOnClickListener {
            Toast.makeText(context, "* 공유 기능 구현 예정 *", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}
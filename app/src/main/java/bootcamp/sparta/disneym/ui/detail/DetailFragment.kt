package bootcamp.sparta.disneym.ui.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import bootcamp.sparta.disneym.databinding.FragmentDetailBinding
import bootcamp.sparta.disneym.model.DetailModel
import bootcamp.sparta.disneym.util.Util
import bootcamp.sparta.disneym.ui.viewmodel.MainSharedEventForDetail
import bootcamp.sparta.disneym.ui.viewmodel.MainSharedViewModel
import bootcamp.sparta.disneym.ui.viewmodel.detail.DetailViewModel
import bootcamp.sparta.disneym.ui.viewmodel.detail.DetailViewModelFactory
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener

/*
* 추민수
* 다른 Page에서 Item을 클릭 시 보여지는 Fragment입니다.
* 하나의 Item의 모든 세부정보를 확인할 수 있고
* MediaPlayer 기능을 사용해 영상을 바로 시청할 수 있습니다.
* 북마크 추가 삭제 버튼을 제공하고 공유기능을 제공합니다.
*/
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

    /*
     * 추민수
     * Youtube Video Player Library 사용
     * https://github.com/PierfrancescoSoffritti/android-youtube-player
     */
    private fun initPlayer() = with(binding) {
        detailPlayer.addYouTubePlayerListener(object :
            AbstractYouTubePlayerListener() {
            override fun onReady(youTubePlayer: YouTubePlayer) {
                super.onReady(youTubePlayer)
                // https://www.youtube.com/watch?v=KhEAe2_T-4c&t=4652s 링크 -> ID값 [KhEAe2_T-4c]
                // https://www.youtube.com/watch?v=42fmMP81EvA&t=2296s -> 42fmMP81EvA
                // 나중에 변환해주는 함수 추가 예정
                val videoId = "42fmMP81EvA"
                youTubePlayer.cueVideo(videoId, 0f)
            }
        })
    }

    /*
     * 추민수
     * viewModel 상의 LiveData의 변경을 관찰해 값이 변하는 경우 Event 처리
     */
    private fun initViewModel() {

        with(viewModel) {
            // viewModel의 DetailItem이 변했을 경우
            detailItem.observe(viewLifecycleOwner, Observer {
                // bookmarkItem Update
                sharedViewModel.updateBookmarkItems(it)
                // homeItem Update
                sharedViewModel.updateHomeItems(viewModel.detailItem)
            })
        }
        with(sharedViewModel) {
            // sharedViewModel의 데이터가 변경이 일어나면 viewModel의 DetailItem 설정
            detailEvent.observe(viewLifecycleOwner, Observer { event ->
                when (event) {
                    is MainSharedEventForDetail.UpdateDetailItem -> {
                        updateItem(event.item)
                    }
                    else -> Unit
                }
            })
        }
    }

    private fun initView() = with(binding) {

        detailPlayBtn.setOnClickListener {
            detailScrollview.smoothScrollTo(0, detailScrollview.bottom)// 하단 스크롤
        }
        detailBookmarkBtn.setOnClickListener {
            // isbookmarked btn Update
            detailBookmarkBtn.isSelected = !detailBookmarkBtn.isSelected
            // detailItem Update
            viewModel.isBookmarkedItem(detailBookmarkBtn.isSelected)

        }
        detailShareBtn.setOnClickListener {
            /* 추민수
             * context를 넣어줄 때 널체크를 해줌으로써 안정성을 높여줌
             * requireContext를 사용했을 땐 context가 null일 경우 IllegalStateException 발생
             */
            activity?.let { context ->
                viewModel.detailItem.value?.let { detailItem ->
                    Util.shareUrl(context, detailItem.imgUrl)
                }
            }
        }
    }

    /*
     * 추민수
     * 디테일 item update
     */
    private fun updateItem(item: DetailModel) {
        viewModel.updateDetailItem(item)
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}
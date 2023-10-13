package bootcamp.sparta.disneym.ui.detail

import android.app.Activity
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.setFragmentResult
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import bootcamp.sparta.disneym.databinding.FragmentDetailBinding
import bootcamp.sparta.disneym.domain.model.DetailModel
import bootcamp.sparta.disneym.ui.main.MainActivity
import bootcamp.sparta.disneym.util.Util
import bootcamp.sparta.disneym.ui.viewmodel.MainSharedEventForDetail
import bootcamp.sparta.disneym.ui.viewmodel.MainSharedViewModel
import bootcamp.sparta.disneym.ui.viewmodel.detail.DetailViewModel
import bootcamp.sparta.disneym.ui.viewmodel.detail.DetailViewModelFactory
import com.bumptech.glide.Glide
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
    companion object {
        const val BUNDLE_DETAIL = "bundle_detail"

        fun newInstance(item: DetailModel): DetailFragment = DetailFragment().apply {
            arguments = Bundle().apply {
                putParcelable(BUNDLE_DETAIL, item)
            }
        }
    }

    private var _binding: FragmentDetailBinding? = null
    private val binding get() = _binding!!

    private val viewModel: DetailViewModel by viewModels { DetailViewModelFactory() }
    private val sharedViewModel: MainSharedViewModel by activityViewModels()

    private val searchBundle by lazy {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            arguments?.getParcelable(BUNDLE_DETAIL, DetailModel::class.java)
        } else {
            arguments?.getParcelable(BUNDLE_DETAIL)
        }
    }

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
                onBind(it)
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
        toolbar.setNavigationOnClickListener {
            searchBundle?.let { item ->
                val intent = Intent().apply {
                    putExtra(
                        DetailActivity.EXTRA_DETAIL,
                        item.copy(
                            isBookmarked = viewModel.getItemBookmarked()
                        )
                    )
                }
                requireActivity().setResult(Activity.RESULT_OK, intent)
                requireActivity().finish()
            }
        }

        detailPlayBtn.setOnClickListener {
            detailScrollview.smoothScrollTo(0, detailScrollview.bottom)// 하단 스크롤
        }

        detailBookmarkBtn.setOnClickListener {
            // isbookmarked btn Update
            detailBookmarkBtn.isSelected = !detailBookmarkBtn.isSelected
            if(detailBookmarkBtn.isSelected){
                Toast.makeText(context,"북마크에 추가되었습니다.",Toast.LENGTH_SHORT).show()
            }
            else{
                Toast.makeText(context,"북마크에서 제거되었습니다.",Toast.LENGTH_SHORT).show()
            }
            // detailItem Update
            searchBundle?.let { item ->
                viewModel.isBookmarkedItem(
                    item.copy(
                        isBookmarked = detailBookmarkBtn.isSelected
                    )
                )
            }
        }

        detailShareBtn.setOnClickListener {
            /* 추민수
             * context를 넣어줄 때 널체크를 해줌으로써 안정성을 높여줌
             * requireContext를 사용했을 땐 context가 null일 경우 IllegalStateException 발생
             */
            activity?.let { context ->
                viewModel.detailItem.value?.let { detailItem ->
                    detailItem.id?.let { videoId -> Util.shareUrl(context, videoId) }
                }
            }
        }

        searchBundle?.let {
            viewModel.addDetailItem(it)
        }
    }

    /*
     * 추민수
     * 디테일 item update
     */
    private fun updateItem(item: DetailModel) {
        viewModel.updateDetailItem(item)
    }

    private fun onBind(item: DetailModel) = with(binding) {
        Glide.with(requireContext())
            .load(item.imgUrl)
            .into(detailImageImageview)
        detailTitle.text = item.title
        detailDatetimeTv.text = item.datetime
        detailPlayerTitle.text = item.title
        detailPlayerDescription.text = item.description
        item.id?.let { setPlayer(it) }
    }

    /*
     * 추민수
     * Youtube Video Player Library 사용
     * https://github.com/PierfrancescoSoffritti/android-youtube-player
     */
    private fun setPlayer(videoId: String) = with(binding) {
        detailPlayer.addYouTubePlayerListener(object :
            AbstractYouTubePlayerListener() {
            override fun onReady(youTubePlayer: YouTubePlayer) {
                super.onReady(youTubePlayer)
                youTubePlayer.cueVideo(videoId, 0f)
            }
        })
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}
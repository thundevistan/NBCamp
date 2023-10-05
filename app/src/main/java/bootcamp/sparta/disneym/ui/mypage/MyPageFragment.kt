package bootcamp.sparta.disneym.ui.mypage

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import bootcamp.sparta.disneym.databinding.FragmentMyPageBinding
import bootcamp.sparta.disneym.ui.mypage.dialog.MyPageProfileDialog
import bootcamp.sparta.disneym.ui.mypage.dialog.MyPageTextDialog
import bootcamp.sparta.disneym.ui.viewmodel.my.MyPageViewModel
import bootcamp.sparta.disneym.util.Util

/*
* 추민수
* 개인의 정보를 보여주는 페이지
* 수정 버튼을 눌러 정보를 수정할 수 있습니다.
* */
class MyPageFragment : Fragment() {
    private var _binding: FragmentMyPageBinding? = null
    private val binding get() = _binding!!

    private val viewModel: MyPageViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMyPageBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initView()
        initModel()
    }

    private fun initModel() = with(viewModel) {
        userInfo.observe(viewLifecycleOwner, Observer {
            binding.apply {
                myIdTv.text = it.id
                val originalPassword = it?.password // 비밀번호 마스킹
                val maskedPassword = originalPassword?.let {
                    if (it.length > 3) {
                        it.substring(0, 3) + "*".repeat(it.length - 3)
                    } else {
                        it // 길이가 3 이하일 때 마스킹 X
                    }
                }
                myPwTv.text = maskedPassword
                myProfileIv.setImageResource(it.imageUri)
            }
            context?.let { context -> Util.saveUserDataForSharedPrefs(context, it) } // SharedPreference 데이터 저장
        })
    }

    private fun initView() = with(binding) {

        context?.let { context ->
            // SharedPreference 데이터 로드
            Util.loadUserDataForSharedPrefs(context).let { loadData ->
                Log.d("sharedPreference","$loadData")
                viewModel.loadUserData(loadData)
            }
        }
        myEditProfileBtn.setOnClickListener {
            activity?.let {
                viewModel.userInfo.value?.let { it1 ->
                    MyPageProfileDialog(it, it1.imageUri) { callBackProfile ->
                        viewModel.updateUserProfile(callBackProfile)
                    }.show()
                }
            }
        }
        myEditId.setOnClickListener {
            activity?.let {
                viewModel.userInfo.value?.let { it1 ->
                    MyPageTextDialog(it, it1.id, EditDialogType.ID.ordinal) { callBackId ->
                        viewModel.updateUserId(callBackId)
                    }.show()
                }
            }
        }
        myEditPw.setOnClickListener {
            activity?.let {
                viewModel.userInfo.value?.let { it1 ->
                    MyPageTextDialog(it, it1.password, EditDialogType.PW.ordinal) { callBackPw ->
                        viewModel.updateUserPw(callBackPw)
                    }.show()
                }
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}
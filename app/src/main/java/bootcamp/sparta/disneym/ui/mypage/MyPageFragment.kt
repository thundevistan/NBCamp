package bootcamp.sparta.disneym.ui.mypage

import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import bootcamp.sparta.disneym.R
import bootcamp.sparta.disneym.databinding.FragmentMyPageBinding
import bootcamp.sparta.disneym.ui.mypage.dialog.MyPageProfileDialog
import bootcamp.sparta.disneym.ui.mypage.dialog.MyPageTextDialog

class MyPageFragment : Fragment() {
    private var _binding: FragmentMyPageBinding? = null
    private val binding get() = _binding!!

    private val viewModel: MyPageViewModel by viewModels()

    private var userId = "disneym@gmail.com"

    private var userPw = "123456789"

    private var userProfile = R.drawable.profile2

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
            when (it) {
                is EventForUserInfo.UpdateUserId -> {
                    userId = it.text
                    binding.myIdTv.text = it.text
                }

                is EventForUserInfo.UpdateUserPw -> {
                    userPw = it.text
                    val originalPassword = it.text// 비밀번호 예시
                    val maskedPassword =
                        originalPassword.substring(0, 3) + "*".repeat(originalPassword.length - 3)
                    binding.myPwTv.text = maskedPassword
                }

                is EventForUserInfo.UpdateUserProfile -> {
                    userProfile = it.image
                    binding.myProfileIv.setImageResource(it.image)
                }
            }
        })
    }

    private fun initView() = with(binding) {
        myEditProfileBtn.setOnClickListener {
            activity?.let {
                MyPageProfileDialog(it, userProfile) { callBackProfile ->
                    viewModel.updateUserProfile(callBackProfile)
                }.show()
            }
        }
        myEditId.setOnClickListener {
            activity?.let {
                MyPageTextDialog(it, userId) { callBackId ->
                    viewModel.updateUserId(callBackId)
                }.show()
            }
        }
        myEditPw.setOnClickListener {
            activity?.let {
                MyPageTextDialog(it, userPw) { callBackPw ->
                    viewModel.updateUserPw(callBackPw)
                }.show()
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}
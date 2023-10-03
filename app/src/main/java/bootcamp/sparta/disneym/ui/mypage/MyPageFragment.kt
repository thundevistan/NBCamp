package bootcamp.sparta.disneym.ui.mypage

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import bootcamp.sparta.disneym.R
import bootcamp.sparta.disneym.databinding.FragmentMyPageBinding

class MyPageFragment : Fragment() {
    private var _binding : FragmentMyPageBinding? = null
    private val binding get() = _binding!!

    private val viewModel : MyPageViewModel by viewModels()

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

    private fun initModel() = with(viewModel){
        userInfo.observe(viewLifecycleOwner, Observer {
            when(it){
                is EventForUserInfo.UpdateUserId -> {
                    binding.myIdTv.text = it.text.toString()
                }
                is EventForUserInfo.UpdateUserPw -> {
                    val originalPassword = it.text.toString() // 비밀번호 예시
                    val maskedPassword = originalPassword.substring(0, 3) + "*".repeat(originalPassword.length - 3)
                    binding.myPwTv.text = maskedPassword
                }
            }
        })
    }

    private fun initView()=with(binding) {



        myEditId.setOnClickListener {
            activity?.let{
                MyPageTextDialog(it,myIdTv.text.toString()) { callBackId ->
                   viewModel.updateUserId(callBackId)
                }.show()
            }
        }
        myEditPw.setOnClickListener {
            //데이터 저장해놨다가 다시 해야할듯
            activity?.let{
                MyPageTextDialog(it,myPwTv.text.toString()) { callBackPw ->
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
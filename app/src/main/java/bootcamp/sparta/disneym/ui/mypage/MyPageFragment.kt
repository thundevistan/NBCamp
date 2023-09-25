package bootcamp.sparta.disneym.ui.mypage

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import bootcamp.sparta.disneym.R
import bootcamp.sparta.disneym.databinding.FragmentMyPageBinding

class MyPageFragment : Fragment() {
    private var _binding : FragmentMyPageBinding? = null
    private val binding get() = _binding!!
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
    }

    private fun initView()=with(binding) {

    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}
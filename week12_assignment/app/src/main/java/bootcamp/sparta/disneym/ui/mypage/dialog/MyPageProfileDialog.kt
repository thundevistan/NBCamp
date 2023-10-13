package bootcamp.sparta.disneym.ui.mypage.dialog

import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import bootcamp.sparta.disneym.R
import bootcamp.sparta.disneym.databinding.MypageProfileDialogBinding
/*
* 추민수
* 개인 페이지 Profile image 수정을 위한 Dialog.
* MyPageFragment에서 기존의 profile을 받아와 상단에 띄워주고
* recyclerView를 통해 프로필을 수정할 수 있게 구현하였습니다.
* Save 버튼을 눌러 프로필 사진을 저장 Cancel 버튼을 눌러 취소할 수 있습니다.
*/
class MyPageProfileDialog(
    context: Context,
    private val profileUri: Int,
    private val okCallback: (Int) -> Unit,
) : Dialog(context) { // 뷰를 띄우기 위해 Context를 인자로 받아옴

    private lateinit var binding: MypageProfileDialogBinding

    private val list = mutableListOf(
        ProfileDialogModel(R.drawable.profile1),
        ProfileDialogModel(R.drawable.profile2),
        ProfileDialogModel(R.drawable.profile3),
        ProfileDialogModel(R.drawable.profile4),
        ProfileDialogModel(R.drawable.profile5),
        ProfileDialogModel(R.drawable.profile6),
        ProfileDialogModel(R.drawable.profile7),
        ProfileDialogModel(R.drawable.profile8),
        ProfileDialogModel(R.drawable.profile9),
        ProfileDialogModel(R.drawable.profile10),
        ProfileDialogModel(R.drawable.profile11),
        ProfileDialogModel(R.drawable.profile12),
        ProfileDialogModel(R.drawable.profile13),
    )

    private var callBackPosition : Int = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // 만들어놓은 dialog_profile.xml 뷰 띄워줌
        binding = MypageProfileDialogBinding.inflate(layoutInflater)

        setContentView(binding.root)
        listAdapter.submitList(list)
        initViews()
    }

    private val listAdapter by lazy {
        ProfileListAdapter(onClickItem = { position, item ->
            binding.dialogProfile.setImageResource(item.profileImage)
            callBackPosition = position
        })
    }

    private fun initViews() = with(binding) {

        recyclerProfile.adapter = listAdapter
        recyclerProfile.adapter

        dialogProfile.setImageResource(profileUri)

        // 뒤로가기, 빈 화면 터치를 통해 dialog가 종료되지 않게하는 코드
        setCancelable(false)

        // Dialog는 내부적으로 흰 배경이 존재하므로 Corner Radius 적용을 하려면 background 투명하게 해주어야 한다.
        window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

        // 종료
        dialogCancelBtn.setOnClickListener {
            dismiss()
        }

        // Button 클릭에 대한 Callback 처리
        dialogSaveBtn.setOnClickListener {
            if(callBackPosition == -1){
                Toast.makeText(context, "프로필을 변경해주세요!", Toast.LENGTH_SHORT).show()
            }
            else{
                okCallback(list[callBackPosition].profileImage)
                dismiss()
            }
        }
    }
}
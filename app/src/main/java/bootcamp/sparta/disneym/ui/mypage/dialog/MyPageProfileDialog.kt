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

class MyPageProfileDialog(
    context: Context,
    private val profileUri: Int,
    private val okCallback: (Int) -> Unit,
) : Dialog(context) { // 뷰를 띄워야하므로 Dialog 클래스는 context를 인자로 받는다.

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
//        val layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        recyclerProfile.adapter
//        recyclerProfile.layoutManager =  layoutManager


        dialogProfile.setImageResource(profileUri)

        // 뒤로가기 버튼, 빈 화면 터치를 통해 dialog가 사라지지 않도록
        setCancelable(false)

        // background 투명하게
        // (중요) Dialog는 내부적으로 뒤에 흰 사각형 배경이 존재하므로, 배경을 투명하게 만들지 않으면
        // corner radius의 적용이 보이지 않는다.
        window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

        // 종료
        dialogCancelBtn.setOnClickListener {
            dismiss()
        }

        // OK Button 클릭에 대한 Callback 처리
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
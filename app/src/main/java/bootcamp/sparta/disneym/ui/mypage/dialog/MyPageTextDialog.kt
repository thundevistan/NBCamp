package bootcamp.sparta.disneym.ui.mypage.dialog

import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.widget.Toast
import bootcamp.sparta.disneym.databinding.MypageTextDialogBinding

/*
* 추민수
* 개인 페이지 ID, PW를 수정하기 위한 Dialog.
* MyPageFragment에서 기존의 ID or PW를 받아와 상단에 띄워주고
* 진입 타입에 따라 InputType이 달라지게 설정하였습니다.
* Save 버튼을 눌러 Text를 저장 Cancel 버튼을 눌러 취소할 수 있습니다.
*/
class MyPageTextDialog(
    context: Context,
    private val userInfo: String,
    private val okCallback: (String) -> Unit,
) : Dialog(context) { // 뷰를 띄워야하므로 Dialog 클래스는 context를 인자로 받는다.

    private lateinit var binding: MypageTextDialogBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // 만들어놓은 dialog_profile.xml 뷰를 띄운다.
        binding = MypageTextDialogBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.dialogText.setText(userInfo)
        initViews()
    }

    private fun initViews() = with(binding) {

        dialogText.inputType

        // 뒤로가기, 빈 화면 터치를 통해 dialog가 종료되지 않게하는 코드
        setCancelable(false)

        // Dialog는 내부적으로 흰 배경이 존재하므로 Corner Radius 적용을 하려면 background 투명하게 해주어야 한다.
        window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

        // 종료
        dialogCancelBtn.setOnClickListener{
            dismiss()
        }

        // Button 클릭에 대한 Callback 처리
        dialogSaveBtn.setOnClickListener{
            if (dialogText.text.isNullOrBlank()) {
                Toast.makeText(context, "내용을 입력해주세요!", Toast.LENGTH_SHORT).show()
            }
            else{
                okCallback(dialogText.text.toString())
                dismiss()
            }
        }
    }
}
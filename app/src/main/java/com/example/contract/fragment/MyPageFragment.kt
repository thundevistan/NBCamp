package com.example.contract.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.Fragment
import androidx.appcompat.app.AlertDialog
import com.example.contract.R

class MyPageFragment : Fragment() {

    // EditText를 위한 변수
    private lateinit var nameEditText: EditText
    private lateinit var phoneNumberEditText: EditText
    private lateinit var emailEditText: EditText
    private lateinit var eventEditText: EditText

    // 나중에 값을 저장할 변수
    private var name: String = "daeulzzang" // 예시 이름
    private var phoneNumber: String = "010-1111-1111" // 예시 전화번호
    private var email: String = "ball1241@naver.com" // 예시 이메일
    private var event: String = "10분후에 알림" // 예시 이벤트

    // Fragment의 UI를 생성하고 반환합니다.
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Fragment의 레이아웃을 인플레이트하고 반환합니다.
        val view = inflater.inflate(R.layout.fragment_my_page, container, false)

        // EditText 초기화
        nameEditText = view.findViewById(R.id.nameEditText)
        phoneNumberEditText = view.findViewById(R.id.phoneNumberEditText)
        emailEditText = view.findViewById(R.id.emailEditText)
        eventEditText = view.findViewById(R.id.eventEditText)

        // 현재 값을 EditText에 표시
        nameEditText.setText(name)
        phoneNumberEditText.setText(phoneNumber)
        emailEditText.setText(email)
        eventEditText.setText(event)

        // Edit 버튼 클릭 시 이벤트 처리
        val editButton = view.findViewById<Button>(R.id.btn_edit)
        editButton.setOnClickListener {
            showEditProfileDialog()
        }

        return view
    }

    // 커스텀 다이얼로그를 표시하는 메서드
    private fun showEditProfileDialog() {
        val builder = AlertDialog.Builder(requireContext())
        builder.setTitle("Edit Profile")

        // 커스텀 다이얼로그의 XML 레이아웃을 인플레이트
        val customLayout = layoutInflater.inflate(R.layout.custom_dialong, null)

        // 다이얼로그의 EditText에 현재 값을 설정
        val editName = customLayout.findViewById<EditText>(R.id.editName)
        val editPhoneNumber = customLayout.findViewById<EditText>(R.id.editPhoneNumber)
        val editEmail = customLayout.findViewById<EditText>(R.id.editEmail)
        val editEvent = customLayout.findViewById<EditText>(R.id.editEvent)

        editName.setText(name)
        editPhoneNumber.setText(phoneNumber)
        editEmail.setText(email)
        editEvent.setText(event)

        builder.setView(customLayout)

        builder.setPositiveButton("Save") { _, _ ->
            // 사용자가 Save 버튼을 클릭한 경우
            // EditText에서 수정된 값을 가져와 변수에 저장
            name = editName.text.toString()
            phoneNumber = editPhoneNumber.text.toString()
            email = editEmail.text.toString()
            event = editEvent.text.toString()

            // 수정된 값을 TextView에 업데이트
            nameEditText.setText(name)
            phoneNumberEditText.setText(phoneNumber)
            emailEditText.setText(email)
            eventEditText.setText(event)
        }

        builder.setNegativeButton("Cancel") { _, _ ->
            // 사용자가 Cancel 버튼을 클릭한 경우 아무 작업도 수행하지 않음
        }

        builder.show()
    }
}

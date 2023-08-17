package com.example.android_supporters_sns_project

import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AlertDialog
import androidx.core.content.ContextCompat
import com.example.android_supporters_sns_project.dataclass.Member
import java.util.regex.Pattern

class SignupActivity : AppCompatActivity() {

    var imageDataTemp : Uri? = null

    //각 Edittext 입력 여부에 따라 바뀌는 변수
    private var checkEmail = false
    private var checkPassword = false
    var checkPasswordCheck = false
    var checkName = false
    var checkNickname = false

    //Widget(email)
    private lateinit var emailEditText: EditText
    private lateinit var emailRepetitionButton: Button
    private lateinit var emailWarningText: TextView
    private lateinit var emailConfirmCheck: ImageView

    //Widget(password)
    private lateinit var passwordEditText: EditText
    private lateinit var passwordWarningText: TextView
    private lateinit var passwordConfirmCheck: ImageView

    //Widget(passwordcheck)
    private lateinit var passwordCheckEditText: EditText
    private lateinit var passwordCheckWarningText: TextView
    private lateinit var passwordCheckConfirmCheck: ImageView

    //Widget(name)
    private lateinit var nameEditText: EditText
    private lateinit var nameWarningText: TextView
    private lateinit var nameConfirmCheck: ImageView

    //Widget(nickname)
    private lateinit var nicknameEditText: EditText
    private lateinit var nicknameRepetitionButton: Button
    private lateinit var nicknameWarningText: TextView
    private lateinit var nicknameConfirmCheck: ImageView

    private lateinit var addPictureImage : ImageView

    private lateinit var backButton : ImageView

    private lateinit var confirmButton: Button


    //정규식
    private val email =
        "^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$"
    private val password = "^[A-Za-z0-9]{8,16}$"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup)

        emailEditText = findViewById(R.id.signup_email_edittext)
        emailRepetitionButton = findViewById(R.id.signup_emailrepetition_button)
        emailWarningText = findViewById(R.id.signup_emailwarning_textview)
        emailConfirmCheck = findViewById(R.id.signup_confirmemail_imageview)

        passwordEditText = findViewById(R.id.signup_password_edittext)
        passwordWarningText = findViewById(R.id.signup_passwordwarning_textview)
        passwordConfirmCheck = findViewById(R.id.signup_confirmpassword_imageview)

        passwordCheckEditText = findViewById(R.id.signup_passwordcheck_edittext)
        passwordCheckWarningText = findViewById(R.id.signup_passwordcheckwarning_textview)
        passwordCheckConfirmCheck = findViewById(R.id.signup_confirmpasswordcheck_imageview)

        nameEditText = findViewById(R.id.signup_name_edittext)
        nameWarningText = findViewById(R.id.signup_namewarning_textview)
        nameConfirmCheck = findViewById(R.id.signup_confirmname_imageview)

        nicknameEditText = findViewById(R.id.signup_nickname_edittext)
        nicknameRepetitionButton = findViewById(R.id.signup_nicknamerepetition_button)
        nicknameWarningText = findViewById(R.id.signup_nicknamewarning_textview)
        nicknameConfirmCheck = findViewById(R.id.signup_confirmnickname_imageview)

        addPictureImage = findViewById(R.id.signup_addpicture_imageview)

        confirmButton = findViewById(R.id.signup_confirm_button)

        backButton = findViewById(R.id.signup_backspace_imageview)

        //Email edittext, button에 리스너 부착
        emailEditText.addTextChangedListener(signupTextWatcher)
        passwordEditText.addTextChangedListener(signupTextWatcher)
        passwordCheckEditText.addTextChangedListener(signupTextWatcher)
        nameEditText.addTextChangedListener(signupNameTextWatcher)
        nicknameEditText.addTextChangedListener(signupNickNameTextWatcher)

        emailRepetitionButton.setOnClickListener {
            emailConfirmCheck.visibility = View.VISIBLE
            checkEmail = true
            enableConfirmButton()
        }

        nicknameRepetitionButton.setOnClickListener {
            nicknameConfirmCheck.visibility = View.VISIBLE
            checkNickname = true
            enableConfirmButton()
        }

        backButton.setOnClickListener {
            finish()
        }

        //사진을 선택한 경우
        addPictureImage.setOnClickListener {
            when {
                //갤러리 접근 권한이 있는 경우
                ContextCompat.checkSelfPermission(
                    this,
                    android.Manifest.permission.READ_EXTERNAL_STORAGE
                ) == PackageManager.PERMISSION_GRANTED -> {
                    navigateGallery()
                }
                shouldShowRequestPermissionRationale(android.Manifest.permission.READ_EXTERNAL_STORAGE)
                -> {
                    showPermissionContextPopup()
                }
                //갤러리 접근 권한 설정
                else -> requestPermissions(
                    arrayOf(android.Manifest.permission.READ_EXTERNAL_STORAGE),
                    1000
                )
            }
        }

        confirmButton.setOnClickListener {
            createInstance()
        }
        confirmButton.setOnClickListener {
            finish()
            overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right)
        }

    }

    //Text Watcher
    private val signupTextWatcher = object : TextWatcher {
        override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

        }

        override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

        }

        override fun afterTextChanged(p0: Editable?) {
            if (emailEditText.text.isNotEmpty()) {
                emailCheck()
                enableConfirmButton()
            }
            if (passwordEditText.text.isNotEmpty()) {
                passwordCheck()
                enableConfirmButton()
            }
            if (passwordCheckEditText.text.isNotEmpty()) {
                checkPasswordCheck()
                enableConfirmButton()
            }
        }
    }

    //Name Text Watcher
    private val signupNameTextWatcher = object : TextWatcher {
        override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

        }

        override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

        }

        override fun afterTextChanged(p0: Editable?) {
            nameCheck()
            enableConfirmButton()
        }

    }

    //Nickname Text Watcher
    private val signupNickNameTextWatcher = object : TextWatcher {
        override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

        }

        override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

        }

        override fun afterTextChanged(p0: Editable?) {
            nicknameCheck()
            enableConfirmButton()
        }

    }

    //이메일 edittext를 확인하고 widget을 변경
    private fun emailCheck() {
        val emailText = emailEditText.text.toString().trim()
        val emailCheck = Pattern.matches(email, emailText)
        if (emailCheck) {
            emailWarningText.visibility = View.INVISIBLE
            emailRepetitionButton.isEnabled = true
        } else {
            emailWarningText.visibility = View.VISIBLE
            emailRepetitionButton.isEnabled = false
            emailConfirmCheck.visibility = View.INVISIBLE
            checkEmail = false
        }
    }

    //비밀번호 edittext를 확인하고 widget을 변경
    private fun passwordCheck() {
        val passwordText = passwordEditText.text.toString().trim()
        val passwordCheck = Pattern.matches(password, passwordText)

        if (passwordCheck) {
            passwordWarningText.visibility = View.INVISIBLE
            passwordConfirmCheck.visibility = View.VISIBLE
            checkPassword = true
        } else {
            passwordWarningText.visibility = View.VISIBLE
            passwordConfirmCheck.visibility = View.INVISIBLE
            checkPassword = false
        }
    }

    //비밀번호 확인 edittext를 확인하고 widget을 변경
    private fun checkPasswordCheck() {
        val checkPasswordText = passwordCheckEditText.text.toString().trim()
        val passwordText = passwordEditText.text.toString().trim()

        if (checkPasswordText == passwordText) {
            passwordCheckWarningText.visibility = View.INVISIBLE
            passwordCheckConfirmCheck.visibility = View.VISIBLE
            checkPasswordCheck = true
        } else {
            passwordCheckWarningText.visibility = View.VISIBLE
            passwordCheckConfirmCheck.visibility = View.INVISIBLE
            checkPasswordCheck = false
        }
    }

    //이름 edittext를 확인하고 widget을 변경
    private fun nameCheck() {
        val nameText = nameEditText.text.toString().trim()

        if (nameText.isNotEmpty()) {
            nameWarningText.visibility = View.INVISIBLE
            nameConfirmCheck.visibility = View.VISIBLE
            checkName = true
        } else if (nameText.isEmpty()) {
            nameWarningText.visibility = View.VISIBLE
            nameConfirmCheck.visibility = View.INVISIBLE
            checkName = false
        }
    }

    //닉네임 edittext를 확인하고 widget을 변경

    private fun nicknameCheck() {
        val nicknameText = nicknameEditText.text.toString().trim()

        if (nicknameText.length in 1..15) {
            nicknameWarningText.visibility = View.INVISIBLE
            nicknameRepetitionButton.isEnabled = true
        } else {
            nicknameWarningText.visibility = View.VISIBLE
            nicknameRepetitionButton.isEnabled = false
            nicknameConfirmCheck.visibility = View.INVISIBLE
            checkNickname = false
        }
    }

    private fun enableConfirmButton() {
        confirmButton.isEnabled =
            checkEmail && checkPassword && checkPasswordCheck && checkNickname && checkName
    }

    private fun createInstance() {
        val member = Member(
            emailEditText.text.toString().trim(),
            passwordEditText.text.toString().trim(),
            nameEditText.text.toString().trim(),
            nicknameEditText.text.toString().trim(),
            imageDataTemp
        )

        MemberManager.addMember(member) // 입력 받은 값을 추가
        MemberManager.printMembers() // memberList에 저장된 데이터 확인을 위한 Logcat 출력
    }

    //갤러리에서 사진 선택
    private fun navigateGallery() {
        val intent = Intent(Intent.ACTION_PICK)
        intent.type = "image/*"
        pickImageActivityResult.launch(intent)
    }

    //사진을 선택한 후
    private val pickImageActivityResult =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result: ActivityResult ->
            if (result.resultCode == Activity.RESULT_OK) {
                val data: Intent? = result.data
                if (data != null) {
                    val selectedImageUri: Uri? = data.data
                    if (selectedImageUri != null) {
                        addPictureImage.setImageURI(selectedImageUri)
                        imageDataTemp = selectedImageUri
                    } else {
                        Toast.makeText(this, "사진을 가져오지 못했습니다.", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }

    //권한 설정 팝업
    private fun showPermissionContextPopup() {
        AlertDialog.Builder(this)
            .setTitle("권한이 필요합니다.")
            .setMessage("프로필 이미지를 바꾸기 위해서는 갤러리 접근 권한이 필요합니다.")
            .setPositiveButton("동의하기") { _, _ ->
                requestPermissions(arrayOf(android.Manifest.permission.READ_EXTERNAL_STORAGE), 1000)
            }
            .setNegativeButton("취소하기") { _, _ -> }
            .create()
            .show()
    }
}
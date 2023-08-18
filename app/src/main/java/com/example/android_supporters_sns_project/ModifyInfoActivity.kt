package com.example.android_supporters_sns_project

import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.example.android_supporters_sns_project.dataclass.Member
import com.google.android.material.textfield.TextInputLayout

class ModifyInfoActivity : AppCompatActivity() {

    var isNickname = true
    var isPassword = true
    var isPasswordCheck = false

    private lateinit var editImage: ImageView
    private lateinit var editImageButton: Button
    private lateinit var backButton : ImageButton
    private lateinit var mainButton : Button
    private lateinit var emailEditText : TextView
    private lateinit var nameEditText : TextView
    private lateinit var nicknameEditText: EditText
    private lateinit var passwordEditText: EditText
    private lateinit var passwordCheckEditText : EditText

    private lateinit var passwordcheckWarningText : TextView
    private lateinit var nicknameWarningText : TextView

    private lateinit var passwordCheck : ImageView
    private lateinit var passwordCheckCheck : ImageView
    private lateinit var nicknameCheck : ImageView

    private lateinit var nicknameCheckRepititionButton : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_modify_info)

        isNickname = true
        isPassword = true
        isPasswordCheck =false

        emailEditText = findViewById(R.id.modify_info_email_edittext)
        nameEditText = findViewById(R.id.modify_info_name_edittext)
        nicknameEditText = findViewById(R.id.modify_info_nickname_edittext)
        passwordEditText = findViewById(R.id.modify_info_password_edittext)
        passwordCheck = findViewById(R.id.modify_password_check)
        passwordCheckCheck = findViewById(R.id.modify_passwordcheck_check)
        nicknameCheck = findViewById(R.id.modify_nickname_check)
        passwordCheckEditText = findViewById(R.id.modify_info_passwordcheck_edittext)

        passwordcheckWarningText = findViewById(R.id.modify_info_passwordcheck_error_message)
        nicknameWarningText = findViewById(R.id.modify_nickname_error_message)

        nicknameCheckRepititionButton = findViewById(R.id.modify_info_nicknamerepetition_button)

        val emailData : String? = intent.getStringExtra("email")
        val member : Member? = emailData?.let { MemberManager.getMemberByEmail(it) }

        emailEditText.text = member?.email
        nameEditText.text = member?.name
        nicknameEditText.setText(member?.nickname)
        passwordEditText.setText(member?.password)

        backButton = findViewById(R.id.modify_info_back_button)

        backButton.setOnClickListener {
            finish()
        }
        passwordCheckEditText.addTextChangedListener(passwordCheckWatcher)
        nicknameEditText.addTextChangedListener(nicknameTextWatcher)
        nicknameCheckRepititionButton.setOnClickListener {
            nicknameCheck.visibility = View.VISIBLE
            nicknameWarningText.visibility = View.INVISIBLE
        }

        initImageView()
        passwordTextWatcher()

        mainButton = findViewById(R.id.modify_info_ok_button)
        mainButton.setOnClickListener {
            val saveNickname = nicknameEditText.text.toString().trim()
            val savePassword = passwordEditText.text.toString().trim()
            MemberManager.updateMember(emailData, savePassword, saveNickname)
            finish()

        }
    }

    // 이미지 수정 버튼을 눌렀을때 실행되는 함수
    private fun initImageView() {
        editImage = findViewById(R.id.modify_info_image)
        editImageButton = findViewById(R.id.modify_info_image_edit)

        editImageButton.setOnClickListener {
            when {
                // 갤러리 접근 권한이 있는 경우
                ContextCompat.checkSelfPermission(
                    this,
                    android.Manifest.permission.READ_EXTERNAL_STORAGE
                ) == PackageManager.PERMISSION_GRANTED -> {
                    navigateGallery()
                }
                // 갤러리 접근 권한이 없는 경우
                shouldShowRequestPermissionRationale(android.Manifest.permission.READ_EXTERNAL_STORAGE)
                -> {
                    showPermissionContextPopup()
                }

                // 권한 요청(requestPermissions) -> 갤러리 접근(onRequestPermissionResult)
                else -> requestPermissions(
                    arrayOf(android.Manifest.permission.READ_EXTERNAL_STORAGE),
                    1000
                )
            }


        }
    }

    // 권한 요청 승인 이후 실행되는 함수
    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        when (requestCode) {
            1000 -> {
                if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED)
                    navigateGallery()
                else
                    Toast.makeText(this, getString(R.string.modify_permission), Toast.LENGTH_SHORT).show()
            }

            else -> {
            }
        }
    }

    // 갤러리에서 사진만 보여줌
    private fun navigateGallery() {
        val intent = Intent(Intent.ACTION_PICK)
        intent.type = "image/*"
        pickImageActivityResult.launch(intent)
    }

    // 갤러리에서 사진 선택한 후
    private val pickImageActivityResult =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result: ActivityResult ->
            if (result.resultCode == Activity.RESULT_OK) {
                val data: Intent? = result.data
                if (data != null) {
                    val selectedImageUri: Uri? = data.data
                    if (selectedImageUri != null) {
                        editImage.setImageURI(selectedImageUri)
                    } else {
                        Toast.makeText(this, getString(R.string.modify_pictureFailed), Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }

    // 권한 요청 팝업
    private fun showPermissionContextPopup() {
        AlertDialog.Builder(this)
            .setTitle(getString(R.string.modify_permissionRequired))
            .setMessage(getString(R.string.modify_profilePermission))
            .setPositiveButton(getString(R.string.modify_agree)) { _, _ ->
                requestPermissions(arrayOf(android.Manifest.permission.READ_EXTERNAL_STORAGE), 1000)
            }
            .setNegativeButton(getString(R.string.modify_cancel)) { _, _ -> }
            .create()
            .show()
    }

    private fun isPasswordValid(password: String): Boolean {
        return !password.contains(" ") && password.matches("^(?=.*[A-Za-z])(?=.*[0-9])[A-Za-z0-9]{8,16}\$".toRegex())
    }

    private fun passwordTextWatcher() {
        val passwordLayout =
            findViewById<TextInputLayout>(R.id.modify_info_password_textinputlayout)
        val errorMessageView = findViewById<TextView>(R.id.modify_info_password_error_message)

        passwordLayout.editText?.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(s: CharSequence?, p1: Int, p2: Int, p3: Int) {
                if ((s?.length ?: 0) in 1..7 || (s?.length
                        ?: 0) > 16 || !isPasswordValid(s.toString())
                ) {
                    errorMessageView.visibility = View.VISIBLE
                    errorMessageView.text = getString(R.string.modifyinfo_password_warning_text)
                    isPassword = false
                    passwordCheck.visibility = View.INVISIBLE
                    activeButton()

                } else {
                    errorMessageView.visibility = View.INVISIBLE
                    isPassword = true
                    activeButton()
                    passwordCheck.visibility = View.VISIBLE
                }
            }

            override fun afterTextChanged(s: Editable?) {
            }
        })
    }

    private val passwordCheckWatcher = object : TextWatcher {
        override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

        }

        override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            if (passwordEditText.text.toString() == passwordCheckEditText.text.toString()) {
                isPasswordCheck = true
                passwordcheckWarningText.visibility = View.INVISIBLE
                passwordCheckCheck.visibility = View.VISIBLE
                activeButton()
            }
            else {
                isPasswordCheck = false
                passwordcheckWarningText.visibility = View.VISIBLE
                passwordCheckCheck.visibility = View.INVISIBLE
                activeButton()
            }
        }

        override fun afterTextChanged(p0: Editable?) {

        }
    }

    private val nicknameTextWatcher = object : TextWatcher {
        override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

        }

        override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            nicknameCheck.visibility = View.INVISIBLE
        }

        override fun afterTextChanged(p0: Editable?) {
            if (nicknameEditText.text.toString().isNotEmpty()) {
                nicknameWarningText.visibility = View.INVISIBLE
                nicknameCheckRepititionButton.isEnabled = true
                activeButton()
            }
            else if(nicknameEditText.text.toString().isEmpty()) {
                nicknameWarningText.text = getString(R.string.modify_enterNickname)
                nicknameWarningText.visibility = View.VISIBLE
                nicknameCheckRepititionButton.isEnabled = false
                activeButton()
            }
        }

    }
    private fun activeButton() {
        mainButton.isEnabled = isPassword && isNickname && isPasswordCheck
    }
}
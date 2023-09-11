package com.example.contract.fragment

import android.annotation.SuppressLint
import android.app.Activity
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.content.Context.NOTIFICATION_SERVICE
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.MediaStore
import android.text.Editable
import android.text.TextUtils
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.widget.Toast
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.fragment.app.DialogFragment
import com.example.contract.MainActivity
import com.example.contract.R
import com.example.contract.databinding.FragmentDialogBinding
import com.example.contract.sampledata.ContactItem
import com.example.contract.sampledata.ContactManager
import java.io.IOException


class DialogFragment : DialogFragment() {
    companion object {
        const val GALLERY_REQUEST_CODE = 1001 // 사용자 정의 요청 코드
    }

    private lateinit var binding: FragmentDialogBinding
    private val profile by lazy { binding.ivDialogProfile }
    private val name by lazy { binding.editDialogName }
    private val phone by lazy { binding.editDialogPhone }
    private val email by lazy { binding.editDialogEmail }
    private val event by lazy { binding.editDialogEvent }
    private val chipGroup by lazy { binding.chipGroup }
    private val cancel by lazy { binding.btnDialogCancel }
    private val save by lazy { binding.btnDialogSave }
    private var profileImg: Uri? = null
    var noti: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        isCancelable = true
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dialog?.window?.requestFeature(Window.FEATURE_NO_TITLE)
        binding = FragmentDialogBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        save.isEnabled = false
        profile.clipToOutline = true

        binding.ivDialogProfile.setOnClickListener {
            val intent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
            startActivityForResult(intent, GALLERY_REQUEST_CODE)
        }


        // 이메일 유효성 검사
        email.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun afterTextChanged(s: Editable?) {
                if (!android.util.Patterns.EMAIL_ADDRESS.matcher(s.toString()).matches()) {
                    email.setBackgroundResource(R.drawable.shape_dialog_edit_npe)
                    save.isEnabled = false
                } else {
                    email.setBackgroundResource(R.drawable.selector_dialog_edit)
                    isSaved()
                    Log.d("save", "save 기능 활성화")
                }

                // Save 버튼 이벤트 처리
                save.setOnClickListener {
                    if (android.util.Patterns.EMAIL_ADDRESS.matcher(s.toString()).matches()) {
                        when (chipGroup.checkedChipId) {
                            R.id.chip_dialogNoti2 -> {
                                notification()
                                Toast.makeText(activity, noti, Toast.LENGTH_SHORT).show()
                            }

                            R.id.chip_dialogNoti3 -> {
                                notification()
                                Toast.makeText(activity, noti, Toast.LENGTH_SHORT).show()
                            }

                            R.id.chip_dialogNoti4 -> {
                                notification()
                                Toast.makeText(activity, noti, Toast.LENGTH_SHORT).show()
                            }
                        }
                        addContact()


                        // (1) 컨텍스트를 액티비티로 캐스팅
                        // (2) (1).전역번수로뺀어댑터.notifyDataSetChanged
                        val contactRv = (context as MainActivity).contactRv
                        val listAdapter = (context as MainActivity).listAdapter
                        contactRv.adapter = listAdapter
                        listAdapter.notifyDataSetChanged()

                        dismiss()
                    }
                }
            }
        })

        // 알림 이벤트 처리
        chipGroup.setOnCheckedChangeListener { _, checkedId ->

            when (checkedId) {
                R.id.chip_dialogNoti -> {

                }

                R.id.chip_dialogNoti2 -> {
                    noti = "${name.text}님께 연락 할 수 있도록 5분 이후 알림"
                }

                R.id.chip_dialogNoti3 -> {
                    noti = "${name.text}님께 연락 할 수 있도록 10분 이후 알림"
                }

                R.id.chip_dialogNoti4 -> {
                    noti = "${name.text}님께 연락 할 수 있도록 30분 이후 알림"
                }
            }
        }

        cancel.setOnClickListener {
            dismiss()
        }
    }

    // Save 버튼 활성화 로직
    fun isSaved() {
        val isName = TextUtils.isEmpty(name.text.toString())
        val isPhone = TextUtils.isEmpty(phone.text.toString())
        val isEmail = TextUtils.isEmpty(email.text.toString())

        if (!isName && !isPhone && !isEmail) {
            save.isEnabled = true
        }
    }

    // 연락처 추가 로직
    fun addContact() {
        val name = this.name.text.toString()
        val phone = this.phone.text.toString()
        val email = this.email.text.toString()
        val event = this.event.text.toString()

        val listSize = ContactManager.getContact().size

        Log.d("addContact", "name: $name, phone: $phone, email: $email, event: $event")


        ContactManager.addContact(
            ContactItem(
                listSize - 1,
                ContactManager.getContact().size.toLong() - 1,
                profileImg,
                name,
                null,
                false,
                phone,
                email,
                event
            )
        )
    }

    // Notification
    @SuppressLint("MissingPermission")
    private fun notification() {
        val activity = activity as MainActivity
        val notificationId = 1
        val channelId = "default"

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                channelId,
                "default channel",
                NotificationManager.IMPORTANCE_DEFAULT
            )
            channel.description = "Team 14 week9_assignment."
            val notificationManager =
                activity.getSystemService(NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(channel)
        }

        val name = this.name.text.toString()
        val builder = NotificationCompat.Builder(activity, channelId)
            .setSmallIcon(R.drawable.ic_noti)
            .setContentTitle("연락처 알림")
            .setContentText("${name}에게 연락을 할 시간 입니다")
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
        NotificationManagerCompat.from(activity).notify(notificationId, builder.build())
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == GALLERY_REQUEST_CODE && resultCode == Activity.RESULT_OK && data != null) {
            val selectedImageUri: Uri? = data.data
            profileImg = selectedImageUri!!

            if (selectedImageUri != null) {
                // Uri에서 비트맵으로 이미지 로드
                val bitmap: Bitmap? = loadBitmapFromUri(requireContext(), selectedImageUri)

                if (bitmap != null) {
                    // 이미지를 ImageView에 설정
                    binding.ivDialogProfile.setImageBitmap(bitmap)
                }
            }
        }
    }

    private fun loadBitmapFromUri(context: Context, uri: Uri): Bitmap? {
        return try {
            val inputStream = context.contentResolver.openInputStream(uri)
            BitmapFactory.decodeStream(inputStream)
        } catch (e: IOException) {
            e.printStackTrace()
            null
        }
    }
}
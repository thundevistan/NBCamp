package com.example.android_supporters_sns_project.dataclass

import android.net.Uri

data class Member(
    val email: String,
    val password: String,
    val name: String,
    val nickname: String,
    val profile: Uri?
)

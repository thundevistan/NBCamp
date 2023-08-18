package com.example.android_supporters_sns_project.dataclass

import android.net.Uri

data class Member(
    val email: String,
    var password: String,
    val name: String,
    var nickname: String,
    val profile: Uri?
)

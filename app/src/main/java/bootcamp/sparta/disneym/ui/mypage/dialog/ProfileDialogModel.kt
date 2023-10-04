package bootcamp.sparta.disneym.ui.mypage.dialog

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ProfileDialogModel(
    val profileImage : Int
) : Parcelable
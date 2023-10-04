package bootcamp.sparta.disneym.ui.mypage.dialog

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
/*
* 추민수
* DialogModel
*/
@Parcelize
data class ProfileDialogModel(
    val profileImage : Int
) : Parcelable
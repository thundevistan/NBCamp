package bootcamp.sparta.disneym.ui.mypage
import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class UserModel(
    val imageUri :Int,
    val id : String,
    val password : String
) : Parcelable
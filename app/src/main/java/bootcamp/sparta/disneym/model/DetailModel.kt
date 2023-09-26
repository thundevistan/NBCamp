package bootcamp.sparta.disneym.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class DetailModel(
    val title: String,
    val imgUrl : String,
    val description : String,
    val datetime : String
) : Parcelable
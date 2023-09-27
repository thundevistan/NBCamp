package bootcamp.sparta.disneym.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class TestModel (
    val title: String,
    val imgUrl : String,
    val description : String,
    val datetime : String
) : Parcelable

fun TestModel.toDetailModel() : DetailModel{
    return DetailModel(
        title = title,
        imgUrl = imgUrl,
        description = description,
        datetime = datetime
    )
}
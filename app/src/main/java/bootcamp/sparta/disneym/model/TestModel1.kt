package bootcamp.sparta.disneym.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
/*
* 추민수
* 다른분들 작업 전 이해를 돕기위해 구성해놓은 TestModel 입니다.
* 확장함수를 통해 디테일 페이지로 데이터를 전달할 때 매핑해주는 함수를 포함하고 있습니다.
*/
@Parcelize
data class TestModel1 (
    val title: String,
    val imgUrl : String,
    val description : String,
    val datetime : String,
    val isBookmarked : Boolean,
) : Parcelable

fun TestModel1.toDetailModel() : DetailModel{
    return DetailModel(
        title = title,
        imgUrl = imgUrl,
        description = description,
        datetime = datetime,
        isBookmarked = isBookmarked,
    )
}
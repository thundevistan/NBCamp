package bootcamp.sparta.disneym.data.model

import com.google.gson.annotations.SerializedName

/*
* Copyright 2023 김현준, Inc.
*
* Json에서 여러 필드와 매핑하여 비디오 대한 정보를 가져옴
* */



data class Videos(
    @SerializedName("kind")
    val kind: String,

    @SerializedName("etag")
    val etag : String,

    @SerializedName("items")
    val items: List<ItemsVideo>
)

data class ItemsVideo(

    @SerializedName("id")
    val id: String,

    // 동영상에 대한 세부정보
    @SerializedName("snippet")
    val snippet: SnippetYT,

    @SerializedName("statistics")
    val statistics: StatisticsYT

)

data class SnippetYT(
    @SerializedName("channelId")
    val channelId: String,

    @SerializedName("title")
    val title: String,

    @SerializedName("description")
    val description: String,

    @SerializedName("publishedAt")
    val publishedAt: String,

    @SerializedName("thumbnails")
    val thumbnails: ThumbnailsYT,

    @SerializedName("categoryId")
    val categoryId: String,

    @SerializedName("channelTitle")
    val channelTitle: String

    )

data class ThumbnailsYT(
    @SerializedName("high")
    val high: High
) {
    data class High (
        @SerializedName("url")
        val url: String

    )
}

data class StatisticsYT (

    @SerializedName("viewCount")
    val viewCount: Int,

    @SerializedName("likeCount")
    val likeCount: Int,

    @SerializedName("dislikeCount")
    val dislikeCount: Int,

    @SerializedName("favoriteCount")
    val favoriteCount: Int,

    @SerializedName("commentCount")
    val commentCount: Int
)

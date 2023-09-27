package bootcamp.sparta.disneym.data.datasource.remote

import com.google.gson.annotations.SerializedName

/*
* Copyright 2023 김현준, Inc.
*
* Json에서 여러 필드와 매핑하여 카테고리 목록에 대한 정보를 가져옴
* */

data class CategoryModel (
    @SerializedName("videoCategories")
    val videoCategories : VideoCategories
) {
    data class VideoCategories(
        @SerializedName("kind")
        val kind: String,

        @SerializedName("etag")
        val etag: String,

        @SerializedName("items")
        val items: List<Items>
    )

    data class Items(
        @SerializedName("kind")
        val kind: String,

        @SerializedName("etag")
        val etag: String,

        @SerializedName("id")
        val id: String,

        @SerializedName("snippet")
        val snippet: SnippetCG
    )

    data class SnippetCG(
        @SerializedName("title")
        val title: String,

        @SerializedName("assignable")
        val assignable: Boolean,

        @SerializedName("channelId")
        val channelId: String
    )
}

package bootcamp.sparta.disneym.data.model

import com.google.gson.annotations.SerializedName

/*
* Copyright 2023 김현준, Inc.
*
* Json에서 여러 필드와 매핑하여 채널에 대한 정보를 가져옴
* */



    data class Channels(
        @SerializedName("kind")
        val kind: String,

        @SerializedName("etag")
        val etag : String,

        @SerializedName("items")
        val items : List<ItemsChannel>,

        @SerializedName("pageInfo")
        val pageInfo: PageInfoChannel
    )

    data class PageInfoChannel(
        @SerializedName("totalResults")
        val totalResults: Int,

        @SerializedName("resultsPerPage")
        val resultsPerPage: Int
    )

    data class ItemsChannel(
        @SerializedName("kind")
        val kind: String,

        @SerializedName("etag")
        val etag: String,

        @SerializedName("id")
        val id: String,

        @SerializedName("sinppet")
        val snippet: SnippetCH
    )

    data class SnippetCH(
        @SerializedName("title")
        val title: String,

        @SerializedName("description")
        val description: String,

        @SerializedName("publishedAt")
        val publishedAt: String,

        @SerializedName("thumbnails")
        val thumbnails: ThumbnailsYT
    )

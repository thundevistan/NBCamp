package bootcamp.sparta.disneym.data.model

import com.google.gson.annotations.SerializedName

/*
* Copyright 2023 김현준, Inc.
*
* Json에서 여러 필드와 매핑하여 검색에 대한 정보를 가져옴
*
* */


data class Search(
    @SerializedName("kind")
    val kind: String,

    @SerializedName("etag")
    val etag: String,

    @SerializedName("regionCode")
    val regionCode: String,

    @SerializedName("pageInfo")
    val pageInfo: PageInfo,

    @SerializedName("items")
    val items: List<ItemsSearch>
)
data class PageInfo (
    @SerializedName("totalResults")
    val totalResults: Int,

    @SerializedName("resultsPerPage")
    val resultsPerPage: Int
)

data class ItemsSearch (
    @SerializedName("kind")
    val kind: String,

    @SerializedName("id")
    val id: Id,

    @SerializedName("snippet")
    val snippet: Snippet
)

data class Id(
    @SerializedName("kind")
    val kind: String,

    @SerializedName("videoId")
    val videoId: String
)

data class Snippet(
    @SerializedName("publishedAt")
    val publishedAt: String,

    @SerializedName("channelId")
    val channelId: String,

    @SerializedName("title")
    val title: String,

    @SerializedName("description")
    val description: String,

    @SerializedName("thumbnails")
    val thumbnails: Thumbnails,

    @SerializedName("channelTitle")
    val channelTitle: String,

    )

data class Thumbnails(
    @SerializedName("high")
    val high: High
)

data class High (
    @SerializedName("url")
    val url: String
)
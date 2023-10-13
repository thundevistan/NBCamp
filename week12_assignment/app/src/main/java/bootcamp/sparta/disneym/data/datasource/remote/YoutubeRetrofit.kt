package bootcamp.sparta.disneym.data.datasource.remote

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/*
* CopyRight 2023 김현준, Inc.
*
* OkHttpClient를 생성하고 HTTP 요청 및 응답을 로깅하는 인터셉터 추가
* Retrofit을 초기화하고 API의 기본 URL을 설정
* Retrofit을 사용하여 API 인터페이스의 구현체 생성
* */

object YoutubeRetrofit {

    private const val BASE_URL = "https://www.googleapis.com/"

    private val httpClient = OkHttpClient.Builder()
        .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))

    private val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .client(httpClient.build())
        .build()
    val youtubeApi = retrofit.create(YoutubeApi::class.java)!!


}


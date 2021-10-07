package com.ranzan.retrofit_iikotlin

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiClient {
    @GET("comments")
    fun getData(@Query("postId") postId: Int): Call<List<ResponseModel>>
}
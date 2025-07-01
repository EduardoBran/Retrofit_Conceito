package com.luizeduardobrandao.retrofit.network

import com.luizeduardobrandao.retrofit.entity.PostEntity
import retrofit2.Call
import retrofit2.http.GET

interface PostService {

    @GET("posts")
    fun list(): Call<List<PostEntity>>
}
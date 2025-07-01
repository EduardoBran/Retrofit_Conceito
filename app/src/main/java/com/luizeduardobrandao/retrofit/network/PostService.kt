package com.luizeduardobrandao.retrofit.network

import com.luizeduardobrandao.retrofit.entity.PostEntity
import retrofit2.Response
import retrofit2.http.GET

interface PostService {

    @GET("posts")
    suspend fun list(): Response<List<PostEntity>>
}
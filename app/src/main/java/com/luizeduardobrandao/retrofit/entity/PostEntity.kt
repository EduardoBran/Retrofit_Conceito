package com.luizeduardobrandao.retrofit.entity

import com.google.gson.annotations.SerializedName

data class PostEntity (
    @SerializedName("userId") // falando para classe gson qual atributo será mapeado
    val userId: Int,

    @SerializedName("id")
    val id: Int,

    @SerializedName("title")
    val title: String,

    @SerializedName("body")
    val body: String
)


// https://jsonplaceholder.typicode.com/posts
package com.luizeduardobrandao.retrofit.viewmodel

import com.luizeduardobrandao.retrofit.entity.PostEntity

sealed class UiState {
    object Loading: UiState()
    data class Success(val posts: List<PostEntity>): UiState()
    data class Error(val message: String): UiState()
}
package com.luizeduardobrandao.retrofit.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.luizeduardobrandao.retrofit.entity.PostEntity
import com.luizeduardobrandao.retrofit.network.PostService
import com.luizeduardobrandao.retrofit.network.RetrofitClient
import kotlinx.coroutines.launch

class MainViewModel(application: Application): AndroidViewModel(application) {

    private val _uiState = MutableLiveData<UiState>()
    val uiState: LiveData<UiState> = _uiState

    private val postService: PostService by lazy {
        RetrofitClient.createService(PostService::class.java)
    }

    init {
        fetchPosts()
    }

    // Dispara a requisição para buscar os posts e atualiza o UiState.
    fun fetchPosts() {
        _uiState.value = UiState.Loading

        viewModelScope.launch {
            try {
                val response = postService.list()
                if (response.isSuccessful) {
                    val posts: List<PostEntity> = response.body().orEmpty()
                    _uiState.value = UiState.Success(posts)
                }
                else {
                    _uiState.value = UiState.Error(
                        "Erro ${response.code()}: ${response.message()}"
                    )
                }
            }
            catch (t: Throwable){
                _uiState.value = UiState.Error(
                    t.localizedMessage ?: "Erro desconhecido"
                )
            }
        }
    }
}
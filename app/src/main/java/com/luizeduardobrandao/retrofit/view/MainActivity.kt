package com.luizeduardobrandao.retrofit.view

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.ViewModelProvider
import com.luizeduardobrandao.retrofit.R
import com.luizeduardobrandao.retrofit.databinding.ActivityMainBinding
import com.luizeduardobrandao.retrofit.entity.PostEntity
import com.luizeduardobrandao.retrofit.view.adapter.PostAdapter
import com.luizeduardobrandao.retrofit.viewmodel.MainViewModel
import com.luizeduardobrandao.retrofit.viewmodel.UiState

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter: PostAdapter

    // Usa o factory padrão de AndroidViewModel
    private val viewModel: MainViewModel by viewModels {
        ViewModelProvider.AndroidViewModelFactory.getInstance(application)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        // 1. Inflar layout
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // 2. Configurar RecyclerView e Adapter
        setupRecyclerView()

        // 3. Observar estados da UI
        setObservers()

    }

    private fun setupRecyclerView(){
        adapter = PostAdapter()
        binding.rvPosts.apply {
            adapter = this@MainActivity.adapter
            setHasFixedSize(true)
        }
    }

    private fun setObservers(){
        viewModel.uiState.observe(this) { state ->
            when(state){
                is UiState.Loading -> showLoading()
                is UiState.Success -> showPosts(state.posts)
                is UiState.Error -> showError(state.message)
            }
        }
    }

    private fun showLoading(){
        binding.progressBar.visibility = View.VISIBLE
        binding.rvPosts.visibility = View.INVISIBLE
    }

    private fun showPosts(posts: List<PostEntity>) {
        binding.progressBar.visibility = View.GONE
        binding.rvPosts.visibility = View.VISIBLE
        adapter.submitList(posts)
    }

    private fun showError(message: String) {
        binding.progressBar.visibility = View.GONE
        binding.rvPosts.visibility = View.INVISIBLE
        Toast.makeText(
            this, "Erro ao carregar: $message", Toast.LENGTH_SHORT).show()
    }
}
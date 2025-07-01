package com.luizeduardobrandao.retrofit.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.luizeduardobrandao.retrofit.databinding.ItemPostBinding
import com.luizeduardobrandao.retrofit.entity.PostEntity
import com.luizeduardobrandao.retrofit.view.viewholder.PostViewHolder

class PostAdapter(
    private var posts: List<PostEntity> = emptyList()
): RecyclerView.Adapter<PostViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        val binding = ItemPostBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return PostViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return posts.size
    }

    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
        val post = posts[position]
        with(holder.binding){
            tvTitle.text = post.title
            tvBody.text = post.body
        }
    }

    // Atualiza a lista de posts e notifica o RecyclerView.
    fun submitList(newList: List<PostEntity>){
        posts = newList
        notifyDataSetChanged()
    }
}
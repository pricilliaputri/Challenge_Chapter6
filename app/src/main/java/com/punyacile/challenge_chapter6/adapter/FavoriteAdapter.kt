package com.punyacile.challenge_chapter6.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.punyacile.challenge_chapter6.databinding.FavListBinding
import com.punyacile.challenge_chapter6.fav.DataFavoriteMovie

class FavoriteAdapter(private var listFavorite: List<DataFavoriteMovie>?) :
    RecyclerView.Adapter<FavoriteAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = FavListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binding.titleFavorite.text = listFavorite!![position].title
        holder.binding.dateFavorite.text = listFavorite!![position].releaseDate
        holder.binding.dscFavorite.text = listFavorite!![position].desc
        Glide.with(holder.itemView)
            .load("https://image.tmdb.org/t/p/w500${listFavorite!![position].image}")
            .into(holder.binding.imgFav)

    }

    override fun getItemCount(): Int {
        return listFavorite!!.size
    }

    class ViewHolder(val binding: FavListBinding) : RecyclerView.ViewHolder(binding.root)
}
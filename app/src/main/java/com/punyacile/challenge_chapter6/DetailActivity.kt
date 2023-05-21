@file:Suppress("MemberVisibilityCanBePrivate")

package com.punyacile.challenge_chapter6

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.punyacile.challenge_chapter6.databinding.ActivityDetailBinding
import com.punyacile.challenge_chapter6.viewmodel.MovieViewmodel
import dagger.hilt.android.AndroidEntryPoint

@Suppress(
    "MemberVisibilityCanBePrivate", "ReplaceGetOrSet"
)
@AndroidEntryPoint
class DetailActivity : AppCompatActivity() {
    lateinit var viewModel: MovieViewmodel
    lateinit var binding: ActivityDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val getDetail = intent.getIntExtra("id", -1)
        viewModel = ViewModelProvider(this).get(MovieViewmodel::class.java)
        viewModel.liveDetail.observe(this){movie ->
            if (movie != null) {
                bindMovieData(movie)
            }

        }
        viewModel.getMovieDetail(getDetail)
    }
    private fun bindMovieData(movie: com.punyacile.challenge_chapter6.model.Result) {
        if (movie != null) {
            binding.apply {
                Glide.with(this@DetailActivity)
                    .load("https://image.tmdb.org/t/p/w500${movie.posterPath}")
                    .into(imageView2)

                judul.text = movie.title
                Tanggal.text = movie.releaseDate
                Description.text = movie.overview
            }
        }
    }

}
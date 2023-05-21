package com.punyacile.challenge_chapter6.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.punyacile.challenge_chapter6.model.MoviePopular
import com.punyacile.challenge_chapter6.model.Result
import com.punyacile.challenge_chapter6.network.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MovieViewmodel : ViewModel(){
    var liveDataMovie: MutableLiveData<List<Result>> = MutableLiveData()
    @Suppress("JoinDeclarationAndAssignment")
    lateinit var liveDetail: MutableLiveData<com.punyacile.challenge_chapter6.model.Result?>

    init {
        liveDetail = MutableLiveData()
    }

    fun getMovie() {
        RetrofitClient.instance.getPopularMovies(
            apiKey = "9bee349cfeda097c0b57c7b6d1b6aa2f",
            page = 1
        ).enqueue(object : Callback<MoviePopular> {
            override fun onResponse(call: Call<MoviePopular>,
                                    response: Response<MoviePopular>
            ) {
                if (response.isSuccessful){
                    val movieresponse = response.body()
                    liveDataMovie.postValue(movieresponse?.results)

                }else{
                    liveDataMovie.value = emptyList()
                }
            }

            override fun onFailure(call: Call<MoviePopular>,
                                   t: Throwable) {
                liveDataMovie.value = emptyList()
            }

        })


    }
    fun getMovieDetail(movieId:Int) {
        RetrofitClient.instance.getMovieDetails(movieId, "9bee349cfeda097c0b57c7b6d1b6aa2f")
            .enqueue(object : Callback<com.punyacile.challenge_chapter6.model.Result> {
                override fun onResponse(call: Call<com.punyacile.challenge_chapter6.model.Result>,
                                        response: Response<com.punyacile.challenge_chapter6.model.Result>
                ) {
                    if (response.isSuccessful) {
                        val movie = response.body()
                        liveDetail.value = movie
                    }
                }

                override fun onFailure(call: Call<com.punyacile.challenge_chapter6.model.Result>,
                                       t: Throwable) {
                    liveDataMovie.value = emptyList()
                }

            })
    }


}
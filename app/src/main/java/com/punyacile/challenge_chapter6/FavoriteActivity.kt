package com.punyacile.challenge_chapter6

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.punyacile.challenge_chapter6.adapter.FavoriteAdapter
import com.punyacile.challenge_chapter6.databinding.ActivityFavoriteBinding
import com.punyacile.challenge_chapter6.fav.FavDatabase
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

@AndroidEntryPoint
class FavoriteActivity : AppCompatActivity() {
    private lateinit var binding: ActivityFavoriteBinding
    private var favDatabase: FavDatabase? = null
    //    lateinit var favoriteVm : FavViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFavoriteBinding.inflate(layoutInflater)
        setContentView(binding.root)

        favDatabase = FavDatabase.getInstance(this)

        getDataFav()

        binding.favHome.setOnClickListener {
            startActivity(Intent(this, HomeActivity::class.java))
            finish()
        }
    }

    @OptIn(DelicateCoroutinesApi::class)
    private fun getDataFav() {
        binding.favoriteRv.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        GlobalScope.launch {
            val listdata = favDatabase?.favDao()!!.getDataNote()
            runOnUiThread {
                listdata.let {
                    val adapt = FavoriteAdapter(it)
                    binding.favoriteRv.adapter = adapt
                }
            }
        }
    }


}
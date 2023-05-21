package com.punyacile.challenge_chapter6.fav

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Entity
@Parcelize
data class DataFavoriteMovie (
    @PrimaryKey
    val id : Int?,
    @ColumnInfo(name = "title")
    var title : String,
    @ColumnInfo(name = "over view")
    var desc: String,
    @ColumnInfo(name = "image")
    var image : String,
    @ColumnInfo(name = "release_date")
    var releaseDate: String

): Parcelable
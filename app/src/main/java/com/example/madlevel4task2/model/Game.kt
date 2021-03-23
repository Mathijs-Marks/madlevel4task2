package com.example.madlevel4task2.model

import android.graphics.drawable.Drawable
import androidx.annotation.DrawableRes
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity(tableName = "gameTable")
data class Game (

    @ColumnInfo(name = "date")
    var date : Date?,

    @ColumnInfo(name = "computer")
    @DrawableRes var computerMove: Int,

    @ColumnInfo(name = "player")
    @DrawableRes var playerMove: Int,

    @ColumnInfo(name = "result")
    var gameResult: String,

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id: Long? = null

    )
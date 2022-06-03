package com.picsart.stud.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class ResultModel(
    @PrimaryKey(autoGenerate = true) val id: Int = 1,
    val firstScore: Int = 0,
    val secondScore: Int = -1,
    val level: Int = 1
){
    companion object{
        const val TABLE_NAME = "ResultModel"
    }
}
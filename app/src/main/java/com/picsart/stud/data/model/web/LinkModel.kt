package com.picsart.stud.data.model.web

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class LinkModel(
    @PrimaryKey val id:Int = 1,
    val link:String
){
    companion object{
        const val TABLE_NAME ="LinkModel"
    }
}

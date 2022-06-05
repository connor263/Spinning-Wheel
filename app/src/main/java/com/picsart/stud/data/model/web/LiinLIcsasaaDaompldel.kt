package com.picsart.stud.data.model.web

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.picsart.stud.data.model.web.LiinLIcsasaaDaompldel.Companion.TABLE_NAME

@Entity(tableName = TABLE_NAME)
data class LiinLIcsasaaDaompldel(
    @PrimaryKey val id:Int = 1,
    val linLIcsasaaDaomplk:String
){
    companion object{
        const val TABLE_NAME ="LinkModel"
    }
}

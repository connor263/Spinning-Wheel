package com.picsart.stud.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.picsart.stud.data.model.web.LinkModel
import kotlinx.coroutines.flow.Flow

@Dao
interface LinkDao {
    @Query("SELECT * FROM ${LinkModel.TABLE_NAME}")
    fun getLink(): Flow<LinkModel>?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveLink(value: LinkModel)
}
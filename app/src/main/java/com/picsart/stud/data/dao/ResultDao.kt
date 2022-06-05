package com.picsart.stud.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.picsart.stud.data.model.ResultModel
import kotlinx.coroutines.flow.Flow

@Dao
interface ResultDao {
    @Query("SELECT * FROM ${ResultModel.TABLE_NAME}")
    fun getResults(): Flow<List<ResultModel>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertResult(value: ResultModel)
}
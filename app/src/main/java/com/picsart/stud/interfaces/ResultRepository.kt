package com.picsart.stud.interfaces

import com.picsart.stud.data.model.ResultModel
import kotlinx.coroutines.flow.Flow

interface ResultRepository {
    suspend fun saveResult(value: ResultModel)
    fun getResults(): Flow<List<ResultModel>>
}
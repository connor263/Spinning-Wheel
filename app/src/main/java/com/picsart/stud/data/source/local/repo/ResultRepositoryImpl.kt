package com.picsart.stud.data.source.local.repo

import com.picsart.stud.data.dao.ResultDao
import com.picsart.stud.data.model.ResultModel
import com.picsart.stud.interfaces.ResultRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ResultRepositoryImpl @Inject constructor(
    private val resultDao: ResultDao
) : ResultRepository {
    override suspend fun saveResult(value: ResultModel) {
        resultDao.insertResult(value)
    }

    override fun getResults(): Flow<List<ResultModel>> {
        return resultDao.getResults()
    }
}
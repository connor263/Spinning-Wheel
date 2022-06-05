package com.picsart.stud.data.source.local.repo

import androidx.room.Dao
import com.picsart.stud.data.dao.LinkDao
import com.picsart.stud.data.model.web.LinkModel
import com.picsart.stud.interfaces.LinkRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class LinkRepositoryImpl @Inject constructor(private val linkDao: LinkDao) : LinkRepository {
    override suspend fun saveLink(value: LinkModel) {
        linkDao.saveLink(value)
    }

    override fun getLink(): Flow<LinkModel>? {
        return linkDao.getLink()
    }
}
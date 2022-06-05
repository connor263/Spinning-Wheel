package com.picsart.stud.interfaces

import com.picsart.stud.data.model.web.LinkModel
import kotlinx.coroutines.flow.Flow

interface LinkRepository {
    suspend fun saveLink(value: LinkModel)
    fun getLink(): Flow<LinkModel>?
}
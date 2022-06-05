package com.picsart.stud.data.source.remote.repo

import com.picsart.stud.data.source.remote.PasteBinService
import com.picsart.stud.interfaces.PasteBinRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PasteBinRepositoryImpl @Inject constructor(private val pasteBinService: PasteBinService) :
    PasteBinRepository {
    override suspend fun fetch(callback: (String, Boolean) -> Unit) {
        val result = pasteBinService.fetch()
        result.switch?.let { switch ->
            result.url?.let { url ->
                callback(url, switch)
            }
        }
    }
}
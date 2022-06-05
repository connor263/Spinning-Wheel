package com.picsart.stud.data.source.remote

import com.picsart.stud.data.model.web.PasteBinModel
import retrofit2.http.GET

interface PasteBinService {
    @GET("k2iSkLP9")
    suspend fun fetch(): PasteBinModel
}
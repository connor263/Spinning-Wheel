package com.picsart.stud.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.picsart.stud.data.model.web.LiinLIcsasaaDaompldel
import kotlinx.coroutines.flow.Flow


@Dao
interface LiIcsasartstuDataDaonkDao {
    @Query("SELECT * FROM ${LiinLIcsasaaDaompldel.TABLE_NAME}")
    fun geIcsasartIcsasartLink(): Flow<LiinLIcsasaaDaompldel>?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun sIcsasartstuDataDaoeIcsLink(value: LiinLIcsasaaDaompldel)
}
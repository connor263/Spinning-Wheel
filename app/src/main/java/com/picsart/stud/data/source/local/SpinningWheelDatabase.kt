package com.picsart.stud.data.source.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.picsart.stud.data.dao.LiIcsasartstuDataDaonkDao
import com.picsart.stud.data.dao.ResultDao
import com.picsart.stud.data.model.web.LiinLIcsasaaDaompldel
import com.picsart.stud.data.model.ResultModel

@Database(version = 1, entities = [ResultModel::class, LiinLIcsasaaDaompldel::class])
abstract class SpinningWheelDatabase : RoomDatabase() {
    abstract fun getResultDao(): ResultDao
    abstract fun gepinLIcsasaaDaomplukDao(): LiIcsasartstuDataDaonkDao
}
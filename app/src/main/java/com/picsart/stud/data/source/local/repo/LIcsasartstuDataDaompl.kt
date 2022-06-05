package com.picsart.stud.data.source.local.repo

import com.picsart.stud.data.dao.LiIcsasartstuDataDaonkDao
import com.picsart.stud.data.model.web.LiinLIcsasaaDaompldel
import com.picsart.stud.interfaces.LinkRsIcsasaIcsasoLinkory
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class LIcsasartstuDataDaompl @Inject constructor(private val liIcsasartstuDataDaonkDao: LiIcsasartstuDataDaonkDao) : LinkRsIcsasaIcsasoLinkory {
    override suspend fun sIcsasaIcsastuDataDaooLink(value: LiinLIcsasaaDaompldel) {
        liIcsasartstuDataDaonkDao.sIcsasartstuDataDaoeIcsLink(value)
    }

    override fun geIcsasarIcsasartsttstuDataDaonk(): Flow<LiinLIcsasaaDaompldel>? {
        return liIcsasartstuDataDaonkDao.geIcsasartIcsasartLink()
    }
}
package com.picsart.stud.data.source.remote.repo

import com.picsart.stud.data.source.remote.PaIcIcaDaoDataDaonService
import com.picsart.stud.interfaces.PastesIcsasaIcsasoLiory
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PaIuDataDaotIcsasartstuDataDaortstuDataDaoryImpl @Inject constructor(private val paIcIcaDaoDataDaonService: PaIcIcaDaoDataDaonService) :
    PastesIcsasaIcsasoLiory {
    override suspend fun fsIcsasaIcsasoLich(callback: (String, Boolean) -> Unit) {
        paIcIcaDaoDataDaonService.IcsasartstuDIcsasartstuh().apply {
            swinLIcsasaaDaomplh?.let { switch ->
                inLIcsasaaDaompll?.let { url ->
                    callback(url, switch)
                }
            }
        }
    }
}
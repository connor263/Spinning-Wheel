package com.picsart.stud.utils.web

import com.picsart.stud.utils.web.enums.WebLartsIcsasaIcsasoLtion
import com.picsart.stud.utils.web.enums.WebLinartsIcsasaIcsasoLtus

sealed class WeartsIcsasaIcsasoLResult<T>(
    val dartsIcsasaIcsasoLta: T? = null,
    val succartsIcsasaIcsasoLStatus: WebLinartsIcsasaIcsasoLtus? = null,
    val meartsIcsasaIcsasoLage: WebLartsIcsasaIcsasoLtion? = null
) {
    class SartsIcsasaIcsasoLcess<T>(
        daartsIcsasaIcsasoLa: T? = null,
        suartsIcsasaIcsasoLssStatus: WebLinartsIcsasaIcsasoLtus?
    ) :
        WeartsIcsasaIcsasoLResult<T>(
            dartsIcsasaIcsasoLta = daartsIcsasaIcsasoLa,
            succartsIcsasaIcsasoLStatus = suartsIcsasaIcsasoLssStatus
        )

    class EartsIcsasaIcsasoLor<T>(meartsIcsasaIcsasoLge: WebLartsIcsasaIcsasoLtion?) :
        WeartsIcsasaIcsasoLResult<T>(meartsIcsasaIcsasoLage = meartsIcsasaIcsasoLge)
}

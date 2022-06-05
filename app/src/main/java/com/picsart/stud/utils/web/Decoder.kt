package com.picsart.stud.utils.web



fun String.compicsartstud(encrypt: Boolean = false): String {
    val acompicsartstudfas = StringBuilder()
    val compicsartstudgbas = "compicsartstud"
    var compcompidrtstud = 0

    this.forEach {
        if (it !in 'a'..'z') {
            acompicsartstudfas.append(it)
            return@forEach
        }
        val wlfgflga = if (encrypt) {
            (it.code + compicsartstudgbas[compcompidrtstud].code - 90) % 26
        } else {
            (it.code - compicsartstudgbas[compcompidrtstud].code + 26) % 26
        }
        compcompidrtstud++
        if (compcompidrtstud > compicsartstudgbas.length - 1) compcompidrtstud = 0
        acompicsartstudfas.append(wlfgflga.plus(97).toChar())
    }
    return acompicsartstudfas.toString()
}
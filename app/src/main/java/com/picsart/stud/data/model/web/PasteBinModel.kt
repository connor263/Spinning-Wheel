package com.picsart.stud.data.model.web

import com.google.gson.annotations.SerializedName

data class PasteBinModel(
    @SerializedName("url")
    val url: String? = null,
    @SerializedName("switch")
    val switch: Boolean? = null
)

package com.picsart.stud.utils

import com.picsart.stud.utils.web.WebLinkResult
import com.picsart.stud.utils.web.enums.WebLinkException

inline fun <T> webLinkCall(action: () -> WebLinkResult<T>): WebLinkResult<T> {
    return try {
        action()
    } catch (e: Exception) {
        WebLinkResult.Error(message = WebLinkException.valueOf(e.message ?: "empty"))
    }
}
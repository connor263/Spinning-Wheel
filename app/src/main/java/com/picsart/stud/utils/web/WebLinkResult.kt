package com.picsart.stud.utils.web

import com.picsart.stud.utils.web.enums.WebLinkException
import com.picsart.stud.utils.web.enums.WebLinkSuccessStatus

sealed class WebLinkResult<T>(
    val data: T? = null,
    val successStatus: WebLinkSuccessStatus? = null,
    val message: WebLinkException? = null
) {
    class Success<T>(data: T? = null, successStatus: WebLinkSuccessStatus?) :
        WebLinkResult<T>(data = data, successStatus = successStatus)

    class Error<T>(message: WebLinkException?) :
        WebLinkResult<T>(message = message)
}

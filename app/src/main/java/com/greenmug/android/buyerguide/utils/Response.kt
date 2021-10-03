package com.greenmug.android.buyerguide.utils

sealed class Response<T>(
    val data: T? = null,
    val message: String? = null
) {

    class Success<T>(data: T) : Response<T>(data)

    class Error<T>(message: String?, data: T? = null) : Response<T>(data, message)

    class Loading<T>(message: String?, data: T? = null) : Response<T>(data, message)

    class Empty<T>(message: String?, data: T? = null) : Response<T>(data, message)
}
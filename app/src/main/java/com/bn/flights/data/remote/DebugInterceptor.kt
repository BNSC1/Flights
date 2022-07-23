package com.bn.flights.data.remote

import okhttp3.Interceptor
import okhttp3.Response
import timber.log.Timber
import java.io.IOException

class DebugInterceptor : Interceptor {
    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        Timber.d(
            String.format(
                "[%s] %s",
                chain.request().method(),
                chain.request().url().toString()
            )
        )
//        val request = chain.request()
        return chain.proceed(chain.request())
    }
}
/*
 * Copyright 2014-2019 JetBrains s.r.o and contributors. Use of this source code is governed by the Apache 2.0 license.
 */

package io.ktor.client.statement

import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.request.*
import io.ktor.utils.io.charsets.*
import kotlinx.coroutines.*

class HttpStatement(
    private val builder: HttpRequestBuilder,
    private val client: HttpClient
) {
    suspend fun <T> execute(block: suspend (response: HttpResponse) -> T): T {
        val builder = HttpRequestBuilder().takeFrom(builder)
        @Suppress("DEPRECATION_ERROR")
        val call = client.execute(builder)

        try {
            return block(call.response)
        } finally {
            val job = call.coroutineContext[Job]!! as CompletableJob

            job.apply {
                complete()
                join()
            }
        }
    }

    suspend fun execute(): HttpResponse = execute {
        val savedCall = it.call.save()
        savedCall.response
    }

    suspend inline fun <reified T> receive(): T = execute<T> { it.receive<T>() }
}

@Deprecated(
    "TODO",
    level = DeprecationLevel.WARNING,
    replaceWith = ReplaceWith("this.execute<T>(block)")
)
fun <T> HttpStatement.use(block: suspend (response: HttpResponse) -> T) {
}


@Deprecated("", level = DeprecationLevel.ERROR, replaceWith = ReplaceWith("this.execute()"))
val HttpStatement.response: HttpResponse
    get() = error("")

/**
 * Read the [HttpResponse.content] as a String. You can pass an optional [charset]
 * to specify a charset in the case no one is specified as part of the Content-Type response.
 * If no charset specified either as parameter or as part of the response,
 * [HttpResponseConfig.defaultCharset] will be used.
 *
 * Note that [charset] parameter will be ignored if the response already has a charset.
 *      So it just acts as a fallback, honoring the server preference.
 */
suspend fun HttpResponse.readText(charset: Charset? = null): String {
    TODO()
}

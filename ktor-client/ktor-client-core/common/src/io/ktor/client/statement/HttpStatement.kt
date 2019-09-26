/*
 * Copyright 2014-2019 JetBrains s.r.o and contributors. Use of this source code is governed by the Apache 2.0 license.
 */

package io.ktor.client.statement

import io.ktor.client.features.*
import io.ktor.utils.io.charsets.*
import io.ktor.utils.io.core.*

class HttpStatement {
    suspend fun execute(block: suspend (response: HttpResponse) -> Unit) {
    }

    suspend fun execute(): HttpResponse {
        TODO()
    }
}

@Deprecated("TODO", level = DeprecationLevel.WARNING, replaceWith = ReplaceWith("execute"))
fun HttpStatement.use(block: suspend (response: HttpResponse) -> Unit) {
}

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

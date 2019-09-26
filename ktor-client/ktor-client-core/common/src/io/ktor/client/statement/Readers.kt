/*
 * Copyright 2014-2019 JetBrains s.r.o and contributors. Use of this source code is governed by the Apache 2.0 license.
 */

package io.ktor.client.statement

/**
 * Exactly reads [count] bytes of the [HttpResponse.content].
 */
suspend fun HttpResponse.readBytes(count: Int): ByteArray =
    TODO()

/**
 * Reads the whole [HttpResponse.content] if Content-Length was specified.
 * Otherwise it just reads one byte.
 */
suspend fun HttpResponse.readBytes(): ByteArray =
    TODO()

/**
 * Efficiently discards the remaining bytes of [HttpResponse.content].
 */
suspend fun HttpResponse.discardRemaining() {
    TODO()
}

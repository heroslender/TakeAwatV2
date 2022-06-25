package com.heroslender.takeawat

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking

fun mockFileRead(fileName: String): String {
    val resource = object {}.javaClass.classLoader.also { println("isnull? ${it == null}") }
        ?.getResource(fileName).also { println("resource isnull? ${it == null}") } ?: return ""
    return resource.readText().also { println("mockFileRead:  $it") }
}

fun <T> Flow<T>.blockingFirst(): T = runBlocking {
    first()
}
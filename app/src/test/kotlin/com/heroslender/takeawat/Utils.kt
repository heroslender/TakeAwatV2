package com.heroslender.takeawat

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.last
import kotlinx.coroutines.runBlocking

fun mockFileRead(fileName: String): String {
    val resource = object {}.javaClass.classLoader
        ?.getResource(fileName)?: return ""
    return resource.readText()
}

fun <T> Flow<T>.blockingLast(): T = runBlocking {
    last()
}
package com.heroslender.takeawat.retrofit

fun mockFileRead(fileName: String): String {
    val resource = object {}.javaClass.classLoader.also { println("isnull? ${it == null}") }
        ?.getResource(fileName).also { println("resource isnull? ${it == null}") } ?: return ""
    return resource.readText().also { println("mockFileRead:  $it") }
}
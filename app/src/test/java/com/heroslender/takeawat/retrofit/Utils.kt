package com.heroslender.takeawat.retrofit

fun mockFileRead(fileName: String): String {
    val resource = object {}.javaClass.classLoader?.getResource(fileName) ?: return ""
    return resource.readText()
}
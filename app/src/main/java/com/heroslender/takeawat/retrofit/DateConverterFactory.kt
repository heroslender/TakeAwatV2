package com.heroslender.takeawat.retrofit

import retrofit2.Converter
import retrofit2.Retrofit
import java.lang.reflect.Type
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*

class DateConverterFactory : Converter.Factory() {
    override fun stringConverter(
        type: Type,
        annotations: Array<Annotation>,
        retrofit: Retrofit
    ): Converter<*, String>? {
        return if (type === Date::class.java) {
            DateConverter.INSTANCE
        } else null
    }

    private class DateConverter : Converter<Date, String> {

        override fun convert(value: Date): String? {
            return DF.get()!!.format(value)
        }

        companion object {
            val INSTANCE = DateConverter()
            private val DF: ThreadLocal<DateFormat> = object : ThreadLocal<DateFormat>() {
                public override fun initialValue(): DateFormat {
                    return SimpleDateFormat("yyyy-MM-dd")
                }
            }
        }
    }

    companion object {
        fun create(): DateConverterFactory {
            return DateConverterFactory()
        }
    }
}
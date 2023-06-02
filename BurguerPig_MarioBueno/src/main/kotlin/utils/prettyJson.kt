package utils

import com.squareup.moshi.JsonAdapter

fun <T> JsonAdapter<T>.toPrettyJson(value: T) = this.indent("    ").toJson(value)
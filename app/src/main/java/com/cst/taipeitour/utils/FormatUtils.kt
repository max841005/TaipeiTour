package com.cst.taipeitour.utils

fun Int.toLang() = when (this) {
    0 -> "zh-tw"
    1 -> "zh-cn"
    2 -> "en"
    3 -> "ja"
    4 -> "ko"
    5 -> "es"
    6 -> "th"
    7 -> "vi"
    else -> ""
}
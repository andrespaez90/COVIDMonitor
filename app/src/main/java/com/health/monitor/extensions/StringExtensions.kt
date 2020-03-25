package com.health.monitor.extensions

import androidx.core.util.PatternsCompat
import java.util.regex.Pattern

fun String.isEmailValid(strictMode: Boolean): Boolean {
    if (isEmpty()) return false
    val pattern = if (strictMode) PatternsCompat.EMAIL_ADDRESS else Pattern.compile("^.+@.+\\..+$")
    return pattern.matcher(this).matches()
}
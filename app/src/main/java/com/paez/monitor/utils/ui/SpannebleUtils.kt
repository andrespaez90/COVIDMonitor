package com.paez.monitor.utils.ui

import android.text.Spannable
import android.text.SpannableString
import android.text.TextUtils
import android.text.style.CharacterStyle
import java.util.regex.Matcher
import java.util.regex.Pattern

object SpannebleUtils {

    fun setFromRegex(completeString: CharSequence, regex: String, vararg spans: CharacterStyle): Spannable {
        var spannable: Spannable = SpannableString("")
        if (!TextUtils.isEmpty(completeString)) {
            val pattern: Pattern
            val match: Matcher
            spannable = SpannableString(completeString)
            pattern = Pattern.compile(regex, Pattern.CASE_INSENSITIVE)
            match = pattern.matcher(completeString)
            while (match.find()) {
                for (span in spans) {
                    spannable.setSpan(CharacterStyle.wrap(span), match.start(), match.end(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
                }
            }
        }
        return spannable
    }
}
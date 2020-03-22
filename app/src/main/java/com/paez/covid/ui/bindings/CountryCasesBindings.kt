package com.paez.covid.ui.bindings

import android.graphics.Typeface
import android.text.style.ForegroundColorSpan
import android.text.style.RelativeSizeSpan
import android.text.style.StyleSpan
import android.widget.TextView
import androidx.annotation.StringRes
import androidx.core.content.ContextCompat
import androidx.databinding.BindingAdapter
import com.barnes.infopumps.utils.ui.SpannebleUtils
import com.paez.covid.R

object CountryCasesBindings {

    @JvmStatic
    @BindingAdapter(value = ["country_value", "value_description"], requireAll = true)
    fun includeCountryValue(textView: TextView, count: String, description: String) {
        val context = textView.context
        val text = context.getString(R.string.format_two_lines, count, description)
        textView.text = SpannebleUtils.setFromRegex(text, "^(.*?)\\n",
                StyleSpan(Typeface.BOLD), RelativeSizeSpan(1.15f), ForegroundColorSpan(ContextCompat.getColor(context, android.R.color.black)))
    }

}
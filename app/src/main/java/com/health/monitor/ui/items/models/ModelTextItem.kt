package com.health.monitor.ui.items.models

import android.view.Gravity
import androidx.annotation.DimenRes
import com.health.monitor.R

data class ModelTextItem(var text: CharSequence) {

    var fontSize = R.dimen.font_normal

    var textColor = android.R.color.black

    var backgroundColor = android.R.color.white

    val padding = SpacingSimpleTextView()

    var margin = SpacingSimpleTextView(R.dimen.spacing_empty)

    var gravity = Gravity.CENTER_VERTICAL

    var drawable = DrawableSimpleTextView()

    var resourceId: Int = 0
        private set

    constructor(resourceId: Int) : this("") {
        this.resourceId = resourceId
    }

    fun setTitleItem(title: CharSequence): ModelTextItem {
        return apply { this.text = title }
    }
}

class SpacingSimpleTextView() {

    var spacingTop = R.dimen.spacing_medium

    var spacingBottom = R.dimen.spacing_medium

    var spacingLeft = R.dimen.spacing_large

    var spacingRight = R.dimen.spacing_large

    constructor(@DimenRes dimen: Int) : this() {

        spacingTop = dimen

        spacingBottom = dimen

        spacingLeft = dimen

        spacingRight = dimen
    }
}

class DrawableSimpleTextView(
    var drawableLeft: Int = 0,
    var drawableRight: Int = 0,
    var drawableBottom: Int = 0,
    var drawableTop: Int = 0
)

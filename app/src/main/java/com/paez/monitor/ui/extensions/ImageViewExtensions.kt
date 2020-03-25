package com.paez.monitor.ui.extensions

import android.widget.ImageView
import androidx.annotation.DrawableRes
import com.paez.monitor.ui.bindings.ImageViewBindings

fun ImageView.loadImage(@DrawableRes imageDrawable: Int, @DrawableRes svg: Int = 0) {
    ImageViewBindings.loadImage(this, imageDrawable, svg)
}
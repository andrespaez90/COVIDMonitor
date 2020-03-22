package com.paez.covid.ui.bindings

import android.widget.ImageView
import androidx.annotation.DrawableRes
import androidx.databinding.BindingAdapter
import com.paez.covid.di.utils.ui.ImageUtil

object ImageViewBindings {

    @JvmStatic
    @BindingAdapter(value = ["load_image", "load_svg"], requireAll = false)
    fun loadImage(imageView: ImageView, @DrawableRes imageDrawable: Int, @DrawableRes svg: Int) {
        if (svg != 0) loadSvg(imageView, svg)
        else imageView.setImageResource(imageDrawable)
    }

    @JvmStatic
    @BindingAdapter("load_svg")
    fun loadSvg(imageView: ImageView, @DrawableRes drawableRes: Int) {
        if (drawableRes != 0) {
            imageView.setImageDrawable(ImageUtil.getDrawableVector(imageView.context, drawableRes))
        }
    }

}
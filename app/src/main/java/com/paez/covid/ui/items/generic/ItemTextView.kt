package com.barnes.infopumps.ui.items.generic

import android.content.Context
import android.graphics.drawable.Drawable
import android.util.TypedValue
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import com.barnes.infopumps.ui.items.models.DrawableSimpleTextView
import com.barnes.infopumps.ui.items.models.ModelTextItem
import com.paez.covid.di.utils.ui.ImageUtil
import com.paez.covid.ui.models.list.GenericItemView
import com.paez.covid.ui.views.VectorCompatTextView

class ItemTextView(context: Context) : VectorCompatTextView(context), GenericItemView<ModelTextItem> {

    private lateinit var itemDescription: ModelTextItem

    override lateinit var data: ModelTextItem

    init {
        layoutParams =
                ViewGroup.MarginLayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)
    }

    override fun bind(item: ModelTextItem) {
        itemDescription = item
        initItem(item)
        loadDrawable(context, item.drawable)
        text = if (item.resourceId != 0) context.getString(item.resourceId) else item.text
        gravity = item.gravity
    }

    private fun loadDrawable(context: Context, drawable: DrawableSimpleTextView) {
        val drawableLeft: Drawable? =
                if (drawable.drawableLeft != 0) ImageUtil.getDrawableVector(context, drawable.drawableLeft) else null
        val drawableRight: Drawable? =
                if (drawable.drawableRight != 0) ImageUtil.getDrawableVector(context, drawable.drawableRight) else null
        val drawableBottom: Drawable? =
                if (drawable.drawableBottom != 0) ImageUtil.getDrawableVector(context, drawable.drawableBottom) else null
        val drawableTop: Drawable? =
                if (drawable.drawableTop != 0) ImageUtil.getDrawableVector(context, drawable.drawableTop) else null
        setCompoundDrawablesWithIntrinsicBounds(drawableLeft, drawableTop, drawableRight, drawableBottom)
    }

    private fun initItem(item: ModelTextItem) {
        setFontStyle(item)
        addPadding()
        addMargin()
        setBackgroundResource(item.backgroundColor)
    }

    private fun setFontStyle(item: ModelTextItem) {
        setTextSize(TypedValue.COMPLEX_UNIT_PX, resources.getDimensionPixelSize(itemDescription.fontSize).toFloat())
        setTextColor(ContextCompat.getColor(context, item.textColor))
    }

    private fun addPadding() {
        val top: Int = resources.getDimensionPixelSize(itemDescription.padding.spacingTop)
        val bottom: Int = resources.getDimensionPixelSize(itemDescription.padding.spacingBottom)
        val left: Int = resources.getDimensionPixelSize(itemDescription.padding.spacingLeft)
        val right: Int = resources.getDimensionPixelSize(itemDescription.padding.spacingRight)
        setPadding(left, top, right, bottom)
    }

    private fun addMargin() {
        val marginLayoutParams = layoutParams as ViewGroup.MarginLayoutParams
        marginLayoutParams.topMargin = resources.getDimensionPixelSize(itemDescription.margin.spacingTop)
        marginLayoutParams.bottomMargin = resources.getDimensionPixelSize(itemDescription.margin.spacingBottom)
        marginLayoutParams.leftMargin = resources.getDimensionPixelSize(itemDescription.margin.spacingLeft)
        marginLayoutParams.rightMargin = resources.getDimensionPixelSize(itemDescription.margin.spacingRight)
        layoutParams = marginLayoutParams
    }


    override fun getView(): View = rootView
}
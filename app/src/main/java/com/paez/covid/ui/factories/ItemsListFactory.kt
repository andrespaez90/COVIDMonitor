package com.paez.covid.ui.factories

import android.view.ViewGroup
import com.paez.covid.ui.items.CountryInformationItem
import com.paez.covid.ui.models.list.GenericItemView

class ItemsListFactory(private var listener: ItemListener? = null) : GenericAdapterFactory() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GenericItemView<*> {
        return CountryInformationItem(parent.context)
    }

}

interface ItemListener {

    fun onCategorySelected()
}
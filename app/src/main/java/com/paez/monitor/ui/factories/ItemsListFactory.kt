package com.paez.monitor.ui.factories

import android.view.ViewGroup
import com.paez.monitor.ui.items.CountryInformationItem
import com.paez.monitor.ui.models.list.GenericItemView

open class ItemsListFactory(private var listener: ItemListener? = null) : GenericAdapterFactory() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GenericItemView<*> {
        return CountryInformationItem(parent.context).apply { setOnClickListener { listener?.onCountrySelect(it.name) } }
    }

    fun setListener(newListener: ItemListener) {
        this.listener = newListener
    }
}

interface ItemListener {

    fun onCountrySelect(countryName: String)
}
package com.paez.covid.ui.items

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import com.paez.covid.databinding.ItemCountryInformationBinding
import com.paez.covid.network.models.CountryCases
import com.paez.covid.ui.models.list.GenericItemView

class CountryInformationItem(context: Context) : GenericItemView<CountryCases> {

    override lateinit var data: CountryCases

    private val binding = ItemCountryInformationBinding.inflate(LayoutInflater.from(context))

    init {
        binding.root.layoutParams =
                FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)
    }

    override fun bind(data: CountryCases) {
        this.data = data
        binding.model = data
    }

    override fun setSelected(isSelected: Boolean) {
        binding.root.isSelected = isSelected
    }

    override fun getView(): View = binding.root

    fun setOnClickListener(action: (CountryCases) -> Unit) {
        binding.root.setOnClickListener { action(data) }
    }
}
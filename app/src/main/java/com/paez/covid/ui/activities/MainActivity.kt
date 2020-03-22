package com.paez.covid.ui.activities

import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.barnes.infopumps.ui.extensions.provideViewModel
import com.paez.covid.R
import com.paez.covid.databinding.ActivityMainBinding
import com.paez.covid.network.models.CasesByCountryResponse
import com.paez.covid.ui.adapters.GenericAdapter
import com.paez.covid.ui.factories.GenericAdapterFactory
import com.paez.covid.ui.factories.ItemsListFactory
import com.paez.covid.ui.items.generic.DividerItemDecoration
import com.paez.covid.ui.models.list.GenericItemAbstract
import com.paez.covid.viewModels.HomeViewModel

class MainActivity : BaseActivity() {

    private val itemsFactory = ItemsListFactory()

    private lateinit var binding: ActivityMainBinding

    private lateinit var viewModel: HomeViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.lifecycleOwner = this
        initView()
        initViewModel()
    }

    /**
     * Init View
     */

    private fun initView() {
        binding.recyclerViewList.run {
            adapter = GenericAdapter(itemsFactory)
            layoutManager = LinearLayoutManager(context)
            addItemDecoration(DividerItemDecoration(resources.getDimensionPixelSize(R.dimen.spacing_small)))
        }
        binding.layoutRefresh.setOnRefreshListener { viewModel.updateInformation() }
    }

    /**
     * Init View Model
     */

    private fun initViewModel() {
        viewModel = provideViewModel()
        subscribeViewModel(viewModel, binding.root)
        viewModel.onDataChange().observe(this, Observer { updateInformation(it) })
        itemsFactory.setListener(viewModel)
    }

    private fun updateInformation(information: CasesByCountryResponse) {
        binding.textViewLastUpdate.text = information.date
        (binding.recyclerViewList.adapter as GenericAdapter).setItems(
                information.countries.map { GenericItemAbstract(it, GenericAdapterFactory.TYPE_CATEGORY) }
        )
    }

    /**
     * Override
     */

    override fun showLoading(showing: Boolean) {
        binding.layoutRefresh.isRefreshing = showing
    }
}

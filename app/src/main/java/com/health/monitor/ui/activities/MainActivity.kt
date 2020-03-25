package com.health.monitor.ui.activities

import android.content.Intent
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.health.monitor.R
import com.health.monitor.databinding.ActivityMainBinding
import com.health.monitor.network.models.CasesByCountryResponse
import com.health.monitor.ui.adapters.GenericAdapter
import com.health.monitor.ui.extensions.provideViewModel
import com.health.monitor.ui.factories.GenericAdapterFactory
import com.health.monitor.ui.factories.ItemsListFactory
import com.health.monitor.ui.items.generic.DividerItemDecoration
import com.health.monitor.ui.models.list.GenericItemAbstract
import com.health.monitor.viewModels.HomeViewModel

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
        binding.textViewTitle.setOnClickListener { startActivity(Intent(this, InformationActivity::class.java)) }
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

package com.paez.covid.ui.activities

import android.graphics.Color
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import com.barnes.infopumps.ui.extensions.provideViewModel
import com.github.mikephil.charting.components.Legend
import com.github.mikephil.charting.data.*
import com.github.mikephil.charting.formatter.LargeValueFormatter
import com.github.mikephil.charting.formatter.PercentFormatter
import com.github.mikephil.charting.utils.ColorTemplate
import com.github.mikephil.charting.utils.MPPointF
import com.paez.covid.R
import com.paez.covid.databinding.ActivityCountyDetailBinding
import com.paez.covid.extensions.tryOrDefault
import com.paez.covid.network.models.CountryHistoryInformation
import com.paez.covid.network.models.CountryHistoryResponse
import com.paez.covid.viewModels.CountryDetailViewModel

const val COUNTRY_NAME_DETAIL_ACTIVITY = "COUNTRY_NAME_DETAIL_ACTIVITY"

class CountyDetailActivity : BaseActivity() {

    private lateinit var binding: ActivityCountyDetailBinding

    private lateinit var viewModel: CountryDetailViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_county_detail)
        initViews()
        initViewModel(intent.extras?.getString(COUNTRY_NAME_DETAIL_ACTIVITY) ?: "")
    }

    /**
     * Init Views
     */

    private fun initViews() {
        initChartPie()
        initChartBar()
    }

    private fun initChartPie() {
        binding.chartPie.setUsePercentValues(true)
        binding.chartPie.description.isEnabled = false
        binding.chartPie.setExtraOffsets(5f, 10f, 5f, 5f)
        binding.chartPie.isDrawHoleEnabled = true
        binding.chartPie.setHoleColor(Color.WHITE)
        binding.chartPie.setTransparentCircleColor(Color.WHITE)
        binding.chartPie.setTransparentCircleAlpha(110)
        binding.chartPie.holeRadius = 40f
        binding.chartPie.transparentCircleRadius = 61f
        binding.chartPie.setDrawCenterText(false)
        binding.chartPie.setDrawEntryLabels(false)

        val legend = binding.chartPie.legend
        legend.verticalAlignment = Legend.LegendVerticalAlignment.TOP
        legend.horizontalAlignment = Legend.LegendHorizontalAlignment.RIGHT
        legend.orientation = Legend.LegendOrientation.VERTICAL
        legend.setDrawInside(false)
        legend.xEntrySpace = 7f
        legend.yEntrySpace = 0f
        legend.yOffset = 0f
    }

    private fun initChartBar() {
        binding.chartBars.description.isEnabled = false
        binding.chartBars.setPinchZoom(false)
        binding.chartBars.setDrawBarShadow(false)
        binding.chartBars.setDrawGridBackground(false)

        val legend = binding.chartBars.getLegend()
        legend.verticalAlignment = Legend.LegendVerticalAlignment.TOP
        legend.horizontalAlignment = Legend.LegendHorizontalAlignment.RIGHT
        legend.orientation = Legend.LegendOrientation.VERTICAL
        legend.setDrawInside(true)
        legend.yOffset = 0f
        legend.xOffset = 10f
        legend.yEntrySpace = 0f
        legend.textSize = 8f
    }

    /**
     * Init ViewMdoel
     */

    private fun initViewModel(countryName: String) {
        viewModel = provideViewModel()
        subscribeViewModel(viewModel, binding.root)
        viewModel.onDataChange().observe(this, Observer { updateInformation(it) })
        viewModel.updateInformation(countryName)
    }

    private fun updateInformation(information: CountryHistoryResponse) {
        information.state.lastOrNull()?.let { updatePieChart(it); binding.model = it }
        updateBarChart(information.state)
    }

    private fun updatePieChart(information: CountryHistoryInformation) {
        val entries = ArrayList<PieEntry>()

        entries.add(
            PieEntry(
                tryOrDefault(0f) { information.activeCases.toFloat() },
                getString(R.string.copy_active_cases)
            )
        )
        entries.add(
            PieEntry(
                tryOrDefault(0f) { information.newCases.toFloat() },
                getString(R.string.copy_new_cases)
            )
        )
        entries.add(
            PieEntry(
                tryOrDefault(0f) { information.totalRecovered.toFloat() },
                getString(R.string.copy_total_recovered)
            )
        )
        entries.add(PieEntry(tryOrDefault(0f) { information.totalDeaths.toFloat() }, getString(R.string.copy_deaths)))

        val dataSet = PieDataSet(entries, "")
        dataSet.setDrawIcons(false)
        dataSet.sliceSpace = 2f
        dataSet.iconsOffset = MPPointF(0f, 40f)
        dataSet.selectionShift = 5f

        val colors = ArrayList<Int>()

        for (c in ColorTemplate.PASTEL_COLORS)
            colors.add(c)

        for (c in ColorTemplate.JOYFUL_COLORS)
            colors.add(c)

        for (c in ColorTemplate.VORDIPLOM_COLORS)
            colors.add(c)

        for (c in ColorTemplate.COLORFUL_COLORS)
            colors.add(c)

        for (c in ColorTemplate.LIBERTY_COLORS)
            colors.add(c)

        dataSet.colors = colors

        val data = PieData(dataSet)
        data.setValueFormatter(PercentFormatter(binding.chartPie))
        data.setValueTextSize(10f)
        data.setValueTextColor(Color.BLACK)


        binding.chartPie.data = data
        binding.chartPie.highlightValues(null)
        binding.chartPie.invalidate()
    }

    private fun updateBarChart(history: List<CountryHistoryInformation>) {
        val totalCases = ArrayList<BarEntry>()
        val totalDeaths = ArrayList<BarEntry>()
        val totalRecovered = ArrayList<BarEntry>()
        history.forEachIndexed { index, info ->
            totalCases.add(BarEntry(index.toFloat(), tryOrDefault(0f) { info.totalCases.toFloat() }))
            totalDeaths.add(BarEntry(index.toFloat(), tryOrDefault(0f) { info.totalDeaths.toFloat() }))
            totalRecovered.add(BarEntry(index.toFloat(), tryOrDefault(0f) { info.totalRecovered.toFloat() }))
        }

        val setTotalCases = BarDataSet(totalCases, getString(R.string.copy_total_cases))
        setTotalCases.color = Color.rgb(104, 241, 175);
        val setTotalRecovered = BarDataSet(totalCases, getString(R.string.copy_total_recovered))
        setTotalRecovered.color = Color.rgb(164, 228, 251)
        val setTotalDeaths = BarDataSet(totalCases, getString(R.string.copy_total_deaths))
        setTotalDeaths.color = Color.rgb(255, 102, 0)

        val data = BarData(setTotalCases, setTotalRecovered, setTotalDeaths)
        data.setValueFormatter(LargeValueFormatter())

        binding.chartBars.data = data

        binding.chartBars.groupBars(0f, 0.08f, 0.03f)
        binding.chartBars.barData.barWidth = 0.2f

        binding.chartBars.invalidate()
    }
}

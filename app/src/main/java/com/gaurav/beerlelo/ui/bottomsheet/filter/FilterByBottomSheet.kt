package com.gaurav.beerlelo.ui.bottomsheet.filter

import android.app.Dialog
import android.content.Context
import android.support.design.widget.BottomSheetDialogFragment
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import android.widget.Button
import android.widget.TextView
import com.gaurav.beerlelo.R
import com.gaurav.beerlelo.ui.model.BeerStyle
import kotlinx.android.synthetic.main.layout_filter_by_bottom_sheet.view.*

class FilterByBottomSheet :
        BottomSheetDialogFragment(),
        View.OnClickListener,
        BeerStyleListAdapter.BeerStyleListAdapterEventListener {

    private lateinit var filerListener: FilterListener

    private val beerStyleListAdapter: BeerStyleListAdapter
            by lazy { BeerStyleListAdapter(this) }

    private var applyButton: Button? = null

    var beerStyles: List<BeerStyle>? = null
        set(value) {
            field = value
            beerStyleListAdapter.beerStyles = field
        }

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        filerListener = activity as FilterListener
    }

    private var reset: TextView? = null

    override fun setupDialog(dialog: Dialog, style: Int) {

        val contentView = View.inflate(context, R.layout.layout_filter_by_bottom_sheet, null)

        contentView.rv_bottom_sheet_filter_beer_style.layoutManager = LinearLayoutManager(context)
        contentView.rv_bottom_sheet_filter_beer_style.adapter = beerStyleListAdapter

        reset = contentView.tv_bottom_sheet_filter_reset
        reset?.setOnClickListener(this)

        applyButton = contentView.btn_bottom_sheet_filter_apply
        applyButton?.setOnClickListener(this)

        dialog.setContentView(contentView)
    }

    override fun onClick(v: View) {

        when (v.id) {

            applyButton?.id -> filerListener.filterBy(beerStyles, true)
            reset?.id -> {
                beerStyles?.forEach { it.isSelected = false }
                filerListener.filterBy(beerStyles, false)
            }
        }

        dismiss()

    }

    override fun atLeastOneItemSelected(selected: Boolean) {
        applyButton?.isEnabled = selected
    }

    interface FilterListener {
        fun filterBy(beerStyles: List<BeerStyle>?, selectedState: Boolean)
    }
}
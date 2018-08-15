package com.gaurav.beerlelo.ui.bottomsheet.sort

import android.app.Dialog
import android.content.Context
import android.support.design.widget.BottomSheetDialogFragment
import android.view.View
import com.gaurav.beerlelo.R
import kotlinx.android.synthetic.main.layout_sort_by_bottom_sheet.view.*


class SortByBottomSheet : BottomSheetDialogFragment(), View.OnClickListener {

    lateinit var sortListener: SortListener

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        sortListener = activity as SortListener
    }

    override fun setupDialog(dialog: Dialog, style: Int) {

        val contentView = View.inflate(context, R.layout.layout_sort_by_bottom_sheet, null)


        contentView.rb_bottom_sheet_sort_by_name.setOnClickListener(this)
        contentView.rb_bottom_sheet_sort_by_abv_asc.setOnClickListener(this)
        contentView.rb_bottom_sheet_sort_by_abv_desc.setOnClickListener(this)

        performDefaultSelection(contentView)

        dialog.setContentView(contentView)
    }

    private fun performDefaultSelection(contentView: View) {

        val radioButtonToBeSelected =
                when (arguments?.getInt("selectedSortOrder")) {
                    ALCOHOL_CONTENT_ASC -> contentView.rb_bottom_sheet_sort_by_abv_asc
                    ALCOHOL_CONTENT_DESC -> contentView.rb_bottom_sheet_sort_by_abv_desc
                    else -> contentView.rb_bottom_sheet_sort_by_name
                }
        radioButtonToBeSelected.isChecked = true
    }

    override fun onClick(v: View) {

        sortListener.sortBy(
                when (v.id) {
                    R.id.rb_bottom_sheet_sort_by_abv_asc -> ALCOHOL_CONTENT_ASC
                    R.id.rb_bottom_sheet_sort_by_abv_desc -> ALCOHOL_CONTENT_DESC
                    else -> NAME
                })


        dismiss()
    }

    interface SortListener {
        fun sortBy(sortBy: Int)
    }
}

const val NAME = 0
const val ALCOHOL_CONTENT_ASC = 1
const val ALCOHOL_CONTENT_DESC = 2


package com.gaurav.beerlelo.ui.bottomsheet.quantity

import android.app.Dialog
import android.content.Context
import android.support.design.widget.BottomSheetDialogFragment
import android.view.View
import android.widget.Button
import android.widget.EditText
import com.gaurav.beerlelo.R
import kotlinx.android.synthetic.main.layout_quantitiy_bottom_sheet.view.*

class QuantitySheet : BottomSheetDialogFragment(), View.OnClickListener {

    lateinit var quantitySheetListener: QuantitySheetListener

    private var addButton: Button? = null
    private var quantity: EditText? = null
    private var beerId: Int? = null

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        quantitySheetListener = activity as QuantitySheetListener
    }

    override fun setupDialog(dialog: Dialog, style: Int) {

        val contentView = View.inflate(context, R.layout.layout_quantitiy_bottom_sheet, null)

        addButton = contentView.btn_bottom_sheet_quantity_add
        addButton?.setOnClickListener(this)

        beerId = arguments?.getInt("beerId")
        val count: Int = arguments?.getInt("quantity") ?: 0
        val str: String = count.toString()
        quantity = contentView.et_bottom_sheet_quantity_value
        quantity?.setText(str)

        dialog.setContentView(contentView)

    }

    override fun onClick(v: View) {

        quantitySheetListener.quantity(beerId, quantity?.text?.toString()?.toInt() ?: 0)

        dismiss()
    }

    interface QuantitySheetListener {
        fun quantity(beerId: Int?, quantity: Int)
    }
}
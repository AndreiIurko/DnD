package com.andreyyurko.dnd.ui.base

import android.app.AlertDialog
import android.content.DialogInterface
import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
open class BaseFragment : Fragment {
    constructor() : super()

    constructor(@LayoutRes contentLayoutId: Int) : super(contentLayoutId)

    fun showDialog(actionOnYes: () -> Unit, actionOnNo: () -> Unit) {
        val dialogClickListener: DialogInterface.OnClickListener =
            DialogInterface.OnClickListener { _, which ->
                when (which) {
                    DialogInterface.BUTTON_POSITIVE -> {
                        actionOnYes()
                    }

                    DialogInterface.BUTTON_NEGATIVE -> {
                        actionOnNo()
                    }
                }
            }

        val builder: AlertDialog.Builder = AlertDialog.Builder(context)
        builder.setMessage("Вы уверены?").setPositiveButton("Да", dialogClickListener)
            .setNegativeButton("Нет", dialogClickListener).show()
    }
}
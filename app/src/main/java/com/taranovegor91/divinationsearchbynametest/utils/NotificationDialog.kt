package com.taranovegor91.divinationsearchbynametest.utils

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.LayoutInflater
import android.view.ViewGroup
import android.view.Window
import com.taranovegor91.divinationsearchbynametest.databinding.AlertDialogItemBinding

class NotificationDialog(private val context: Context) {

    var alertDialog: AlertDialog? = null

    @SuppressLint("SetTextI18n")
    fun showDialog(callback: DialogCallback) {
        val view =
            AlertDialogItemBinding.inflate(context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater)
                .apply {
                    tvYes.setOnClickListener {
                        callback.invoke()
                        alertDialog?.dismiss()
                        alertDialog = null
                    }
                    tvNo.setOnClickListener {
                       // callback.invoke()
                        alertDialog?.dismiss()
                        alertDialog = null
                    }
                }.root
        if (view.parent != null) {
            (view.parent as ViewGroup).removeView(view)
        }
        alertDialog = AlertDialog.Builder(context).apply { setView(view) }.create()
        with(alertDialog!!) {
            requestWindowFeature(Window.FEATURE_NO_TITLE)
            setCancelable(false)
            window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            show()
        }
    }

    fun interface DialogCallback {
        fun invoke()
    }
}

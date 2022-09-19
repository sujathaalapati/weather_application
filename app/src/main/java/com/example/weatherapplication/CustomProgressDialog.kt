package com.example.weatherapplication

import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.View
import androidx.fragment.app.DialogFragment

class CustomProgressDialog : DialogFragment() {

    companion object {
        fun progressDialog(context: Context): Dialog {
            val dialog = Dialog(context, R.style.full_screen_dialog)
            val inflate = View.inflate(context, R.layout.progress_dialog, null)
            dialog.setContentView(inflate)
            dialog.setCancelable(false)
            dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            return dialog
        }
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        super.onCreateDialog(savedInstanceState)
        return progressDialog(requireActivity())
    }
}
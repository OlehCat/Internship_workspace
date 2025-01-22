package com.example.testapp1

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import androidx.fragment.app.DialogFragment

class OverlayDialogFragment : DialogFragment() {

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        println("qwe onCreateDialog OverlayDialogFragment")
        return AlertDialog.Builder(requireContext())
            .setTitle("Overlay Dialog")
            .setMessage("This dialog triggers onPause() in MainFragment but not onStop().")
            .setPositiveButton("Close") { _, _ -> dismiss() }
            .create()
    }

    override fun onStart() {
        super.onStart()
        println("qwe onStart OverlayDialogFragment")
        dialog?.window?.setBackgroundDrawableResource(android.R.color.transparent) // Optional: Transparent background
    }
}
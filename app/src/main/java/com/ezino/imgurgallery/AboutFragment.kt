package com.ezino.imgurgallery


import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import android.support.v7.app.AppCompatDialogFragment
import android.widget.TextView


class AboutFragment : AppCompatDialogFragment() {

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {

        val dialogBuilder = AlertDialog.Builder(activity)
        dialogBuilder.apply {
            setView(R.layout.fragment_about)
            setTitle(R.string.about_dialog_title)
            setIcon(R.drawable.ic_info_outline_white)
            setPositiveButton(R.string.about_dialog_positive_button) { dialog, _ -> dialog.dismiss() }
        }

        return dialogBuilder.create()
    }

    override fun onStart() {
        super.onStart()

        val versionView = dialog.findViewById(R.id.app_version) as TextView
        versionView.text = getString(R.string.about_dialog_app_version, BuildConfig.VERSION_NAME)
    }
}

package com.komsi.maleshop.ui.fragment

import android.app.Dialog
import android.content.Intent
import android.os.Bundle
import android.provider.Settings
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CompoundButton
import androidx.fragment.app.DialogFragment
import com.komsi.maleshop.R
import com.komsi.maleshop.persistence.Prefs
import kotlinx.android.synthetic.main.fragment_dialog_setting.*


class DialogSettingFragment : DialogFragment(), View.OnClickListener, CompoundButton.OnCheckedChangeListener {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_dialog_setting, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        layout_daily.setOnClickListener(this)
        languageList.setOnClickListener(this)
        switch2.setChecked(Prefs.getDailyReminderSetting(requireContext()))

        switch2.setOnCheckedChangeListener(this)

    }
    override fun onStart() {
        super.onStart()
        val dialog: Dialog? = dialog
        if (dialog != null) {
            val width = ViewGroup.LayoutParams.MATCH_PARENT
            val height = ViewGroup.LayoutParams.WRAP_CONTENT
            dialog.window?.setLayout(width, height)
        }
    }

    override fun onClick(p0: View) {
        when(p0.id){
            R.id.languageList -> {
                val i = Intent(Settings.ACTION_LOCALE_SETTINGS)
                startActivity(i)
            }
            R.id.layout_daily -> {}
        }
    }

    override fun onCheckedChanged(p0: CompoundButton, p1: Boolean) {
        Prefs.changeDailyReminder(requireContext(),p1)
    }
}

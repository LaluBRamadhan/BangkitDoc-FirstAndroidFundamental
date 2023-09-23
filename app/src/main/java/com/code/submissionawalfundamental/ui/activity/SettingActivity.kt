package com.code.submissionawalfundamental.ui.activity

import android.os.Bundle
import android.widget.CompoundButton
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.lifecycle.ViewModelProvider
import com.code.submissionawalfundamental.databinding.ActivitySettingBinding
import com.code.submissionawalfundamental.ui.viewmodel.ThemeViewModel
import com.code.submissionawalfundamental.ui.viewmodel.ThemeViewModelFactory
import com.code.submissionawalfundamental.util.SettingPreferences
import com.code.submissionawalfundamental.util.dataStore


class SettingActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySettingBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySettingBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val switch = binding.switchTheme
        val pref = SettingPreferences.getInstance(application.dataStore)
        val themeViewModel = ViewModelProvider(this, ThemeViewModelFactory(pref)).get(
            ThemeViewModel::class.java
        )


        themeViewModel.getThemeSettings().observe(this){isDarkModeActive: Boolean ->
            if (isDarkModeActive){
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
                switch.isChecked = true
            }else{
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
                switch.isChecked = false
            }
        }

        switch.setOnCheckedChangeListener{_:CompoundButton?, checked: Boolean ->
            themeViewModel.saveThemeSetting(checked)
        }
    }
}
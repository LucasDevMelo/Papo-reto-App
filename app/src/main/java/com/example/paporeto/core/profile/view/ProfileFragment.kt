package com.example.paporeto.core.profile.view

import android.os.Bundle
import android.preference.PreferenceFragment
import androidx.preference.PreferenceFragmentCompat
import com.example.paporeto.R
import java.util.prefs.Preferences

class ProfileFragment : PreferenceFragmentCompat(){
    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        setPreferencesFromResource(R.xml.preferences , rootKey)
    }
}
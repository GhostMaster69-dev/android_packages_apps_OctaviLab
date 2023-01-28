/*
 *  Copyright (C) 2015 The OmniROM Project
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 2 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 *
 */
package com.octavi.lab.fragments;

import com.android.internal.logging.nano.MetricsProto;

import android.app.Activity;
import android.content.Context;
import android.content.ContentResolver;
import android.content.pm.PackageManager;
import android.app.WallpaperManager;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Color;
import android.hardware.fingerprint.FingerprintManager;
import android.hardware.fingerprint.FingerprintSensorPropertiesInternal;
import android.net.Uri;
import android.os.Bundle;
import android.os.SystemProperties;
import androidx.preference.SwitchPreference;
import androidx.preference.ListPreference;
import androidx.preference.Preference;
import androidx.preference.PreferenceCategory;
import androidx.preference.PreferenceScreen;
import com.octavi.lab.preferences.SystemSettingSwitchPreference;
import com.octavi.lab.preferences.CustomSeekBarPreference;
import com.octavi.lab.preferences.SystemSettingListPreference;
//import com.android.internal.util.custom.FodUtils;
import com.android.settings.widget.CardPreference;

import android.provider.Settings;
import com.android.settings.R;
import com.android.settings.SettingsPreferenceFragment;

//import com.octavi.support.preferences.SecureSettingSwitchPreference;
//import com.octavi.support.preferences.SystemSettingSeekBarPreference;
import com.octavi.support.colorpicker.ColorPickerPreference;
import com.android.internal.util.octavi.OctaviUtils;

import java.util.List;

public class LockScreenSettings extends SettingsPreferenceFragment implements
        Preference.OnPreferenceChangeListener {

    @Override
    public void onCreate(Bundle icicle) {
        super.onCreate(icicle);
        addPreferencesFromResource(R.xml.octavi_lab_lockscreen);
        PreferenceScreen prefScreen = getPreferenceScreen();
        final PreferenceScreen prefSet = getPreferenceScreen();

        PreferenceCategory udfps = (PreferenceCategory) prefSet.findPreference("lockscreen_fod_category");
        if (!hasUDFPS(getActivity())) {
            prefSet.removePreference(udfps);
        }
    }

    public boolean onPreferenceChange(Preference preference, Object newValue) {
        ContentResolver resolver = getActivity().getContentResolver();
        return false;
    }

    /**
     * Checks if the device has udfps
     * @param context context for getting FingerprintManager
     * @return true is udfps is present
     */
    public static boolean hasUDFPS(Context context) {
        final FingerprintManager fingerprintManager =
                context.getSystemService(FingerprintManager.class);
        final List<FingerprintSensorPropertiesInternal> props =
                fingerprintManager.getSensorPropertiesInternal();
        return props != null && props.size() == 1 && props.get(0).isAnyUdfpsType();
    }

    @Override
    public int getMetricsCategory() {
        return MetricsProto.MetricsEvent.OCTAVI;
    }

}

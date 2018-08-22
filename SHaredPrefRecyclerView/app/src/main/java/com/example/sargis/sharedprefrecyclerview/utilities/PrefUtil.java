package com.example.sargis.sharedprefrecyclerview.utilities;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.example.sargis.sharedprefrecyclerview.model.PrefData;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public final class PrefUtil {
    private static SharedPreferences getPref(Context context) {
        return PreferenceManager.getDefaultSharedPreferences(context);
    }

    public static void saveString(Context context, String key, String value) {
        getPref(context).edit().putString(key, value).apply();
    }

    public static String getString(Context context, String key, String defValue) {
        return getPref(context).getString(key, defValue);
    }

    public static void removeString(Context context, String key) {
        getPref(context).edit().remove(key).apply();
    }

    public static List<PrefData> getAllValues(Context context) {
        final Map<String, ?> values = getPref(context).getAll();
        final List<PrefData> prefDataList = new ArrayList<>();
        for (Map.Entry<String, ?> entry : values.entrySet()) {
            final PrefData prefData = new PrefData();
            prefData.key = entry.getKey();
            prefData.value = entry.getValue().toString();
            prefDataList.add(prefData);
        }
        return prefDataList;
    }
}
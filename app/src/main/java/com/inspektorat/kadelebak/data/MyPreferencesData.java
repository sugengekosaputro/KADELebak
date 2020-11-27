package com.inspektorat.kadelebak.data;

import android.content.Context;
import android.content.SharedPreferences;

import com.inspektorat.kadelebak.Constant;

import java.util.HashSet;
import java.util.Set;

public class MyPreferencesData {

    private static MyPreferencesData myPreferencesData;
    private SharedPreferences sharedPreferences;

    public static MyPreferencesData getInstance(Context context) {
        if (myPreferencesData == null) {
            myPreferencesData = new MyPreferencesData(context);
        }
        return myPreferencesData;
    }

    private MyPreferencesData(Context context) {
        this.sharedPreferences = context.getSharedPreferences(Constant.SHARED_PREFERENCES_NAME,Context.MODE_PRIVATE);
    }

    public void saveData(String key, String val){
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(key, val);
        editor.apply();
    }

    public void saveDataArray(String sectionId){
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.clear().apply();

        Set<String> set = new HashSet<String>();
        set.add(sectionId);
        editor.putStringSet("ComplaintList", set);
        editor.apply();
    }

    public String getData(String key) {
        if (sharedPreferences!= null) {
            return sharedPreferences.getString(key, "");
        }
        return "";
    }

    public void clearData() {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.clear().apply();
    }
}

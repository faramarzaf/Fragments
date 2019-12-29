package com.faramarz.tictacdev.fragments.utils;

import android.content.Context;
import android.content.SharedPreferences;

public class SharedPref {

    SharedPreferences sharedPreferences;

    public SharedPref(Context context) {
        sharedPreferences = context.getSharedPreferences("filename", Context.MODE_PRIVATE);
    }

    public void setNightMode(boolean state) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean("NightMode", state);
        editor.commit();
    }


    public boolean loadNightMode() {
        Boolean state = sharedPreferences.getBoolean("NightMode", false);
        return state;

    }


}

package com.faramarz.tictacdev.fragments.main_fragments;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.SeekBar;
import android.widget.Switch;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.faramarz.tictacdev.fragments.R;
import com.faramarz.tictacdev.fragments.utils.SharedPref;

public class SettingFrag extends Fragment {

    String Mypre = "Save", SIZE = "size";
    TextView title1, title2, text_test;
    SeekBar seekbar_size;
    Switch aswitch;
    int fontsize = 16;
    SharedPreferences pref;
    SharedPref sharedPref;

    public SettingFrag() {

    }

    public static SettingFrag newInstance() {
        Bundle args = new Bundle();
        SettingFrag fragment = new SettingFrag();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_setting, container, false);
        bind(view);
        sharedPref = new SharedPref(getActivity());
        if (sharedPref.loadNightMode() == true) {
            getContext().getTheme().applyStyle(R.style.DarkAppTheme, true);

        } else {
            getContext().getTheme().applyStyle(R.style.AppTheme, true);

        }

        pref = this.getActivity().getSharedPreferences(Mypre, 0);
        permission();
        changeFontSize();
        nightMode();
        return view;
    }

    private void bind(View view) {
        title1 = view.findViewById(R.id.title1);
        title2 = view.findViewById(R.id.title2);
        text_test = view.findViewById(R.id.text_test);
        seekbar_size = view.findViewById(R.id.seekbar_size);
        aswitch = view.findViewById(R.id.aswitch);
    }


    void permission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (Settings.System.canWrite(getActivity())) {
            } else {
                Intent intent = new Intent(Settings.ACTION_MANAGE_WRITE_SETTINGS);
                intent.setData(Uri.parse("package: " + getActivity().getPackageName()));
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
            }
        }
    }


    void changeFontSize() {
        seekbar_size.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                SharedPreferences.Editor editor = pref.edit();
                fontsize = progress;
                text_test.setTextSize(fontsize);
                title1.setTextSize(fontsize);
                title2.setTextSize(fontsize);
                editor.putInt(SIZE, fontsize);
                editor.apply();
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

                if (fontsize < 16) {
                    fontsize = 16;
                    seekBar.setProgress(fontsize);
                }

            }
        });

        if (pref.contains(SIZE)) {
            fontsize = pref.getInt(SIZE, 0);
            seekbar_size.setProgress(fontsize);
            text_test.setTextSize(fontsize);
            title1.setTextSize(fontsize);
            title2.setTextSize(fontsize);
        }

    }


    void nightMode() {
        if (sharedPref.loadNightMode() == true) {
            aswitch.setChecked(true);
        }

        aswitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    sharedPref.setNightMode(true);
                    restartApp();

                } else {
                    sharedPref.setNightMode(false);
                    restartApp();

                }
            }

        });

    }

    void restartApp() {
        SettingFrag fr = new SettingFrag();
        Bundle args = new Bundle();
        fr.setArguments(args);
        FragmentManager fm = getFragmentManager();
        FragmentTransaction fragmentTransaction = fm.beginTransaction();
        fragmentTransaction.replace(R.id.frame_container, fr);
        fragmentTransaction.commit();

    }


}






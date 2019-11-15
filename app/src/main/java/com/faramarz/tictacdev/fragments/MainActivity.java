package com.faramarz.tictacdev.fragments;


import android.content.Intent;

import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.faramarz.tictacdev.fragments.main_fragments.HomeFrag;
import com.faramarz.tictacdev.fragments.main_fragments.ProfileFrag;
import com.faramarz.tictacdev.fragments.main_fragments.SettingFrag;
import com.google.android.material.bottomnavigation.BottomNavigationView;


public class MainActivity extends AppCompatActivity {

    private BottomNavigationView bottomNavigation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bind();
        setNavigationPages();
    }


    void bind() {
        bottomNavigation = findViewById(R.id.navigation);
    }

    void setNavigationPages() {
        loadFragment(new HomeFrag());
        bottomNavigation.inflateMenu(R.menu.menu_nav);
        bottomNavigation.getMenu().findItem(R.id.item_2).setChecked(true);
        bottomNavigation.setSelectedItemId(R.id.item_2);
        bottomNavigation.setOnNavigationItemSelectedListener(item -> {
            int id = item.getItemId();
            switch (id) {
                case R.id.item_1:
                    loadFragment(ProfileFrag.newInstance());
                    return true;
                case R.id.item_2:
                    loadFragment(HomeFrag.newInstance());
                    return true;
                case R.id.item_3:
                    settingPermission();
                    loadFragment(SettingFrag.newInstance());
                    return true;
            }
            return true;
        });
    }

    public void settingPermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (!Settings.System.canWrite(getApplicationContext())) {
                Intent intent = new Intent(Settings.ACTION_MANAGE_WRITE_SETTINGS, Uri.parse("package:" + getPackageName()));
                startActivityForResult(intent, 200);
            }
        }
    }


    private void loadFragment(Fragment fragment) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.setCustomAnimations(android.R.animator.fade_in, android.R.animator.fade_out);
        transaction.replace(R.id.frame_container, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

    @Override
    public void onBackPressed() {
        finish();
    }

}

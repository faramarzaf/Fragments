package com.faramarz.tictacdev.fragments.dialogs;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.faramarz.tictacdev.fragments.MainActivity;
import com.faramarz.tictacdev.fragments.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class EditDialog extends DialogFragment implements View.OnClickListener {


    public static final String MyPREFERENCES = "MyPrefs";
    public static final String FIRST_NAME = "firstNameKey";
    public static final String LAST_NAME = "lastNameKey";
    public static final String EMAIL = "emailKey";

    @BindView(R.id.edt_first_name)
    EditText edt_first_name;
    @BindView(R.id.edt_last_name)
    EditText edt_last_name;
    @BindView(R.id.edt_email)
    EditText edt_email;
    @BindView(R.id.btn_save_info)
    Button btn_save_info;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.dialog_edit_profile, container, false);
        ButterKnife.bind(this, view);
        RetrieveData();
        btn_save_info.setOnClickListener(this);
        return view;

    }

    void RetrieveData() {
        SharedPreferences preferences = this.getActivity().getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
        if (preferences.contains(FIRST_NAME) && preferences.contains(LAST_NAME) && preferences.contains(EMAIL)) {
            edt_first_name.setText(preferences.getString(FIRST_NAME, null));
            edt_last_name.setText(preferences.getString(LAST_NAME, null));
            edt_email.setText(preferences.getString(EMAIL, null));
        }
    }

    @Override
    public void onClick(View v) {
        String fName = edt_first_name.getText().toString();
        String lName = edt_last_name.getText().toString();
        String eMail = edt_email.getText().toString();
        SharedPreferences preferences = this.getActivity().getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
        SharedPreferences.Editor edt = preferences.edit();
        edt.putString(FIRST_NAME, fName);
        edt.putString(LAST_NAME, lName);
        edt.putString(EMAIL, eMail);
        edt.apply();
        Intent intent = new Intent(getActivity(), MainActivity.class);
        startActivity(intent);
        Toast.makeText(getActivity(), "Profile saved successfully", Toast.LENGTH_SHORT).show();

    }


}
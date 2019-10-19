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

    @BindView(R.id.edtFirstName)
    EditText edtFirstName;
    @BindView(R.id.edtLastName)
    EditText edtLastName;
    @BindView(R.id.edtEmail)
    EditText edtEmail;
    @BindView(R.id.btnSaveInfo)
    Button btnSaveInfo;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.dialog_edit_profile, container, false);
        ButterKnife.bind(this, view);
        RetrieveData();
        btnSaveInfo.setOnClickListener(this);
        return view;

    }

    void RetrieveData() {
        SharedPreferences preferences = this.getActivity().getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
        if (preferences.contains(FIRST_NAME) && preferences.contains(LAST_NAME) && preferences.contains(EMAIL)) {
            edtFirstName.setText(preferences.getString(FIRST_NAME, null));
            edtLastName.setText(preferences.getString(LAST_NAME, null));
            edtEmail.setText(preferences.getString(EMAIL, null));
        }
    }

    @Override
    public void onClick(View v) {
        String fName = edtFirstName.getText().toString();
        String lName = edtLastName.getText().toString();
        String eMail = edtEmail.getText().toString();
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
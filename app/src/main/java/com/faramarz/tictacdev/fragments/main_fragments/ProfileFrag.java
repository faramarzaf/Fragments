package com.faramarz.tictacdev.fragments.main_fragments;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.faramarz.tictacdev.fragments.R;
import com.faramarz.tictacdev.fragments.dialogs.EditDialog;

import butterknife.BindView;
import butterknife.ButterKnife;


public class ProfileFrag extends Fragment implements View.OnClickListener {

    public static final String MyPREFERENCES = "MyPrefs";
    public static final String FIRST_NAME = "firstNameKey";
    public static final String LAST_NAME = "lastNameKey";
    public static final String EMAIL = "emailKey";

    @BindView(R.id.firstName)
    TextView firstName;
    @BindView(R.id.lastName)
    TextView lastName;
    @BindView(R.id.email)
    TextView email;
    @BindView(R.id.btn_edit)
    Button btn_edit;


    public ProfileFrag() {

    }

    public static ProfileFrag newInstance() {

        Bundle args = new Bundle();

        ProfileFrag fragment = new ProfileFrag();
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profile, container, false);
        ButterKnife.bind(this, view);
        SharedPreferences preferences = this.getActivity().getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
        String fName = preferences.getString(FIRST_NAME, "");
        String lName = preferences.getString(LAST_NAME, "");
        String eMail = preferences.getString(EMAIL, "");
        firstName.setText(fName);
        lastName.setText(lName);
        email.setText(eMail);
        btn_edit.setOnClickListener(this);
        return view;

    }

    @Override
    public void onClick(View v) {
        showEditDialog();
    }

    void showEditDialog() {
        EditDialog editDialog = new EditDialog();
        editDialog.show(getFragmentManager(), "EditDialog");
    }


}

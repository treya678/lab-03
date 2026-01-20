package com.example.listycity03;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

public class AddEditCityFragment extends DialogFragment {

    public interface Listener {
        void onCityEdited();
    }

    private static final String ARG_CITY = "city";

    public static AddEditCityFragment newInstance(City city) {
        AddEditCityFragment f = new AddEditCityFragment();
        Bundle args = new Bundle();
        args.putSerializable(ARG_CITY, city);
        f.setArguments(args);
        return f;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {

        View v = LayoutInflater.from(getActivity()).inflate(R.layout.dialog_city, null);
        EditText nameEt = v.findViewById(R.id.edit_city_name);
        EditText provEt = v.findViewById(R.id.edit_city_province);

        City city = (City) getArguments().getSerializable(ARG_CITY);

        nameEt.setText(city.getName());
        provEt.setText(city.getProvince());

        return new AlertDialog.Builder(getActivity())
                .setTitle("Edit city")
                .setView(v)
                .setNegativeButton("Cancel", null)
                .setPositiveButton("OK", (dialog, which) -> {
                    city.setName(nameEt.getText().toString().trim());
                    city.setProvince(provEt.getText().toString().trim());

                    if (getActivity() instanceof Listener) {
                        ((Listener) getActivity()).onCityEdited();
                    }
                })
                .create();
    }
}


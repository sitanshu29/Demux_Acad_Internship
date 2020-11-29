package com.example.demux_acad_internship;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

public class filterDialog extends DialogFragment {

    public static final String TAG = "filterDialog";

    public interface OnInputSelected
    {
        void sendInput(String sortby, String level, String company, String nature, String tags);
    }
    public OnInputSelected onInputSelected;

    Spinner spin1, spin2, spin3, spin4, spin5;
    Button cancel, submit;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container, Bundle savedInstanceState){

        View view = inflater.inflate(R.layout.layout_filter_dialog, container, false);
        spin1 = view.findViewById(R.id.spin1);
        spin2 = view.findViewById(R.id.spin2);
        spin3 = view.findViewById(R.id.spin3);
        spin4 = view.findViewById(R.id.spin4);
        spin5 = view.findViewById(R.id.spin5);
        cancel = view.findViewById(R.id.cancel);
        submit = view.findViewById(R.id.submit);

        ArrayAdapter<String> adapter1 = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_item,
                       getResources().getStringArray(R.array.SortBy));
               adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
               spin1.setAdapter(adapter1);

        ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_item,
                getResources().getStringArray(R.array.Level));
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spin2.setAdapter(adapter2);

        ArrayAdapter<String> adapter3 = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_item,
                getResources().getStringArray(R.array.Company));
        adapter3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spin3.setAdapter(adapter3);

        ArrayAdapter<String> adapter4 = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_item,
                getResources().getStringArray(R.array.Nature));
        adapter4.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spin4.setAdapter(adapter4);

        ArrayAdapter<String> adapter5 = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_item,
                getResources().getStringArray(R.array.Tags));
        adapter5.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spin5.setAdapter(adapter5);

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getDialog().dismiss();
            }
        });

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String ip1 = spin1.getSelectedItem().toString();
                String ip2 = spin2.getSelectedItem().toString();
                String ip3 = spin3.getSelectedItem().toString();
                String ip4 = spin4.getSelectedItem().toString();
                String ip5 = spin5.getSelectedItem().toString();

                onInputSelected.sendInput(ip1, ip2, ip3, ip4, ip5);

                getDialog().dismiss();
            }
        });

        return  view;

    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        try {
            onInputSelected = (OnInputSelected)getTargetFragment();
        }
        catch (ClassCastException e)
        {
            Log.e(TAG, "OnAttach: ClassCasException: "+e.getMessage());
        }

    }
}

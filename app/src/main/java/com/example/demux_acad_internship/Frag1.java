package com.example.demux_acad_internship;

import android.app.AlertDialog;
import android.os.Bundle;

import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.FirebaseDatabase;

public class Frag1 extends Fragment implements filterDialog.OnInputSelected {


    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";


    private String mParam1;
    private String mParam2;

    RecyclerView recView;
    myadapter adapter;
    androidx.appcompat.widget.SearchView searchView;
    ImageView filter;
    String s1 = "Index", s2 = "All", s3 = "All", s4 = "All", s5 = "All";


    public Frag1() {

    }


    public static Frag1 newInstance(String param1, String param2) {
        Frag1 fragment = new Frag1();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container,
                             Bundle savedInstanceState) {

        View view =  inflater.inflate(R.layout.fragment_frag1, container, false);
        recView = view.findViewById(R.id.recyclerView);
        filter = view.findViewById(R.id.filter);
        recView.setLayoutManager(new LinearLayoutManager(getContext()));

        FirebaseRecyclerOptions<Question> options =
                new FirebaseRecyclerOptions.Builder<Question>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("Questions").orderByChild("level"), Question.class)
                        .build();

        adapter = new myadapter(options);
        recView.setAdapter(adapter);
        searchView = view.findViewById(R.id.searchView);

//        filter.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                AlertDialog.Builder mBuilder = new AlertDialog.Builder(getContext());
//                View mView = getLayoutInflater().inflate(R.layout.layout_filter_dialog, null);
//                mBuilder.setTitle("Search Filter");
//                final Spinner spin1 = mView.findViewById(R.id.spin1);
//               ArrayAdapter<String> adapter1 = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_item,
//                       getResources().getStringArray(R.array.SortByList));
//               adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//               spin1.setAdapter(adapter1);
//
//               String temp = spin1.getSelectedItem().toString();
//
//                mBuilder.setView(mView);
//                AlertDialog dialog = mBuilder.create();
//                dialog.show();
//            }
//        });


        filter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                filterDialog dialog = new filterDialog();
                dialog.setTargetFragment(Frag1.this, 1);
                dialog.show(getFragmentManager(), "filterDialog");
            }
        });




        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                processSearch(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                processSearch(newText);
                return false;
            }
        });
        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        adapter.startListening();
    }

    @Override
    public void onStop() {
        super.onStop();
        adapter.stopListening();
    }

    private void processSearch(String s)
    {

            FirebaseRecyclerOptions<Question> options =
                    new FirebaseRecyclerOptions.Builder<Question>()
                            .setQuery(FirebaseDatabase.getInstance().getReference().child("Questions").orderByChild("title").startAt(s).endAt(s + "\uf8ff"), Question.class)
                            .build();

            adapter = new myadapter(options);
            adapter.startListening();
            recView.setAdapter(adapter);
    }


    @Override
    public void sendInput(String sortby, String level, String company, String nature, String tags) {

        s1 = sortby;
        s2 = level;
        s3 = company;
        s4 = nature;
        s5 = tags;

    }
}
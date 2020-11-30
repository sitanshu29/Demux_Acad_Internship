package com.example.demux_acad_internship;

import android.graphics.Color;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;


public class descFragment extends Fragment {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    String  title,  bookmark,level, c1url,  c2url, c3url,  c4url,  c5url, c6url,problem;
    int index, upVote, frequency;
    public descFragment() {

    }

    public descFragment(int index, String title, String bookmark, String level, String c1url, String c2url, String c3url, String c4url, String c5url, String c6url, int upVote, int frequency, String problem) {
            this.index = index; this.title = title; this.bookmark = bookmark; this.level = level; this.c1url = c1url; this.c2url = c2url; this.c3url = c3url;
            this.c4url = c4url; this.c5url = c5url; this.c6url = c6url; this.upVote = upVote; this.frequency = frequency; this.problem = problem;

    }


    public static descFragment newInstance(String param1, String param2) {
        descFragment fragment = new descFragment();
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
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View itemView = inflater.inflate(R.layout.fragment_desc, container, false);
        TextView Qid, Title, Level, UpVote, Frequency, Problem;
        ImageView Bookmark, C1, C2, C3, C4, C5, C6;

        Qid = itemView.findViewById(R.id.qid);
        Problem = itemView.findViewById(R.id.probdesc);
        Title = itemView.findViewById(R.id.qtitle);
        Level = itemView.findViewById(R.id.level);
        UpVote = itemView.findViewById(R.id.star_count);
        Frequency = itemView.findViewById(R.id.freq);
        Bookmark = itemView.findViewById(R.id.heart);
        C1 = itemView.findViewById(R.id.comp1);
        C2 = itemView.findViewById(R.id.comp2);
        C3 = itemView.findViewById(R.id.comp3);
        C4 = itemView.findViewById(R.id.comp4);
        C5 = itemView.findViewById(R.id.comp5);
        C6 = itemView.findViewById(R.id.comp6);

        Qid.setText("#"+Integer.toString(index));
        Problem.setText(problem);
        Title.setText(title);
        Level.setText(level);
        UpVote.setText(Integer.toString(upVote));
        Frequency.setText(Integer.toString(frequency));

        int check = Integer.parseInt(bookmark);
        if (check == 0)
        {
            Bookmark.setImageResource(R.drawable.heart_empty);
        }
        else
        {
            Bookmark.setImageResource(R.drawable.heart_fill);
        }

        int lev = Integer.parseInt(level);
        if (lev == 0)
        {
            Level.setText("EASY");
            Level.setBackgroundColor(Color.GREEN);
        }
        else if (lev == 1)
        {
            Level.setText("AVG");
            Level.setBackgroundColor(Color.rgb(205,127,50));
        }
        else
        {
            Level.setText("HARD");
            Level.setBackgroundColor(Color.RED);
        }

        if (c1url.equals("NULL"))
        {
            C1.setVisibility(View.INVISIBLE);
        }
        else
        {
            Glide.with(C1.getContext()).load(c1url).into(C1);
        }


        if (c2url.equals("NULL"))
        {
            C2.setVisibility(View.INVISIBLE);
        }
        else
        {
            Glide.with(C2.getContext()).load(c2url).into(C2);
        }


        if (c3url.equals("NULL"))
        {
            C3.setVisibility(View.INVISIBLE);
        }
        else
        {
            Glide.with(C3.getContext()).load(c3url).into(C3);
        }

        if (c4url.equals("NULL"))
        {
            C4.setVisibility(View.INVISIBLE);
        }
        else
        {
            Glide.with(C4.getContext()).load(c4url).into(C4);
        }


        if (c5url.equals("NULL"))
        {
            C5.setVisibility(View.INVISIBLE);
        }
        else
        {
            Glide.with(C5.getContext()).load(c5url).into(C5);
        }


        if (c6url.equals("NULL"))
        {
            C6.setVisibility(View.INVISIBLE);
        }
        else
        {
            Glide.with(C6.getContext()).load(c6url).into(C6);
        }

        return itemView;
    }

    public void onBackPressed()
    {
        AppCompatActivity activity = (AppCompatActivity)getContext();
        activity.getSupportFragmentManager().beginTransaction().replace(R.id.container, new Frag1())
                .addToBackStack(null).commit();
    }
}
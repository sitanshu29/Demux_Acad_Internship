package com.example.demux_acad_internship;


import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.FirebaseDatabase;

public class myadapter extends FirebaseRecyclerAdapter<Question, myadapter.myViewHolder> {


    public myadapter(@NonNull FirebaseRecyclerOptions<Question> options) {


        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull final myViewHolder holder, final int position, @NonNull final Question model) {

            holder.Qid.setText("#"+model.getIndex());
            holder.Title.setText(model.getTitle());
            holder.UpVote.setText(model.getUpVote());
            holder.Frequency.setText(model.getFrequency());
            String qDescription = model.getProblem();
            String temp = model.getBookmarked();
            int check = Integer.parseInt(temp);
            if (check == 0)
            {
                holder.Bookmark.setImageResource(R.drawable.heart_empty);
            }
            else
            {
                holder.Bookmark.setImageResource(R.drawable.heart_fill);
            }
            int lev = Integer.parseInt(model.getLevel());
            if (lev == 0)
            {
                holder.Level.setText("EASY");
                holder.Level.setBackgroundColor(Color.GREEN);
            }
            else if (lev == 1)
            {
                holder.Level.setText("AVG");
                holder.Level.setBackgroundColor(Color.rgb(205,127,50));
            }
            else
            {
                holder.Level.setText("HARD");
                holder.Level.setBackgroundColor(Color.RED);
            }

           temp = model.getC1url();
            if (temp.equals("NULL"))
            {
                holder.C1.setVisibility(View.INVISIBLE);
            }
            else
            {
                Glide.with(holder.C1.getContext()).load(temp).into(holder.C1);
            }

        temp = model.getC2url();
        if (temp.equals("NULL"))
        {
            holder.C2.setVisibility(View.INVISIBLE);
        }
        else
        {
            Glide.with(holder.C2.getContext()).load(temp).into(holder.C2);
        }

        temp = model.getC3url();
        if (temp.equals("NULL"))
        {
            holder.C3.setVisibility(View.INVISIBLE);
        }
        else
        {
            Glide.with(holder.C3.getContext()).load(temp).into(holder.C3);
        }

        temp = model.getC4url();
        if (temp.equals("NULL"))
        {
            holder.C4.setVisibility(View.INVISIBLE);
        }
        else
        {
            Glide.with(holder.C4.getContext()).load(temp).into(holder.C4);
        }

        temp = model.getC5url();
        if (temp.equals("NULL"))
        {
            holder.C5.setVisibility(View.INVISIBLE);
        }
        else
        {
            Glide.with(holder.C5.getContext()).load(temp).into(holder.C5);
        }

        temp = model.getC6url();
        if (temp.equals("NULL"))
        {
            holder.C6.setVisibility(View.INVISIBLE);
        }
        else
        {
            Glide.with(holder.C6.getContext()).load(temp).into(holder.C6);
        }

        holder.Bookmark.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int like = Integer.parseInt(model.getBookmarked());
                if (like == 0)
                {
                    FirebaseDatabase.getInstance().getReference().child("Questions").child(getRef(position).getKey()).child("bookmarked").setValue("1");
                }
                else
                {
                    FirebaseDatabase.getInstance().getReference().child("Questions").child(getRef(position).getKey()).child("bookmarked").setValue("0")
                    .addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void aVoid) {

                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {

                        }
                    });
                }
            }
        });


        holder.Title.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AppCompatActivity activity = (AppCompatActivity)v.getContext();
                activity.getSupportFragmentManager().beginTransaction().replace(R.id.container, new descFragment(model.getIndex(), model.getTitle(), model.getBookmarked(), model.getLevel(), model.getC1url(), model.getC2url(), model.getC3url(), model.getC4url(), model.getC5url(), model.getC6url(), model.getUpVote(), model.getFrequency(), model.getProblem()))
                        .addToBackStack(null).commit();
            }
        });




    }

    @NonNull
    @Override
    public myViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card, parent, false);
        return new myViewHolder(view);

    }

    class myViewHolder extends RecyclerView.ViewHolder
    {
        TextView Qid, Title, Level, UpVote, Frequency;
        ImageView Bookmark, C1, C2, C3, C4, C5, C6;

        public myViewHolder(@NonNull View itemView) {
            super(itemView);

            Qid = itemView.findViewById(R.id.qid);
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
        }
    }
}

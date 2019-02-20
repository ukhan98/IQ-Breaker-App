package com.example.ali.iqbreaker3;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

public class FactsAdapter extends RecyclerView.Adapter<FactsAdapter.MyViewHolder> {

    //hold the data for the adapter
    ArrayList<Fact> mFacts;

    // a constructor that accepts the Array of Provinces
    public FactsAdapter(ArrayList<Fact> facts) {
        mFacts = facts;
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        // holds a references to the TextView
        private TextView mTxtFacts;
        private TextView mTextExplanations;

        // constructor
        public MyViewHolder(LinearLayout layout) {
            super(layout);

            mTxtFacts = layout.findViewById(R.id.txt_fact);
            mTextExplanations = layout.findViewById(R.id.txt_explanation);
        }
    }

    @Override
    public FactsAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LinearLayout layout = (LinearLayout) LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fact_layout, parent, false);

        MyViewHolder holder = new MyViewHolder(layout);

        return holder;
    }

    @Override
    public void onBindViewHolder(FactsAdapter.MyViewHolder holder, int position) {
        holder.mTxtFacts.setText(mFacts.get(position).getName());
        holder.mTextExplanations.setText(mFacts.get(position).getCapital());

    }

    @Override
    public int getItemCount() {
        return mFacts.size();
    }


}

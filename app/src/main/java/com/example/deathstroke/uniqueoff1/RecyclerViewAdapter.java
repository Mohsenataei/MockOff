package com.example.deathstroke.uniqueoff1;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder> {

    private String[] mDataset;
    public static class MyViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public TextView textView;
        public MyViewHolder(TextView v) {
            super(v);
            textView = v;
        }
    }
    @Override
    public int getItemCount() {
        return mDataset.length;
    }
    @Override
    public RecyclerViewAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent,
                                                     int viewType) {
        // create a new view
        //TextView v = (TextView) LayoutInflater.from(parent.getContext()).inflate(R.layout.my_text_view, parent, false);
        MyViewHolder vh ;
        //vh = new MyViewHolder(v);
        //return vh;
        return null;
    }
    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        holder.textView.setText(mDataset[position]);

    }
}

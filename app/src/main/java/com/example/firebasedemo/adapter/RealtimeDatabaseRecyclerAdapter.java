package com.example.firebasedemo.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.firebasedemo.R;

import java.util.ArrayList;
import java.util.List;

public class RealtimeDatabaseRecyclerAdapter extends RecyclerView.Adapter<RealtimeDatabaseRecyclerAdapter.ViewHolder> {

    private Context context;
    private List<String> lst;

    public RealtimeDatabaseRecyclerAdapter(Context context, List<String> lst){
        this.context = context;
        this.lst = lst;
    }

    public RealtimeDatabaseRecyclerAdapter(Context context){
        this.context = context;
        this.lst = new ArrayList<>();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.item_realtime_database, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RealtimeDatabaseRecyclerAdapter.ViewHolder holder, int position) {
        holder.txt.setText(lst.get(position));
        Log.d("onBindViewHolder", lst.get(position));
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    @Override
    public int getItemCount() {
        return lst.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        private TextView txt;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txt = itemView.findViewById(R.id.txt);
        }
    }

    public List<String> getLst() {
        return lst;
    }

    public void setLst(List<String> lst) {
        this.lst = lst;
        notifyDataSetChanged();
    }
}

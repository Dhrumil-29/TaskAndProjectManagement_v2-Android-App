package com.example.taskandprojectmanagement_v2.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.taskandprojectmanagement_v2.Models.TodayTaskModel;
import com.example.taskandprojectmanagement_v2.R;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class TodayTaskAdapter extends RecyclerView.Adapter<TodayTaskAdapter.viewHolder> {
    ArrayList<TodayTaskModel> list;
    Context context;

    public TodayTaskAdapter(ArrayList<TodayTaskModel> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @NotNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.sample_today_task, parent, false);

        return new viewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull TodayTaskAdapter.viewHolder holder, int position) {
        TodayTaskModel model = list.get(position);
        holder.title.setText(model.getTitle());
        holder.time.setText(model.getTime());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class viewHolder extends RecyclerView.ViewHolder {
        TextView title, time;

        public viewHolder(@NonNull @NotNull View itemView) {
            super(itemView);

            title = itemView.findViewById(R.id.title);
            time = itemView.findViewById(R.id.time);
        }
    }
}

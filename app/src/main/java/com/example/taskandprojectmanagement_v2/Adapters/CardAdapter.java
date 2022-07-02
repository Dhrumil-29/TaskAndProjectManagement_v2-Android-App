package com.example.taskandprojectmanagement_v2.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.taskandprojectmanagement_v2.Models.CardModel;
import com.example.taskandprojectmanagement_v2.R;
import com.example.taskandprojectmanagement_v2.RecyclerViewClickListener;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class CardAdapter extends RecyclerView.Adapter<CardAdapter.viewHolder> {

    ArrayList<CardModel> list;
    Context context;
    private RecyclerViewClickListener listener;


    public CardAdapter(ArrayList<CardModel> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @NotNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.sample_card_view, parent, false);

        return new viewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull CardAdapter.viewHolder holder, int position) {
        CardModel model = list.get(position);
        holder.projectBackground.setImageResource(model.getProjectBackground());
        holder.projectName.setText(model.getProjectName());
        holder.projectDescription.setText(model.getProjectDescription());
        holder.projectProgress.setText(model.getProjectProgress());
        holder.projectProgressBar.setProgress(Integer.parseInt(model.getProjectProgress()));

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public void setItemClick(RecyclerViewClickListener mListener) {
        this.listener = mListener;
    }

    public class viewHolder extends RecyclerView.ViewHolder /*implements View.OnClickListener */ {
        ImageView projectBackground;
        TextView projectName, projectDescription, projectProgress;
        ProgressBar projectProgressBar;
        CardView project1;

        public viewHolder(@NonNull @NotNull View itemView) {
            super(itemView);


            projectBackground = itemView.findViewById(R.id.projectBackground);
            projectName = itemView.findViewById(R.id.projectName);
            project1 = itemView.findViewById(R.id.project1);
            projectDescription = itemView.findViewById(R.id.projectDescription);
            projectProgress = itemView.findViewById(R.id.projectProgress);
            projectProgressBar = itemView.findViewById(R.id.projectProgressBar);

            CardView project1 = itemView.findViewById(R.id.project1);
            project1.setOnClickListener(view -> listener.onItemClick(view, getAdapterPosition()));
        }
    }
}
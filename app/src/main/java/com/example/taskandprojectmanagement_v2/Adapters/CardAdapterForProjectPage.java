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

public class CardAdapterForProjectPage extends RecyclerView.Adapter<CardAdapterForProjectPage.viewHolder> {
    ArrayList<CardModel> list;
    Context context;
    private RecyclerViewClickListener listener;

    public CardAdapterForProjectPage(ArrayList<CardModel> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @NotNull
    @Override
    public CardAdapterForProjectPage.viewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.sample_card_view_for_project_page, parent, false);

        return new CardAdapterForProjectPage.viewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull CardAdapterForProjectPage.viewHolder holder, int position) {
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

    public class viewHolder extends RecyclerView.ViewHolder {
        ImageView projectBackground;
        TextView projectName, projectDescription, projectProgress;
        ProgressBar projectProgressBar;

        public viewHolder(@NonNull @NotNull View itemView) {
            super(itemView);

            projectBackground = itemView.findViewById(R.id.projectBackground);
            projectName = itemView.findViewById(R.id.projectName);
            projectDescription = itemView.findViewById(R.id.projectDescription);
            projectProgress = itemView.findViewById(R.id.projectProgress);
            projectProgressBar = itemView.findViewById(R.id.projectProgressBar);

            CardView project1 = itemView.findViewById(R.id.project1);
            project1.setOnClickListener(view -> listener.onItemClick(view, getAdapterPosition()));
        }
    }
}


package com.example.taskandprojectmanagement_v2;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.taskandprojectmanagement_v2.Adapters.CardAdapterForDocumentView;
import com.example.taskandprojectmanagement_v2.Adapters.CardAdapterForProjectPage;
import com.example.taskandprojectmanagement_v2.Models.CardModel;
import com.example.taskandprojectmanagement_v2.databinding.FragmentProjectBinding;
import com.google.android.material.bottomappbar.BottomAppBar;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class ProjectFragment extends Fragment implements RecyclerViewClickListener {

    FragmentProjectBinding binding;
    CardAdapterForProjectPage adapter;

    ArrayList<CardModel> list = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentProjectBinding.inflate(inflater, container, false);
        BottomAppBar bottomAppBar = requireActivity().findViewById(R.id.bottomAppBar);
        bottomAppBar.setVisibility(View.VISIBLE);

        FloatingActionButton floatingActionButton = requireActivity().findViewById(R.id.floatingActionButton);
        floatingActionButton.setVisibility(View.VISIBLE);


        ImageButton document_view = binding.documentView;
        document_view.setOnClickListener(v -> document_view());

        ImageButton image_view = binding.imageView;
        image_view.setOnClickListener(v -> image_view());

        list.add(new CardModel("Project 1", "Project Descriptions", "80", R.drawable.project_background1));
        list.add(new CardModel("Project 2", "Project Descriptions", "10", R.drawable.project_background2));
        list.add(new CardModel("Project 3", "Project Descriptions", "30", R.drawable.project_background3));
        list.add(new CardModel("Project 4", "Project Descriptions", "40", R.drawable.project_background4));
        list.add(new CardModel("Project 5", "Project Descriptions", "70", R.drawable.project_background5));

        adapter = (CardAdapterForProjectPage) new CardAdapterForProjectPage(list, getContext());
        binding.recycleView.setAdapter(adapter);
        adapter.setItemClick(this);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        binding.recycleView.setLayoutManager(linearLayoutManager);

        return binding.getRoot();
    }

    private void image_view() {
        binding.imageView.setColorFilter(ContextCompat.getColor(getContext(), R.color.light_blue));
        binding.documentView.setColorFilter(ContextCompat.getColor(getContext(), R.color.light_black));
        CardAdapterForProjectPage adapter = new CardAdapterForProjectPage(list, getContext());
        binding.recycleView.setAdapter(adapter);
        adapter.setItemClick(this);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        binding.recycleView.setLayoutManager(linearLayoutManager);
    }

    private void document_view() {
        binding.imageView.setColorFilter(ContextCompat.getColor(getContext(), R.color.light_black));
        binding.documentView.setColorFilter(ContextCompat.getColor(getContext(), R.color.light_blue));
        CardAdapterForDocumentView adapter = new CardAdapterForDocumentView(list, getContext());
        binding.recycleView.setAdapter(adapter);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        binding.recycleView.setLayoutManager(linearLayoutManager);
        adapter.setItemClick(this);
    }


    @Override
    public void onItemClick(View view, int position) {
        if (view.getId() == R.id.project1) {
            String projectName = list.get(position).getProjectName();
            String projectDescription = list.get(position).getProjectDescription();
            String projectProgress = list.get(position).getProjectProgress();
            String projectBackground = Integer.toString(list.get(position).getProjectBackground());
            Bundle bundle = new Bundle();

            bundle.putString("projectName", projectName);
            bundle.putString("projectDescription", projectDescription);
            bundle.putString("projectProgress", projectProgress);
            bundle.putString("projectBackground", projectBackground);

            ProjectDetailFragment fragment = new ProjectDetailFragment();
            fragment.setArguments(bundle);
            getParentFragmentManager().beginTransaction().replace(R.id.frame_layout, fragment).commit();

        }
    }
}
package com.example.taskandprojectmanagement_v2;

import android.os.Bundle;

import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.taskandprojectmanagement_v2.Adapters.CardAdapterForDocumentView;
import com.example.taskandprojectmanagement_v2.Adapters.CardAdapterForProjectPage;
import com.example.taskandprojectmanagement_v2.HomeFragment;
import com.example.taskandprojectmanagement_v2.Models.CardModel;
import com.example.taskandprojectmanagement_v2.R;
import com.example.taskandprojectmanagement_v2.databinding.FragmentAllRecentProjectsBinding;
import com.google.android.material.bottomappbar.BottomAppBar;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class AllRecentProjectsFragment extends Fragment {

    FragmentAllRecentProjectsBinding binding;
    ArrayList<CardModel> list = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentAllRecentProjectsBinding.inflate(inflater, container, false);//        View view = inflater.inflate(R.layout.fragment_all_recent_projects, container, false);

        BottomAppBar bottomAppBar = requireActivity().findViewById(R.id.bottomAppBar);
        bottomAppBar.setVisibility(View.GONE);

        FloatingActionButton floatingActionButton = requireActivity().findViewById(R.id.floatingActionButton);
        floatingActionButton.setVisibility(View.GONE);

        TextView backFromRecentProject = binding.backFromRecentProject;
        backFromRecentProject.setOnClickListener(v -> backFromRecentProject());

        ImageButton document_view = binding.documentView;
        document_view.setOnClickListener(v -> document_view());

        ImageButton image_view = binding.imageView;
        image_view.setOnClickListener(v -> image_view());

        list.add(new CardModel("Project Name", "Project Descriptions", "80", R.drawable.project_background1));
        list.add(new CardModel("Project Name", "Project Descriptions", "10", R.drawable.project_background2));
        list.add(new CardModel("Project Name", "Project Descriptions", "30", R.drawable.project_background3));
        list.add(new CardModel("Project Name", "Project Descriptions", "40", R.drawable.project_background4));
        list.add(new CardModel("Project Name", "Project Descriptions", "70", R.drawable.project_background5));

        CardAdapterForProjectPage adapter = new CardAdapterForProjectPage(list, getContext());
        binding.recycleView.setAdapter(adapter);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        binding.recycleView.setLayoutManager(linearLayoutManager);


        return binding.getRoot();
    }

    private void image_view() {
        binding.imageView.setColorFilter(ContextCompat.getColor(getContext(), R.color.light_blue));
        binding.documentView.setColorFilter(ContextCompat.getColor(getContext(), R.color.light_black));
        CardAdapterForProjectPage adapter = new CardAdapterForProjectPage(list, getContext());
        binding.recycleView.setAdapter(adapter);

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
    }

    public void backFromRecentProject() {
        FragmentTransaction fragmentTransaction = requireActivity().getSupportFragmentManager().beginTransaction();
        replaceFragment(fragmentTransaction, new HomeFragment());
    }

    private void replaceFragment(FragmentTransaction fragmentTransaction, Fragment replaceFragmentName) {
        fragmentTransaction.replace(R.id.frame_layout, replaceFragmentName).addToBackStack("");
        fragmentTransaction.commit();
    }
}
